package patchi.silk.foundation;

import java.util.HashSet;
import java.util.Set;

/**
 *	Tracks time. Capable of time math.
 */

public class Time {
		
	/** Month names. */
	private static final String[] MONTHS_LIST = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
	
	/** Set of months with 30 days. */
	private static Set<Integer> THIRTY_DAYS = new HashSet<Integer>();
		
	/** Current year. */
	private int year;
	
	/** Current month, starting at 0. */
	private int month;
	
	/** Current day of the month, starting at 0. */
	private int day;
	
	/**  Current hour, where 0 is 00:xx. */
	private int hour;
	
	/** The current minute, where 0 is xx:00. */
	private int minute;
	
	/**  Number of days since new year. */
	private int daysCount;
	
	/**
	 * Initialises a time object.
	 */
	public Time() {
		
		year = 700;
		month = 8;
		day = 21;
		
		hour = 7;
		minute = 0;
		
		THIRTY_DAYS.add(3);
		THIRTY_DAYS.add(5);
		THIRTY_DAYS.add(8);
		THIRTY_DAYS.add(10);
		
		daysCount = 264;
		
	}
	
	public Time(String in) {
		
		String[] data = in.split(",");
		year = Integer.parseInt(data[0]);
		month = Integer.parseInt(data[1]);
		day = Integer.parseInt(data[2]);
		hour = Integer.parseInt(data[3]);
		minute = Integer.parseInt(data[4]);
		daysCount = Integer.parseInt(data[5]);
		
		THIRTY_DAYS.add(3);
		THIRTY_DAYS.add(5);
		THIRTY_DAYS.add(8);
		THIRTY_DAYS.add(10);
		
	}
	
	/**
	 * Advances the time by one hour.<br>
	 * Handles any necessary changes to the day, month, or year.
	 */
	public int advanceHour() {
		
		/**
		 * Status Codes:
		 * 0 NONE
		 * 1 HOUR
		 * 2 DAY
		 * 3 MONTH
		 * 4 YEAR
		 */
		int status = 0;
		
		//Advance to nearest hour
		hour++;
		minute = 0;
		
		//advance day if appropriate
		if(getHour() > 23) {
			
			hour = 0;
			day ++;
			daysCount ++;
			status = 2;
			
			//Advance month if appropriate
			if(month == 1 && day > 27) {
				month ++;
				day = 0;
				status = 3;
			}
			else if(THIRTY_DAYS.contains(month) && day > 29) {
				month ++;
				day = 0;
				status = 3;
			}
			else if(day > 30){
				month++;
				day = 0;
				status = 3;
			}
			
			//Advance year if appropriate
			if(month > 11) {
				year++;
				month = 0;
				daysCount = 0;
				status = 4;
			}
			
		}
		
		return status;
		
	}
	
	/**
	 * Returns the current year.
	 *
	 * @return Current year
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * Returns the id of the current month.
	 *
	 * @return Current month
	 */
	public int getMonthByID() {
		return month;
	}
	
	/**
	 * Returns the current day.
	 *
	 * @return Current day
	 */
	public int getDay() {
		return day;
	}
	
	/**
	 * Returns the current hour.
	 *
	 * @return Current hour
	 */
	public int getHour() {
		return hour;
	}
		
	/**
	 * Returns the current minute.
	 *
	 * @return Current minute
	 */
	public int getMinute() {
		return minute;
	}	
		
	/**
	 * Returns the name of the current month.
	 *
	 * @return Current month
	 */
	public String getMonthByName() {
		return MONTHS_LIST[month];
	}
	
	/**
	 * Returns a formatted date.
	 *
	 * @return Formatted date
	 */
	public String getFormattedDate() {
		return (day+1) + " " + MONTHS_LIST[month] + ", " + year;
	}
	
	/**
	 * Returns the formatted time.
	 *
	 * @return Formatted time
	 */
	public String getFormattedTime() {
		
		if(hour == 0){
			return "12:" + String.format("%02d", minute) + " am";
		}
		else if(hour == 12){
			return "12:" + String.format("%02d", minute) + " pm";
		}
		else if(hour < 12){
			return String.format("%02d", hour) + ":" + String.format("%02d", minute) + " am";
		}
		else {
			return String.format("%02d", hour-12) + ":" + String.format("%02d", minute) + " pm";
		}
	}
		
	@Override
	public String toString() {
		return year +"," + month + "," + day + "," + hour + "," + minute + ","+ daysCount;
	}
	
	/**
	 * Returns the value of daysCount.
	 *
	 * @return daysCount
	 */
	public int getCurrentDayCount() {
		return daysCount;
	}
	
	/**
	 * Returns the sunrise time in decimal hours.
	 *
	 * @return Sunrise time
	 */
	public double getSunriseTime() {
		return 2.0 * Math.cos((Math.PI * (double) daysCount)/182.0 + (5.0 * Math.PI)/91.0) + 6.0;
	}

	public double getSunsetTime() {
		return 18.0 - 2.0 * Math.cos((Math.PI * daysCount) / 182.0 + (5.0 * Math.PI) / 91.0);
	}
	
	public double getCurrentDayLength() {
		return 12.0 - 4.0 * Math.cos((Math.PI * (double) daysCount) / 182.0 + (5.0 * Math.PI) / 91.0);
	}
		
}
