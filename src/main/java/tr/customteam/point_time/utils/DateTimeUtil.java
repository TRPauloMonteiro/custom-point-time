package tr.customteam.point_time.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {
	
	public static boolean isCurrentTimeBetween(String startTime, String endTime, String minutesOfTolerance) throws ParseException {
		boolean isBetween = false;
		String reg =  "^([0-1][0-9]|2[0-3]):([0-5][0-9])$";
		System.out.println(startTime);
		if(startTime.matches(reg) && endTime.matches(reg)) {
			System.out.println("Matches");
			Date dt_startTime = new SimpleDateFormat("HH:mm").parse(startTime);
			Calendar startCalendar = Calendar.getInstance();
			startCalendar.setTime(dt_startTime);
			
			Date dt_endTime = new SimpleDateFormat("HH:mm").parse(endTime);
			Calendar endCalendar = Calendar.getInstance();
			endCalendar.setTime(dt_endTime);
			
			LocalDateTime currentTime = LocalDateTime.now();
			Date dt_currentTime = new SimpleDateFormat("HH:mm").parse(currentTime.getHour() + ":" + currentTime.getMinute());
			Calendar currentCalendar = Calendar.getInstance();
			currentCalendar.setTime(dt_currentTime);
			
			if(dt_currentTime.compareTo(dt_endTime) < 0) {
				currentCalendar.add(Calendar.DATE, 1);
				dt_currentTime = currentCalendar.getTime();
			}
			
			if(dt_startTime.compareTo(dt_endTime) < 0 || dt_startTime.compareTo(dt_endTime) == 0) {
				//startCalendar.add(Calendar.DATE, 1);
				int minutes = Integer.parseInt(minutesOfTolerance) * -1;
				System.out.println(minutes);
				startCalendar.add(Calendar.MINUTE, -10);
				dt_startTime = startCalendar.getTime();
			}
			
			if(dt_currentTime.before(dt_startTime)) {
				System.out.println("Current time is lesser than start Time");	
			}else {
				if(dt_currentTime.after(dt_endTime)) {
					//endCalendar.add(Calendar.DATE, 1);
					endCalendar.add(Calendar.MINUTE, Integer.parseInt(minutesOfTolerance));
					dt_endTime = endCalendar.getTime();
				}
				
				System.out.println("StartTime = " + dt_startTime);
				System.out.println("endTIme = " + dt_endTime);
				System.out.println("currentTime = " + dt_currentTime);
				
				if(dt_currentTime.before(dt_endTime)) {
					isBetween = true;
				}
			}
		}
		
		return isBetween;
	}
	
	public static boolean isValidReminderTime(String startTime, String endTime, String minutesOfTolerance) throws ParseException {
		boolean isValid = false;
		String reg =  "^([0-1][0-9]|2[0-3]):([0-5][0-9])$";
		
		if(startTime.matches(reg) && endTime.matches(reg)) {
			LocalTime sTime = LocalTime.parse(startTime).plusMinutes(-Integer.parseInt(minutesOfTolerance));
			LocalTime eTime = LocalTime.parse(endTime).plusMinutes(Integer.parseInt(minutesOfTolerance));;
			LocalTime cTime = LocalTime.now();
			
			System.out.println(sTime);
			System.out.println(eTime);
			System.out.println(cTime);
			
			if(cTime.isBefore(sTime)) {
				System.out.println("Current time is lesser than Start Time");
			}else {
				if(cTime.isAfter(eTime)) {
					System.out.println("Current time is greater than End Time");
				}else {
					isValid = true;
				}
			}
		}else {
			System.out.println("Not a valid time");
		}
		
		return isValid;
	}
}
