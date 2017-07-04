package com.zhzx.uip.commons.utils;

import org.apache.commons.lang.time.DateUtils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class DateUtil {
	/** 日期格式(yyyy-MM-dd) */
	public static final String yyyy_MM_dd_EN = "yyyy-MM-dd";

	/** 日期格式(yyyy-MM-dd HH:mm) */
	public static final String yyyy_MM_dd_HH_mm_EN = "yyyy-MM-dd HH:mm";

	/** 日期格式(yyyyMMdd) */
	public static final String yyyyMMdd_EN = "yyyyMMdd";

	/** 日期格式(yyyy-MM) */
	public static final String yyyy_MM_EN = "yyyy-MM";

	/** 日期格式(yyyyMM) */
	public static final String yyyyMM_EN = "yyyyMM";

	/** 日期格式(yyyy-MM-dd HH:mm:ss) */
	public static final String yyyy_MM_dd_HH_mm_ss_EN = "yyyy-MM-dd HH:mm:ss";

	/** 日期格式(yyyyMMddHHmmss) */
	public static final String yyyyMMddHHmmss_EN = "yyyyMMddHHmmss";

	/** 日期格式(yyyy年MM月dd日) */
	public static final String yyyy_MM_dd_CN = "yyyy年MM月dd日";

	/** 日期格式(yyyy年MM月dd日HH时mm分ss秒) */
	public static final String yyyy_MM_dd_HH_mm_ss_CN = "yyyy年MM月dd日HH时mm分ss秒";

	/** 日期格式(yyyy年MM月dd日HH时mm分) */
	public static final String yyyy_MM_dd_HH_mm_CN = "yyyy年MM月dd日HH时mm分";

	/** DateFormat缓存 */
	private static Map<String, DateFormat> dateFormatMap = new HashMap<String, DateFormat>();

	/**
	 * 获取DateFormat
	 * 
	 * @param formatStr
	 * @param formatStr
	 * @return
	 */
	public static DateFormat getDateFormat(String formatStr) {
		DateFormat df = dateFormatMap.get(formatStr);
		if (df == null) {
			df = new SimpleDateFormat(formatStr);
			dateFormatMap.put(formatStr, df);
		}
		return df;
	}
	
	public static String addHour(Date date,Integer hours) {
		if (null == date)
			return null;
		
		Calendar cal = Calendar.getInstance();   
        cal.setTime(date);   
        cal.add(Calendar.HOUR, hours);// 24小时制   
        date = cal.getTime(); 
   
        return dateToDateString(date);
	}

	public static boolean isFormatDate(String dateTimeStr, String formatStr) {
		try {
			convertDate(dateTimeStr,formatStr);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 转化dateTimeStr为Date类型
	 *
	 * @param dateTimeStr
	 * @param formatStr
	 * @return
	 */
	public static Date convertDate(String dateTimeStr, String formatStr) {
		try {
			if (dateTimeStr == null || dateTimeStr.equals("")) {
				return null;
			}
			DateFormat sdf = DateUtil.getDateFormat(formatStr);
			Date d = sdf.parse(dateTimeStr);
			return d;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 日期转换成字符串yyyy-MM-dd
	 * 
	 * @param date
	 * @return str
	 */

	public static String DateToStr(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(yyyy_MM_dd_EN);
		if (date != null) {
			String str = format.format(date);
			return str;
		} else {
			return null;
		}

	}

	/**
	 * 按照默认formatStr的格式，转化dateTimeStr为Date类型 dateTimeStr必须是formatStr的形式
	 * 
	 * @param dateTimeStr
	 * @param formatStr
	 * @return
	 */
	public static Date getDate(String dateTimeStr, String formatStr) {
		try {
			if (dateTimeStr == null || dateTimeStr.equals("")) {
				return null;
			}
			DateFormat sdf = DateUtil.getDateFormat(formatStr);
			Date d = sdf.parse(dateTimeStr);
			return d;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 转化dateTimeStr为Date类型
	 * 
	 * @param dateTimeStr
	 * @param dateTimeStr
	 * @return
	 */
	public static Date convertDate(String dateTimeStr) {
		try {
			if (dateTimeStr == null || dateTimeStr.equals("")) {
				return null;
			}
			DateFormat sdf = DateUtil.getDateFormat(yyyy_MM_dd_HH_mm_ss_EN);
			Date d = sdf.parse(dateTimeStr);
			return d;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 按照默认显示日期时间的格式"yyyy-MM-dd"，转化dateTimeStr为Date类型
	 * dateTimeStr必须是"yyyy-MM-dd"的形式
	 * 
	 * @param dateTimeStr
	 * @return
	 */
	public static Date getDate(String dateTimeStr) {
		return getDate(dateTimeStr, yyyy_MM_dd_EN);
	}

	/**
	 * 将YYYYMMDD转换成Date日期
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static Date transferDate(String date) throws Exception {
		if (date == null || date.length() < 1)
			return null;

		if (date.length() != 8)
			throw new Exception("日期格式错误");
		String con = "-";

		String yyyy = date.substring(0, 4);
		String mm = date.substring(4, 6);
		String dd = date.substring(6, 8);

		int month = Integer.parseInt(mm);
		int day = Integer.parseInt(dd);
		if (month < 1 || month > 12 || day < 1 || day > 31)
			throw new Exception("日期格式错误");

		String str = yyyy + con + mm + con + dd;
		return DateUtil.getDate(str, DateUtil.yyyy_MM_dd_EN);
	}

	/**
	 * 将Date转换成字符串“yyyy-mm-dd hh:mm:ss”的字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToDateString(Date date) {
		return dateToDateString(date, yyyy_MM_dd_HH_mm_ss_EN);
	}

	/**
	 * 将Date转换成formatStr格式的字符串
	 * 
	 * @param date
	 * @param formatStr
	 * @return
	 */
	public static String dateToDateString(Date date, String formatStr) {
		DateFormat df = getDateFormat(formatStr);
		return df.format(date);
	}

	/**
	 * 将String转换成formatStr格式的字符串
	 * 
	 * @param date
	 * @param formatStr1
	 * @param formatStr2
	 * @return
	 */
	public static String stringToDateString(String date, String formatStr1, String formatStr2) {
		Date d = getDate(date, formatStr1);
		DateFormat df = getDateFormat(formatStr2);

		return df.format(d);
	}

	/**
	 * 获取当前日期yyyy-MM-dd的形式
	 * 
	 * @return
	 */
	public static String getCurDateStr() {
		return dateToDateString(new Date(), yyyy_MM_dd_EN);
	}
	
	public static Date getCurDate() {
		return new Date();
	}

	/**
	 * 获取当前日期yyyy年MM月dd日的形式
	 * 
	 * @return
	 */
	public static String getCurCNDateStr() {
		return dateToDateString(new Date(), yyyy_MM_dd_CN);
	}

	/**
	 * 获取当前日期时间yyyy-MM-dd HH:mm:ss的形式
	 * 
	 * @return
	 */
	public static String getCurDateTimeStr() {
		return dateToDateString(new Date(), yyyy_MM_dd_HH_mm_ss_EN);
	}

	/**
	 * 获取当前日期时间yyyy年MM月dd日HH时mm分ss秒的形式
	 * 
	 * @return
	 */
	public static String getCurZhCNDateTimeStr() {
		return dateToDateString(new Date(), yyyy_MM_dd_HH_mm_ss_CN);
	}

	/**
	 * 比较两个"yyyy-MM-dd"格式的日期，之间相差多少毫秒,time2-time1
	 * 
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static long compareDateStr(String time1, String time2) {
		Date d1 = getDate(time1);
		Date d2 = getDate(time2);
		return d2.getTime() - d1.getTime();
	}

	/**
	 * 比较两个"yyyy-MM-dd"格式的日期，之间相差多少毫秒,time2-time1
	 *
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static long compareTimeStr(String time1, String time2) {
		Date d1 = convertDate(time1);
		Date d2 = convertDate(time2);
		return d2.getTime() - d1.getTime();
	}

	/**
	 * 比较两个"yyyy-MM-dd HH:mm:ss"格式的日期的大小
	 * 
	 * @param time1
	 * @param time2
	 * @return boolean true第一个时间大
	 */
	public static boolean compareDateStrTime(String time1, String time2) {
		boolean b = false;
		Date d1 = getDate(time1, yyyy_MM_dd_HH_mm_ss_EN);
		Date d2 = getDate(time2, yyyy_MM_dd_HH_mm_ss_EN);
		long temp = d1.getTime() - d2.getTime();
		if (temp >= 0) {
			b = true;
		}
		return b;
	}
	
	/**
	 * 比较两个日期(相对1970年1月1日的毫秒数)的大小
	 * 
	 * @param time1Str
	 * @param time2Str
	 * @return boolean true第一个时间大
	 */
	public static boolean compareDateTime(String time1Str, String time2Str) {
		boolean b = false;
		Long time1 = Long.valueOf(time1Str);
		Long time2 = Long.valueOf(time2Str);
		long temp = time1 - time2;
		if (temp >= 0) {
			b = true;
		}
		return b;
	}

	/**
	 * 将小时数换算成返回以毫秒为单位的时间
	 * 
	 * @param hours
	 * @return
	 */
	public static long getMicroSec(BigDecimal hours) {
		BigDecimal bd;
		bd = hours.multiply(new BigDecimal(3600 * 1000));
		return bd.longValue();
	}

	/**
	 * 获取当前日期years年后的一个(formatStr)的字符串
	 * 
	 * @param years
	 * @param formatStr
	 * @return
	 */
	public static String getDateStringOfYear(int years, String formatStr) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(new Date());
		now.add(Calendar.YEAR, years);
		return dateToDateString(now.getTime(), formatStr);
	}

	/**
	 * 获取当前日期mon月后的一个(formatStr)的字符串
	 * 
	 * @param months
	 * @param formatStr
	 * @return
	 */
	public static String getDateStringOfMon(int months, String formatStr) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(new Date());
		now.add(Calendar.MONTH, months);
		return dateToDateString(now.getTime(), formatStr);
	}
	
	public static Date getDateOfMon(int months) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(new Date());
		now.add(Calendar.MONTH, months);
		return now.getTime();
	}

	/**
	 * 获取当前日期days天后的一个(formatStr)的字符串
	 * 
	 * @param days
	 * @param formatStr
	 * @return
	 */
	public static String getDateStringOfDay(int days, String formatStr) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(new Date());
		now.add(Calendar.DATE, days);
		return dateToDateString(now.getTime(), formatStr);
	}

	/**
	 * 获取当前日期hours小时后的一个(formatStr)的字符串
	 * 
	 * @param hours
	 * @param formatStr
	 * @return
	 */
	public static String getDateStringOfHour(int hours, String formatStr) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(new Date());
		now.add(Calendar.HOUR_OF_DAY, hours);
		return dateToDateString(now.getTime(), formatStr);
	}

	/**
	 * 获取指定日期mon月后的一个(formatStr)的字符串
	 * 
	 * @param date
	 * @param mon
	 * @param formatStr
	 * @return
	 */
	public static String getDateOfMon(String date, int mon, String formatStr) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(DateUtil.getDate(date, formatStr));
		now.add(Calendar.MONTH, mon);
		return dateToDateString(now.getTime(), formatStr);
	}

	/**
	 * 获取指定日期day天后的一个(formatStr)的字符串
	 * 
	 * @param date
	 * @param mins
	 * @param formatStr
	 * @return
	 */
	public static String getDateOfDay(String date, int day, String formatStr) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(DateUtil.getDate(date, formatStr));
		now.add(Calendar.DATE, day);
		return dateToDateString(now.getTime(), formatStr);
	}

	/**
	 * 获取指定日期mins分钟后的一个(formatStr)的字符串
	 * 
	 * @param date
	 * @param mins
	 * @param formatStr
	 * @return
	 */
	public static String getDateOfMin(String date, int mins, String formatStr) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(DateUtil.getDate(date, formatStr));
		now.add(Calendar.SECOND, mins * 60);
		return dateToDateString(now.getTime(), formatStr);
	}

	/**
	 * 获取当前日期mins分钟后的一个(formatStr)的字符串
	 * 
	 * @param mins
	 * @param formatStr
	 * @return
	 */
	public static String getDateStringOfMin(int mins, String formatStr) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(new Date());
		now.add(Calendar.MINUTE, mins);
		return dateToDateString(now.getTime(), formatStr);
	}

	/**
	 * 获取当前日期sec秒后的一个(formatStr)的字符串
	 * 
	 * @param sec
	 * @param formatStr
	 * @return
	 */
	public static String getDateStringOfSec(int sec, String formatStr) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(new Date());
		now.add(Calendar.SECOND, sec);
		return dateToDateString(now.getTime(), formatStr);
	}

	/**
	 * 获得指定日期月份的天数
	 * 
	 * @return
	 */
	public static int getMonthDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getActualMaximum(Calendar.DAY_OF_MONTH);

	}

	/**
	 * 获得系统当前月份的天数
	 * 
	 * @return
	 */
	public static int getCurentMonthDay() {
		Date date = Calendar.getInstance().getTime();
		return getMonthDay(date);
	}

	/**
	 * 获得指定日期月份的天数 yyyy-mm-dd
	 * 
	 * @return
	 */
	public static int getMonthDay(String date) {
		Date strDate = getDate(date, yyyy_MM_dd_EN);
		return getMonthDay(strDate);
	}

	/**
	 * 获取19xx,20xx形式的年
	 * 
	 * @param d
	 * @return
	 */
	public static int getYear(Date d) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		return now.get(Calendar.YEAR);
	}

	/**
	 * 获取月份，1-12月
	 * 
	 * @param d
	 * @return
	 */
	public static int getMonth(Date d) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		return now.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取xxxx-xx-xx的日
	 * 
	 * @param d
	 * @return
	 */
	public static int getDay(Date d) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		return now.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取Date中的小时(24小时)
	 * 
	 * @param d
	 * @return
	 */
	public static int getHour(Date d) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		return now.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 获取Date中的分钟
	 * 
	 * @param d
	 * @return
	 */
	public static int getMin(Date d) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		return now.get(Calendar.MINUTE);
	}

	/**
	 * 获取Date中的秒
	 * 
	 * @param d
	 * @return
	 */
	public static int getSecond(Date d) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		return now.get(Calendar.SECOND);
	}

	/**
	 * 得到本周周一
	 * 
	 * @return yyyy-MM-dd
	 */
	public static String getMondayOfThisWeek() {
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + 1);
		return dateToDateString(c.getTime(), yyyy_MM_dd_EN);
	}

	/**
	 * 得到本周周日
	 * 
	 * @return yyyy-MM-dd
	 */
	public static String getSundayOfThisWeek() {
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + 7);
		return dateToDateString(c.getTime());
	}

	/**
	 * 得到本周周(*)
	 * 
	 * @return yyyy-MM-dd
	 */
	public static String getDayOfThisWeek(int num) {
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + num);
		return dateToDateString(c.getTime(), yyyy_MM_dd_EN);
	}

	/**
	 * 得到本月指定天
	 * 
	 * @return yyyy-MM-dd
	 */
	public static String getDayOfThisMoon(String num) {
		String date = dateToDateString(new Date(), yyyy_MM_EN);
		date = date + "-" + num;
		return date;
	}

	/**
	 * 得到两个日期相隔的日期区间段字符串
	 * 
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @param dateFroamt
	 *            日期格式字符串
	 */
	public static List<String> printDate(String startDate, String endDate, String dateFroamt) {
		List<String> dateString = new ArrayList<String>();
		try {
			SimpleDateFormat format = new SimpleDateFormat(dateFroamt);
			// 设置起始时间段(得到的日期会大于起始时间段一天)
			Calendar calStartDate = Calendar.getInstance();
			calStartDate.setTime(format.parse(startDate));
			// 得到实际其实时间段
			calStartDate.add(Calendar.DATE, -1);
			// 设置结束时间段
			Calendar calEndDate = Calendar.getInstance();
			calEndDate.setTime(format.parse(endDate));
			while (calStartDate.before(calEndDate)) {
				calStartDate.add(Calendar.DAY_OF_YEAR, 1);
				dateString.add(format.format(calStartDate.getTime()));

			}
		} catch (ParseException p) {
			p.printStackTrace();
		}
		return dateString;
	}

