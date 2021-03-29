package com.medimpact.medeasy.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {
	private static SimpleDateFormat df4DateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private static SimpleDateFormat df4Date = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat df4Time= new SimpleDateFormat("HH:mm");
	private static SimpleDateFormat df4DateTimeWithSec = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static String formatDate(Date date) {
		if (date == null)
			return "";
		return df4Date.format(date);
	}
	
	public static String formatDateTime(Date date) {
		if (date == null)
			return "";
		return df4DateTime.format(date);
	}
	
	public static String formatDateTime(Long date) {
		if (date == null)
			return "";
		return df4DateTime.format(date);
	}
	
	public static String formatTime(Date date) {
		if (date == null)
			return "";
		return df4Time.format(date);
	}
	
	public static String formatDateTimeWithSec(Date date) {
		if (date == null)
			return "";
		return df4DateTimeWithSec.format(date);
	}
	
	public static String formatDateTimeWithSec(Long date) {
		if (date == null)
			return "";
		return df4DateTimeWithSec.format(date);
	}
	
	public static String inDateFormat(String pattern ,Date indate)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String str = sdf.format(indate);
		return str;
	}
}
