package attendance.timelog.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import attendance.employee.model.Employee;

@Entity
@IdClass(TimelogKey.class)
public class Timelog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Date recordDate;
	@Id
	private Employee employee;
	
	private Date loginTime;
	private Date logoutTime;
	private Boolean workFromHome = false;
	private Boolean loggedIn = false;
	private Boolean loggedOut = false;
	private Boolean absent = false;
	private Boolean onLeave = false;

	@Column(length = 150)
	private String comment;

	public Timelog() {
	}

	public Timelog(Date recordDate, Employee employee, Boolean workFromHome) {
		super();
		this.recordDate = recordDate;
		this.employee = employee;
		setWorkFromHome(workFromHome);
	}

	public Timelog(Date recordDate, Employee employee) {
		super();
		this.recordDate = recordDate;
		this.employee = employee;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
		this.loggedIn = true;
		this.loggedOut = false;
		this.absent = false;
		this.workFromHome = false;
		this.onLeave = false;
	}

	public Date getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
		this.loggedIn = false;
		this.loggedOut = true;
		this.absent = false;
		this.workFromHome = false;
		this.onLeave = false;
	}

	public Boolean getWorkFromHome() {
		return workFromHome;
	}

	public void setWorkFromHome(Boolean workFromHome) {
		this.workFromHome = workFromHome;
		if (workFromHome != null && workFromHome) {
			this.loggedIn = false;
			this.loggedOut = false;
			this.absent = false;
			this.onLeave = false;
		}
		setComment(workFromHome ? "Working from home" : getComment());
	}

	public Boolean getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public Boolean getLoggedOut() {
		return loggedOut;
	}

	public void setLoggedOut(Boolean loggedOut) {
		this.loggedOut = loggedOut;
	}

	public Boolean getAbsent() {
		return absent;
	}

	public void setAbsent(Boolean absent) {
		this.absent = absent;
	}

	public Boolean getOnLeave() {
		return onLeave;
	}

	public void setOnLeave(Boolean onLeave) {
		this.onLeave = onLeave;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

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