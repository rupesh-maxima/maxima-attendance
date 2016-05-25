package attendance.timelog.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import attendance.employee.model.Employee;

public class TimelogObject {

	public final static SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy MMM, dd HH:mm");
	private Long employeeId;
	private String firstName;
	private String lastName;
	private Boolean workFromHome = false;
	private Boolean loggedIn = false;
	private Boolean loggedOut = false;
	private Boolean absent = true;
	private Boolean onLeave = false;
	private String[] status = new String[2];

	public TimelogObject(Employee employee, Timelog timelog) {
		setEmployee(employee);
		setTimLog(timelog);
	}

	private void setEmployee(Employee employee) {
		setEmployeeId(employee.getId());
		setFirstName(employee.getFirstName());
		setLastName(employee.getLastName());
	}

	private void setTimLog(Timelog timelog) {
		if (timelog == null) {
			setAbsent(true);
		} else {
			setLoggedIn(timelog.getLoggedIn(), timelog.getLoginTime());
			setLoggedOut(timelog.getLoggedOut(), timelog.getLogoutTime());
			setOnLeave(timelog.getOnLeave());
			setAbsent(timelog.getAbsent());
			setWorkFromHome(timelog.getWorkFromHome());
		}
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Boolean getWorkFromHome() {
		return workFromHome;
	}

	public void setWorkFromHome(Boolean workFromHome) {
		this.workFromHome = workFromHome;
		if (workFromHome != null && workFromHome) {
			this.absent = false;
			this.loggedIn = false;
			this.loggedOut = false;
			this.onLeave = false;
			this.status[0] = "is working from home today";
		}
	}

	public Boolean getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(Boolean loggedIn, Date time) {
		this.loggedIn = loggedIn;
		if (loggedIn != null && loggedIn) {
			this.absent = false;
			this.workFromHome = false;
			this.loggedOut = false;
			this.onLeave = false;
			this.status[0] = "is logged in at " + timeFormat.format(time);
		}
	}

	public Boolean getLoggedOut() {
		return loggedOut;
	}

	public void setLoggedOut(Boolean loggedOut, Date time) {
		this.loggedOut = loggedOut;
		if (loggedOut != null && loggedOut) {
			this.absent = false;
			this.workFromHome = false;
			this.loggedIn = false;
			this.onLeave = false;
			this.status[1] = "is logged out at " + timeFormat.format(time);
		}
	}

	public Boolean getAbsent() {
		return absent;
	}

	public void setAbsent(Boolean absent) {
		this.absent = absent;
		if (absent != null && absent) {
			this.loggedOut = false;
			this.workFromHome = false;
			this.loggedIn = false;
			this.onLeave = false;
			this.status[0] = "is not yet logged in";
		}
	}

	public Boolean getOnLeave() {
		return onLeave;
	}

	public void setOnLeave(Boolean onLeave) {
		this.onLeave = onLeave;
		if (onLeave != null && onLeave) {
			this.loggedOut = false;
			this.workFromHome = false;
			this.loggedIn = false;
			this.absent = false;
			this.status[0] = "is on leave today";
		}
	}

	public String[] getStatus() {
		return status;
	}

	public void setStatus(String[] status) {
		this.status = status;
	}
}