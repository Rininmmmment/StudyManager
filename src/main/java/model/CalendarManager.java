package model;

import java.util.Calendar;

public class CalendarManager {
	public static String getDayOfWeek(Calendar cal){
		String result = null;

		switch(cal.get(Calendar.DAY_OF_WEEK)) {
			case(Calendar.MONDAY):
				result = "(月)";
				break;
			case(Calendar.TUESDAY):
				result = "(火)";
				break;
			case(Calendar.WEDNESDAY):
				result = "(水)";
				break;
			case(Calendar.THURSDAY):
				result = "(木)";
				break;
			case(Calendar.FRIDAY):
				result = "(金)";
				break;
			case(Calendar.SATURDAY):
				result = "(土)";
			case(Calendar.SUNDAY):
				result = "(日)";
				break;
			default:
				 throw new IllegalStateException();	
		}
		return result;
	}
}
