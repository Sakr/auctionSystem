/**
 * 
 */
package fr.auctionSystem.util;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * @author slimem
 * 
 */
public class Horloge{

	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private int second;
	private String currentDateHour;
	
	public Horloge() {
		TimeZone timeZone = TimeZone.getTimeZone("GMT");
		Calendar calendar = Calendar.getInstance(timeZone);
		this.year = calendar.get(Calendar.YEAR);
		this.month = calendar.get(Calendar.MONTH);
		this.day = calendar.get(Calendar.DAY_OF_MONTH);
		this.hour = calendar.get(Calendar.HOUR_OF_DAY);
		this.minute = calendar.get(Calendar.MINUTE);
		this.second = calendar.get(Calendar.SECOND);
		//Le current date hour est une espece de clé fabriqué qui peux servir pour identifier l'objet dans le comparator
		this.currentDateHour=day+"/"+month+"/"+year+"-"+hour+":"+minute+":"+second;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(int month) {
		this.month = month;
	}

	/**
	 * @return the day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(int day) {
		this.day = day;
	}

	/**
	 * @return the hour
	 */
	public int getHour() {
		return hour;
	}

	/**
	 * @param hour the hour to set
	 */
	public void setHour(int hour) {
		this.hour = hour;
	}

	/**
	 * @return the minute
	 */
	public int getMinute() {
		return minute;
	}

	/**
	 * @param minute the minute to set
	 */
	public void setMinute(int minute) {
		this.minute = minute;
	}

	/**
	 * @return the second
	 */
	public int getSecond() {
		return second;
	}

	/**
	 * @param second the second to set
	 */
	public void setSecond(int second) {
		this.second = second;
	}

	/**
	 * @return the currentDateHour
	 */
	public String getCurrentDateHour() {
		return currentDateHour;
	}

	/**
	 * @param currentDateHour the currentDateHour to set
	 */
	public void setCurrentDateHour(String currentDateHour) {
		this.currentDateHour = currentDateHour;
	}
	

}