//	public static void main(String[] arg) {
//		System.out.println(new java.sql.Date((new Date().getTime())));
//		System.out.println(getDateOfMin("2012-09-12 02:00:00", -1, yyyy_MM_dd_HH_mm_ss_EN));
//        System.out.println(getWeekOfDate(new Date()));
//        System.out.println(getWeekOfDate(getFirstDayOfWeek(new Date())));
//	}

    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return sdf.format(date);
    }


    /**
     * 获取当前日期是周几<br>
     *
     * @param dt
     * @return 当前日期是周几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 取得指定日期所在周的第一天
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
        return c.getTime ();
    }

    /**
     * 列出周一到周五的日期
     * @param date
     * @return
     */
    public static String[] listWeekFiveDays(Date date){
        String[] dateArray = new String[5];
        Date firstDayOfWeek = DateUtil.getFirstDayOfWeek(date);
        String dateStart = DateUtil.formatDate(firstDayOfWeek, "yyyy-MM-dd");
        dateArray[0] = dateStart;
        for(int i = 1;i< 5; i++ ){
            firstDayOfWeek = DateUtils.addDays(firstDayOfWeek, 1);
            dateArray[i] =  DateUtil.formatDate(firstDayOfWeek, "yyyy-MM-dd");
        }
        return dateArray;
    }

	/**
	 * 转换日期格式
	 *
	 * @param date
	 * @param oldPattern
	 * @param newPattern
	 * @return
	 */
	public static String changePatten(String date, String oldPattern, String newPattern) {
		SimpleDateFormat oldFormat = new SimpleDateFormat(oldPattern);
		SimpleDateFormat newFormat = new SimpleDateFormat(newPattern);

		try {
			Date oldDate = oldFormat.parse(date);
			return newFormat.format(oldDate);
		} catch (Exception e) {
			return null;
		}
	}

}
