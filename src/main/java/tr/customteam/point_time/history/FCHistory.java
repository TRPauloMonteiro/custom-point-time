package tr.customteam.point_time.history;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;

public class FCHistory {
	private LocalDate createdDate;
	
	private HashMap<String, Integer> remindersStatus;
	private HashMap<Integer, LocalTime> appointments;
	
	public LocalDate getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	
	public void setReminderStatus(String reminder, int status) {
		remindersStatus.putIfAbsent(reminder, status);
	}
	
	public int getReminderStatus(String reminder) {
		if(remindersStatus.containsKey(reminder)) {
			return remindersStatus.get(reminder);
		}else {
			return -1;
		}
	}
	
	public void setAppointment(int count, LocalTime appointmentDate) {
		appointments.putIfAbsent(count, appointmentDate);
	}
	
	public void setAppointments(HashMap<Integer, LocalTime> appointments) {
		this.appointments = appointments;
	}
	
	public void getAppointment(int count) {
		appointments.get(count);
	}
	
	public int getQtdAppointments() {
		if(appointments != null) {
			return appointments.size();
		}else {
			return 0;
		}
		
	}
}
