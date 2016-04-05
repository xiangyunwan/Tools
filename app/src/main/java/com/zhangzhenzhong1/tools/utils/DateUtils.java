package com.zhangzhenzhong1.tools.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * 解析日期的工具类
 * 
 * @author xuliangbo
 * 
 */
@SuppressLint("SimpleDateFormat")
public class DateUtils {
	/**
	 * 如：2012-12-12
	 */
	private static SimpleDateFormat commonDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");

	/**
	 * 如：2012-12-12 12:12:12
	 */
	private static SimpleDateFormat totalDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 如：2012-12-12 12:12
	 */
	private static SimpleDateFormat YMDHM_DateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");
	
	/**
	 * 如：12-12 12:12
	 */
	private static SimpleDateFormat MDHM_DateFormat = new SimpleDateFormat(
			"MM-dd HH:mm");

	/**
	 * 如：12-12
	 */
	private static SimpleDateFormat monthDayDateFormat = new SimpleDateFormat(
			"MM-dd");

	/**
	 * 如：2014年06月
	 */
	private static SimpleDateFormat yearMonthDateFormat = new SimpleDateFormat(
			"yyyy年MM月");

	/**
	 * 如：HH:mm:ss
	 */
	private static SimpleDateFormat hourMinSecDateFormat = new SimpleDateFormat(
			"HH:mm:ss");


	/**
	 * 如：06.21
	 */
	private static SimpleDateFormat mMonthDateFormat = new SimpleDateFormat(
			"MM.dd");

	/**
	 * 得到年月日
	 * 
	 * @param longdate
	 * @return
	 */
	public static String getNormalDateFormat(long longdate) {

		Date date = new Date(longdate);
		return commonDateFormat.format(date);
	}

	public static String getMonthDateFormater(long longdate) {
		Date date = new Date(longdate);
		return mMonthDateFormat.format(date);
	}

	/**
	 * 得到年月日
	 * 
	 * @param longdate
	 * @return
	 */
	public static String getTotalDateFormat(long longdate) {
		Date date = new Date(longdate);
		return totalDateFormat.format(date);
	}

	/**
	 * 得到年月日
	 * 
	 * @param longdate
	 * @return
	 */
	public static String getMonthDateFormat(long longdate) {
		Date date = new Date(longdate);
		return monthDayDateFormat.format(date);
	}

	/**
	 * 得到年月日
	 * 
	 * @param longdate
	 * @return
	 */
	public static String getYearMonthDateFormat(long longdate) {
		Date date = new Date(longdate);
		return yearMonthDateFormat.format(date);
	}

	/**
	 * HH:mm:ss
	 * 
	 * @param longdate
	 * @return
	 */
	public static String hourMinSecDateFormat(long longdate) {
		Date date = new Date(longdate);
		return hourMinSecDateFormat.format(date);
	}
	/**
	 * HH:mm
	 * 
	 * @param longdate
	 * @return
	 */
	public static String hourMinDateFormat(long longdate) {
		Date date = new Date(longdate);
		String[] hourMinArray=hourMinSecDateFormat.format(date).split(":");
		
		return hourMinArray[0]+":"+hourMinArray[1];
	}
	
	/**
	 * @param longData
	 * @return  月-日 时-分
	 * @author gaixutian@jd.com
	 */
	public static String getMDHMFormat(long longData){
		Date date = new Date(longData);
		return MDHM_DateFormat.format(date);
	}
	
	public static String getYMDHMFormat(long longData){
		Date date = new Date(longData);
		return YMDHM_DateFormat.format(date);
	}
	/**
	 * 时间自动补0转换
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String parseStr2Date(String date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		try {
			return sdf.format(sdf.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
