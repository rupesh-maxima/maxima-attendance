package attendance.timelog.impl;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import attendance.common.impl.GenericControllerImpl;
import attendance.common.utils.DateOperations;
import attendance.employee.controller.EmployeeController;
import attendance.employee.model.Employee;
import attendance.timelog.controller.TimelogController;
import attendance.timelog.model.Timelog;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RestController
public class TimelogControllerImpl extends GenericControllerImpl<Timelog> implements TimelogController {
	@Override
	public Class<Timelog> getObjectClass() {
		return Timelog.class;
	}

	@Autowired
	EmployeeController employeeController;

	@Override
	public void setWorkfromHome(String json) {
		Boolean workFromHome = new JSONObject(json).getBoolean("isWorkFromHome");
		setWorkFromHome(parseIds(json), parseDate(json), workFromHome);
	}

	private void setWorkFromHome(List<Long> ids, Date recordDate, Boolean workFromHome) {
		List<Timelog> timelogs = getAllTimeLogsForEmployeesForDate(ids, recordDate);
		List<Long> existingIds = new ArrayList<Long>();
		for (Timelog timelog : timelogs) {
			existingIds.add(timelog.getEmployee().getId());
			timelog.setWorkFromHome(workFromHome);
			update(timelog);
		}
		ids.removeAll(existingIds);
		if (!ids.isEmpty()) {
			for (Employee employee : employeeController.getAll(ids)) {
				update(new Timelog(recordDate, employee, workFromHome));
			}
		}
	}

	private List<Timelog> getAllTimeLogsForEmployeesForDate(List<Long> ids, Date recordDate) {
		return entityManager
				.createQuery(
						"from " + getObjectClass().getName()
								+ " t join fetch t.employee e where e.id in(:ids) and recordDate=(:recordDate)",
						getObjectClass())
				.setParameter("ids", ids).setParameter("recordDate", recordDate).getResultList();
	}

	private List<Timelog> getAllTimeLogsForEmployeesForMonth(List<Long> ids, Date recordDate) {
		return entityManager
				.createQuery(
						"from " + getObjectClass().getName()
								+ " t join fetch t.employee e where e.id in(:ids) and recordDate >=(:firstDayOfMonth) and recordDate <=(:lastDayOfMonth)",
						getObjectClass())
				.setParameter("ids", ids)
				.setParameter("firstDayOfMonth", DateOperations.getFirstDateOfMonth(recordDate))
				.setParameter("lastDayOfMonth", DateOperations.getLastDateOfMonth(recordDate)).getResultList();
	}

	@Override
	public List<Employee> getWorkfromHome(Date recordDate) {
		return entityManager
				.createQuery(
						"select t.employee from " + getObjectClass().getName()
								+ " t where workFromHome = (:workFromHome) and recordDate=(:recordDate)",
						Employee.class)
				.setParameter("recordDate", DateOperations.setZeroTime(recordDate)).setParameter("workFromHome", true)
				.getResultList();
	}

	@Override
	public List<Employee> getOnLeave(Date recordDate) {
		return entityManager
				.createQuery("select t.employee from " + getObjectClass().getName()
						+ " t where onLeave = (:onLeave) and recordDate=(:recordDate)", Employee.class)
				.setParameter("recordDate", DateOperations.setZeroTime(recordDate)).setParameter("onLeave", true)
				.getResultList();
	}

	@Override
	public void setOnLeave(String json) {
		Boolean isOnLeave = new JSONObject(json).getBoolean("isOnLeave");
		setOnLeave(parseIds(json), parseDate(json), isOnLeave);

	}

	private void setOnLeave(List<Long> ids, Date recordDate, Boolean isOnLeave) {
		List<Timelog> timelogs = getAllTimeLogsForEmployeesForDate(ids, recordDate);
		List<Long> existingIds = new ArrayList<Long>();
		for (Timelog timelog : timelogs) {
			existingIds.add(timelog.getEmployee().getId());
			timelog.setOnLeave(isOnLeave);
			update(timelog);
		}
		ids.removeAll(existingIds);
		if (!ids.isEmpty()) {
			List<Employee> employees = employeeController.getAll(ids);
			for (Employee employee : employees) {
				Timelog timelog = new Timelog(recordDate, employee);
				timelog.setOnLeave(isOnLeave);
				update(timelog);
			}
		}
	}

