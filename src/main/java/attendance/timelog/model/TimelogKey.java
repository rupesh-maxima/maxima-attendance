package attendance.timelog.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import attendance.employee.model.Employee;

@Embeddable
public class TimelogKey implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(columnDefinition = "date")
	private Date recordDate;
	@ManyToOne
	@JoinColumn(name = "employeeId")
	private Employee employee;

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
