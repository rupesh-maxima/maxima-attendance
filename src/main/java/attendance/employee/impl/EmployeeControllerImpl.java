package attendance.employee.impl;

import java.util.List;

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

	@Override
	public List<Employee> getAllActiveEmployees() {
		return entityManager.createQuery("from " + getObjectClass().getName() + " e where active=(:active)", Employee.class).setParameter("active", true).getResultList();
	}
}
