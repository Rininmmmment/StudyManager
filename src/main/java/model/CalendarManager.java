package model;

import java.util.Calendar;

public class CalendarManager {
	public static String getDayOfWeek(Calendar cal){
		String result = null;

		switch(cal.get(Calendar.DAY_OF_WEEK)) {
			case(Calendar.MONDAY):
				result = "月曜日";
				break;
			case(Calendar.TUESDAY):
				result = "火曜日";
				break;
			case(Calendar.WEDNESDAY):
				result = "水曜日";
				break;
			case(Calendar.THURSDAY):
				result = "木曜日";
				break;
			case(Calendar.FRIDAY):
				result = "金曜日";
				break;
			case(Calendar.SATURDAY):
				result = "土曜日";
			case(Calendar.SUNDAY):
				result = "日曜日";
				break;
			default:
				 throw new IllegalStateException();	
		}
		return result;
	}
}
