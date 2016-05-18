package attendance.timelog.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import attendance.employee.model.Employee;

@Entity
public class Timelog implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long id;
	@Column(columnDefinition = "date")
	private Date recordDate;

	private Date loginTime;
	private Date logoutTime;
	private Boolean workFromHome;
	private Boolean loggedIn;
	private Boolean loggedOut;
	private Boolean absent;
	private Boolean onLeave;

	@ManyToOne
	@JoinColumn(name = "employeeId")
	private Employee employee;

	@Column(length = 150)
	private String comment;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Date getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}

	public Boolean getWorkFromHome() {
		return workFromHome;
	}

	public void setWorkFromHome(Boolean workFromHome) {
		this.workFromHome = workFromHome;
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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}