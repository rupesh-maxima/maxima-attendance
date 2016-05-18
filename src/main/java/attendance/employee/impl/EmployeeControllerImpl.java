package attendance.employee.impl;

import org.springframework.web.bind.annotation.RestController;

import attendance.common.impl.GenericControllerImpl;
import attendance.employee.controller.EmployeeController;
import attendance.employee.model.Employee;

@RestController
public class EmployeeControllerImpl extends GenericControllerImpl<Employee> implements EmployeeController {
	@Override
	public Class<Employee> getObjectClass() {
		return Employee.class;
	}
}

