package calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.crypto.Data;

public class CalendarDemo {
	public static CalendarDemo thiz;
	public static String NOW_BEFORE = "before";
	public static String NOW_AFTER = "after";

	public static CalendarDemo getInstance() {
		if (thiz == null)
			thiz = new CalendarDemo();
		return thiz;
	}

	public Date afterHour(int afterHour) {
		return CalendarDemo.getInstance().processTimeHour(NOW_AFTER, afterHour);
	}

	public Date beforeHour(int beforeHour) {
		return CalendarDemo.getInstance().processTimeHour(NOW_BEFORE, beforeHour);
	}

	public Date processTimeHour(String beforeOrAfter, int hoverCount) {
		Calendar calendar = Calendar.getInstance();

		if (beforeOrAfter.equals(NOW_BEFORE))
			calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - hoverCount);
		if (beforeOrAfter.equals(NOW_AFTER))
			calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hoverCount);

		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");

		System.out.println("一个小时前的时间 ： " + df.format(calendar.getTime()));

		System.out.println("当前的时间： " + df.format(new Date()));

		return calendar.getTime();
	}

	public Date afterMinute(long afterMinute) {
		return CalendarDemo.getInstance().processTimeMinute(NOW_AFTER, afterMinute);
	}

	public Date beforeMinute(long beforeMinute) {
		return CalendarDemo.getInstance().processTimeMinute(NOW_BEFORE, beforeMinute);
	}

	public Date processTimeMinute(String beforeOrAfter, long minuteCount) {
		long currentTime = System.currentTimeMillis();
		if (beforeOrAfter.equals(NOW_BEFORE))
			currentTime -= minuteCount * 1000 * 60;
		if (beforeOrAfter.equals(NOW_AFTER))
			currentTime += minuteCount * 1000 * 60;
		Date date = new Date(currentTime);

		return date;
	}

	public static void main(String[] args) throws ParseException {
		Date date1 = CalendarDemo.getInstance().afterMinute(10);
		Date date2 = CalendarDemo.getInstance().beforeMinute(10);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		
		
		
		
		
//		Date date3 = sdf.parse(sdf2.format(new Date()) + " "+"16:30");
		
//		Date date23 = sdf.format(new Date());
		
//		System.out.println(date1.getTime() < date3.getTime());
//		 System.out.println("date 1 = " + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date1));
//		 System.out.println("date 3 = " + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date3));
		// CalendarDemo.getInstance().afterTime();
		
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		Date d1 = new Date();
		Date d2 =formatter.parse("2017-09-24 15:44");
		
		System.out.println(d1.compareTo(d2));
		
	}
}
