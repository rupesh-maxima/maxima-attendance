package attendance.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateOperations {

	public static SimpleDateFormat sqlDateFormat= new  SimpleDateFormat("MM/dd/yyyy");
	public static void main(String[] args){
//		long date=Date.parse("2/26/2014 12:00:00 AM");
//		System.out.println(date);
//		date=Date.parse(new Date().toLocaleString());
//		System.out.println(date);
//		List<String> lst = new ArrayList<String>();
//		lst.add("a");
//		lst.add("a");
//		lst.add("a");
//		lst.add("a");
//		for (final String string : lst) {
//			System.out.println(string);
//		}
		getFirstDateOfMonth(new Date());
		System.out.println(getFirstDateOfMonth(new Date()));
		System.out.println(getLastDateOfMonth(new Date()));
	}
	public static int getTodaysDay()
	{
		Date dt= new Date();
		SimpleDateFormat sdf= new SimpleDateFormat("dd");
		return Integer.parseInt(sdf.format(dt));
	}
	public static int getCurrentMonth()
	{
		Date dt= new Date();
		SimpleDateFormat sdf= new SimpleDateFormat("MM");
		return Integer.parseInt(sdf.format(dt));
	}
	public static int getCurrentYear()
	{
		Date dt= new Date();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy");
		return Integer.parseInt(sdf.format(dt));
	}
	public static Date addDays(int x)
	{
		Calendar cal= Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, x);
		return cal.getTime();
	}
	public static Date setDay(int x)
	{
		Date date= new Date();
		Calendar cal= Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, x);
		return cal.getTime();
	}
	public static Date setZeroTime(Date date){
		
		Calendar cal = Calendar.getInstance(); // locale-specific
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	public static Date getFirstDateOfMonth(Date date) {
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH,Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}
	public static Date getLastDateOfMonth(Date date) {
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH,Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}
}
