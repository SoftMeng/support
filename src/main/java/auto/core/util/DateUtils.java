package auto.core.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 〈日期转换的公共方法〉
 * 〈张维维提供〉
 * @author mengxy[孟祥元]
 * @version 2015年12月16日
 * @see DateUtils
 * @since 1.0
 */
public class DateUtils {
	private static final SimpleDateFormat	SDF			= new SimpleDateFormat();
	public static final String				DATE		= "yyyy-MM-dd";
	public static final String				TIME		= "HH:mm:ss";
	public static final String				TIME_HHMM	= "HHmm";
	public static final String				TIME_HHMM2	= "HH:mm";
	public static final String				DATETIME	= "yyyy-MM-dd HH:mm:ss";

	/**
	 * Description: 上个月的最后一天<br>
	 * 1、…<br>
	 * 2、…<br>
	 * Implement: <br>
	 * 1、…<br>
	 * 2、…<br>
	 * 
	 * @return 
	 * @see 
	 */
	public static Date lastMonthLastDay() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		c.set(Calendar.DATE, 1);
		c.roll(Calendar.DATE, -1);
		return c.getTime();
	}

	/**
	 * Description: 上个月的第一天<br>
	 * 1、…<br>
	 * 2、…<br>
	 * Implement: <br>
	 * 1、…<br>
	 * 2、…<br>
	 * 
	 * @return 
	 * @see 
	 */
	public static Date lastMonthFirstDay() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		c.set(Calendar.DATE, 1);
		return c.getTime();
	}

	/**
	 * Description:格式化：上月最后一天 <br>
	 * 1、…<br>
	 * 2、…<br>
	 * Implement: <br>
	 * 1、…<br>
	 * 2、…<br>
	 * 
	 * @param pattern
	 * @return 
	 * @see 
	 */
	public static String lastMonthLastDay(String pattern) {
		SDF.applyPattern(pattern);
		return SDF.format(lastMonthLastDay());
	}

	/**
	 * Description: 格式化：上月第一天<br>
	 * 1、…<br>
	 * 2、…<br>
	 * Implement: <br>
	 * 1、…<br>
	 * 2、…<br>
	 * 
	 * @param pattern
	 * @return 
	 * @see 
	 */
	public static String lastMonthFirstDay(String pattern) {
		SDF.applyPattern(pattern);
		return SDF.format(lastMonthFirstDay());
	}

	/**
	 * Description:将日期字符串转换日期类型 <br>
	 * 1、…<br>
	 * 2、…<br>
	 * Implement: <br>
	 * 1、…<br>
	 * 2、…<br>
	 * 
	 * @param str
	 * @param pattern
	 * @return 
	 * @see 
	 */
	public static Date parseDate(String str, String pattern) {
		try {
			SDF.applyPattern(pattern);
			return SDF.parse(str);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Description: 对日期的月份按数字相减<br>
	 * 1、…<br>
	 * 2、…<br>
	 * Implement: <br>
	 * 1、…<br>
	 * 2、…<br>
	 * 
	 * @param date
	 * @param amount
	 * @return 
	 * @see 
	 */
	public static Date subtractMonth(Date date, int amount) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, -amount);// 减月
		return c.getTime();
	}

	/**
	 * Description:格式化：对日期的月份按数字相减 <br>
	 * 1、…<br>
	 * 2、…<br>
	 * Implement: <br>
	 * 1、…<br>
	 * 2、…<br>
	 * 
	 * @param str
	 * @param amount
	 * @param pattern
	 * @return 
	 * @see 
	 */
	public static String subtractMonth(String str, int amount, String pattern) {
		Date d = parseDate(str, pattern);
		d = subtractMonth(d, amount);
		return SDF.format(d);
	}

	/**
	 * Description: 按默认格式返回当前系统时间<br>
	 * 1、…<br>
	 * 2、…<br>
	 * Implement: <br>
	 * 1、…<br>
	 * 2、…<br>
	 * 
	 * @return 
	 * @see 
	 */
	public static String currentDate() {
		Date date = new Date();
		SDF.applyPattern(DATETIME);
		return SDF.format(date);
	}

	/**
	 * Description: 按格式将日期转成字符串<br>
	 * 1、…<br>
	 * 2、…<br>
	 * Implement: <br>
	 * 1、…<br>
	 * 2、…<br>
	 * 
	 * @param date
	 * @param pattern
	 * @return 
	 * @see 
	 */
	public static String formatDate(Date date, String pattern) {
		SDF.applyPattern(pattern);
		return SDF.format(date);
	}

	/**
	 * Description: 返回一周中的第几天<br>
	 * 1、…<br>
	 * 2、…<br>
	 * Implement: <br>
	 * 1、…<br>
	 * 2、…<br>
	 * 
	 * @param date
	 * @return 
	 * @see 
	 */
	public static int getDayOfWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * Description:返回月份中的第几天 <br>
	 * 1、…<br>
	 * 2、…<br>
	 * Implement: <br>
	 * 1、…<br>
	 * 2、…<br>
	 * 
	 * @param date
	 * @return 
	 * @see 
	 */
	public static int getDayOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}
}
