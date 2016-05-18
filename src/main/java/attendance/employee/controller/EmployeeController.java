package attendance.employee.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import attendance.employee.model.Employee;

@RestController
public interface EmployeeController {
	@RequestMapping(value = "/employee/add", method = RequestMethod.POST)
	Employee add(@RequestBody Employee employee);

	@RequestMapping(value = "/employee/update", method = RequestMethod.POST)
	void update(@RequestBody Employee existingObj);

	@RequestMapping(value = "/employee/get", method = { RequestMethod.POST })
	Employee get(@RequestBody Long id);

	@RequestMapping(value = "/employee/getAll", method = RequestMethod.POST)
	List<Employee> getAll(@RequestBody List<Long> ids);

	@RequestMapping(value = "/employee/list", method = RequestMethod.POST)
	List<Employee> list();

	@RequestMapping(value = "/employee/search", method = RequestMethod.POST)
	List<Employee> search(@RequestBody String json);

	@RequestMapping(value = "/employee/delete", method = RequestMethod.POST)
	void delete(@RequestBody Long id);

	@RequestMapping(value = "/employee/deleteAll", method = RequestMethod.POST)
	void deleteAll(@RequestBody List<Long> ids);
}