	@Override
	public void login(Long employeeId) {
		Employee employee = employeeController.get(employeeId);
		Timelog timelog = new Timelog(DateOperations.setZeroTime(new Date()), employee);
		timelog.setLoginTime(new Date());
		add(timelog);
	}

	@Override
	public void logout(Long employeeId) {
		Timelog timelog = getLastLoggedInRecord(employeeId);
		if (timelog == null) {
			timelog = getForUserForDate(employeeId, new Date());
		}
		timelog.setLogoutTime(new Date());
		update(timelog);
	}

	@Override
	public List<Timelog> getAllForMonth(Date recordDate) {
		return entityManager
				.createQuery(
						"from " + getObjectClass().getName()
								+ " t where recordDate >=(:firstDayOfMonth) and recordDate <=(:lastDayOfMonth)",
						Timelog.class)
				.setParameter("firstDayOfMonth", DateOperations.getFirstDateOfMonth(recordDate))
				.setParameter("lastDayOfMonth", DateOperations.getLastDateOfMonth(recordDate)).getResultList();
	}

	@Override
	public List<Timelog> getAllForDate(Date recordDate) {
		return entityManager
				.createQuery("from " + getObjectClass().getName() + " t where recordDate=(:recordDate)", Timelog.class)
				.setParameter("recordDate", DateOperations.setZeroTime(recordDate)).getResultList();
	}

	@Override
	public Timelog getForUserForCurrentDate(Long employeeId) {
		return getForUserForDate(employeeId, new Date());
	}

	private Timelog getForUserForDate(Long employeeId, Date recordDate) {
		List<Timelog> timelogs = entityManager
				.createQuery("from " + getObjectClass().getName()
						+ " t where t.employee.id = (:employeeId) and recordDate=(:recordDate)", Timelog.class)
				.setParameter("recordDate", DateOperations.setZeroTime(recordDate))
				.setParameter("employeeId", employeeId).getResultList();
		return timelogs.isEmpty() ? null : timelogs.get(0);
	}

	@Override
	public Timelog getLastLoggedInRecord(Long employeeId) {
		List<Timelog> timelogs = entityManager
				.createQuery("from " + getObjectClass().getName()
						+ " t where t.employee.id = (:employeeId) and loggedIn=(:loggedIn)", Timelog.class)
				.setParameter("employeeId", employeeId).setParameter("loggedIn", true).getResultList();
		return timelogs.isEmpty() ? null : timelogs.get(0);
	}

	@Override
	public List<Timelog> getAllTimeLogsForEmployeesForMonth(String json) {
		return getAllTimeLogsForEmployeesForMonth(parseIds(json), parseDate(json));
	}

	@Override
	public List<Timelog> getAllTimeLogsForEmployeesForDate(String json) {
		return getAllTimeLogsForEmployeesForDate(parseIds(json), parseDate(json));
	}

	private Date parseDate(String json) {
		JSONObject obj = new JSONObject(json);
		try {
			return DateOperations.sqlDateFormat.parse(obj.getString("recordDate"));
		} catch (JSONException | ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	private List<Long> parseIds(String json) {
		JSONObject obj = new JSONObject(json);
		Gson gson = new Gson();
		Type listType = new TypeToken<List<Long>>() {
		}.getType();
		return gson.fromJson(obj.get("employeeIds").toString(), listType);
		
	/*	
		Type collectionType = new TypeToken<List<Long>>(){}.getType();
		List<channelSearchEnum> lcs = (List<Long>) new Gson()
		               .fromJson( obj.get("employeeIds").toString(), collectionType);*/
	}

	@Override
	public Timelog getForUserForDate(String json) {
		Long id = new JSONObject(json).getLong("employeeId");
		return getForUserForDate(id, parseDate(json));
	}
}
