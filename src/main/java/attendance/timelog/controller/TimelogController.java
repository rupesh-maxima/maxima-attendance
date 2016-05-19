package attendance.timelog.controller;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import attendance.employee.model.Employee;
import attendance.timelog.model.Timelog;

@RestController
public interface TimelogController {
	@RequestMapping(value = "/timelog/add", method = RequestMethod.POST)
	Timelog add(@RequestBody Timelog timelog);

	@RequestMapping(value = "/timelog/update", method = RequestMethod.POST)
	void update(@RequestBody Timelog existingObj);

	@RequestMapping(value = "/timelog/get"  ,method = { RequestMethod.POST })
	Timelog get(@RequestBody Long id);

	@RequestMapping(value = "/timelog/getAll", method = RequestMethod.POST)
	List<Timelog> getAll(@RequestBody List<Long> ids);

	@RequestMapping(value = "/timelog/list", method=RequestMethod.POST)
	List<Timelog> list();

	@RequestMapping(value = "/timelog/search", method = RequestMethod.POST)
	List<Timelog> search(@RequestBody String json);

	@RequestMapping(value = "/timelog/delete", method = RequestMethod.POST)
	void delete(@RequestBody Long id);

	@RequestMapping(value = "/timelog/deleteAll", method = RequestMethod.POST)
	void deleteAll(@RequestBody List<Long> ids);
	
	@RequestMapping(value = "/timelog/setWorkfromHome", method = RequestMethod.POST)
	void setWorkfromHome(@RequestBody String json);
	
	@RequestMapping(value = "/timelog/getWorkfromHome", method = RequestMethod.POST)
	List<Employee> getWorkfromHome(@RequestBody Date recordDate);
	
	@RequestMapping(value = "/timelog/getOnLeave", method = RequestMethod.POST)
	List<Employee> getOnLeave(@RequestBody Date recordDate);
	
	@RequestMapping(value = "/timelog/setOnLeave", method = RequestMethod.POST)
	void setOnLeave(@RequestBody String json);
	
	@RequestMapping(value = "/timelog/login", method = RequestMethod.POST)
	void login(@RequestBody Long employeeId);
	
	@RequestMapping(value = "/timelog/logout", method = RequestMethod.POST)
	void logout(@RequestBody Long employeeId);
	
	@RequestMapping(value = "/timelog/getAllForMonth", method = RequestMethod.POST)
	List<Timelog> getAllForMonth(@RequestBody Date recordDate);
	
	@RequestMapping(value = "/timelog/getAllForDate", method = RequestMethod.POST)
	List<Timelog> getAllForDate(@RequestBody Date recordDate);
	
	@RequestMapping(value = "/timelog/getForUserForCurrentDate", method = RequestMethod.POST)
	Timelog getForUserForCurrentDate(@RequestBody Long employeeId);

	@RequestMapping(value = "/timelog/getAllTimeLogsForEmployeesForMonth", method = RequestMethod.POST)
	List<Timelog> getAllTimeLogsForEmployeesForMonth(@RequestBody String json);

	@RequestMapping(value = "/timelog/getAllTimeLogsForEmployeesForDate", method = RequestMethod.POST)
	List<Timelog> getAllTimeLogsForEmployeesForDate(@RequestBody String json);

	@RequestMapping(value = "/timelog/getForUserForDate", method = RequestMethod.POST)
	Timelog getForUserForDate(@RequestBody String json);

	@RequestMapping(value = "/timelog/getLastLoggedInRecord", method = RequestMethod.POST)
	Timelog getLastLoggedInRecord(Long employeeId);
}
