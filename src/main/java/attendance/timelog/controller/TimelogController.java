package attendance.timelog.controller;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import attendance.employee.model.Employee;
import attendance.timelog.model.Timelog;
import attendance.timelog.model.TimelogObject;

@RestController
public interface TimelogController {
	Timelog add(Timelog timelog);

	void update(Timelog existingObj);

	@RequestMapping(value = "/timelog/list", method=RequestMethod.POST)
	List<Timelog> list();

	@RequestMapping(value = "/timelog/search", method = RequestMethod.POST)
	List<Timelog> search(@RequestBody String json);

	@RequestMapping(value = "/timelog/setWorkfromHome", method = RequestMethod.POST)
	void setWorkfromHome(@RequestBody String json);
	
	@RequestMapping(value = "/timelog/getWorkfromHome", method = RequestMethod.POST)
	List<Employee> getWorkfromHome(@RequestBody Date recordDate);
	
	@RequestMapping(value = "/timelog/getOnLeave", method = RequestMethod.POST)
	List<Employee> getOnLeave(@RequestBody Date recordDate);
	
	@RequestMapping(value = "/timelog/setOnLeave", method = RequestMethod.POST)
	void setOnLeave(@RequestBody String json);
	
	@RequestMapping(value = "/timelog/login", method = RequestMethod.POST)
	TimelogObject login(@RequestBody Long employeeId);
	
	@RequestMapping(value = "/timelog/logout", method = RequestMethod.POST)
	TimelogObject logout(@RequestBody Long employeeId);
	
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
	Timelog getLastLoggedInRecord(@RequestBody Long employeeId);

	@RequestMapping(value = "/timelog/getTimelogsForToday", method = RequestMethod.POST)
	List<TimelogObject> getTimelogsForToday();

	@RequestMapping(value = "/timelog/getEmployeesWFHInProject", method = RequestMethod.POST)
	List<Employee> getEmployeesWFHInProject(@RequestBody String json);
}
