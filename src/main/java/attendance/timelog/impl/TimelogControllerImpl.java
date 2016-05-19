package attendance.timelog.impl;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import attendance.common.impl.GenericControllerImpl;
import attendance.employee.model.Employee;
import attendance.timelog.controller.TimelogController;
import attendance.timelog.model.Timelog;



@RestController
public class TimelogControllerImpl extends GenericControllerImpl<Timelog> implements TimelogController {
	@Override
	public Class<Timelog> getObjectClass() {
		return Timelog.class;
	}

	@Override
	public void setWorkfromHome(List<Long> employeeIds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Employee> getWorkfromHome(Date recordDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> getOnLeave(Date recordDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setOnLeave(List<Long> employeeIds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void login(Long employeeId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logout(Long employeeId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Timelog> getAllForMonth(Date recordDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Timelog> getAllForDate(Date recordDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Timelog getForUserForCurrentDate(Long employeeId) {
		// TODO Auto-generated method stub
		return null;
	}
}
