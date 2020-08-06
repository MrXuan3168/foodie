package com.foodie.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * jamie时间工具类
 *
 * @author jamie
 */
public class JamieDateUtils {

    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static final String TIME_FORMATTER_STR = "HH:mm:ss";

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static final String DATE_FORMATTER_STR = "yyyy-MM-dd";

    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static final String DATETIME_FORMATTER_STR = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当前系统时间
     *
     * @return 纯日期时间
     */
    public static LocalTime getLocalTime() {
        return LocalTime.now();
    }

    /**
     * 获取当前系统日期
     *
     * @return 纯日期对象
     */
    public static LocalDate getLocalDate() {
        return LocalDate.now();
    }

    /**
     * 获取当前系统日期时间
     *
     * @return 日期+时间对象
     */
    public static LocalDateTime getLocalDateTime() {
        return LocalDateTime.now();
    }

    /**
     * 获取当前系统时间字符串
     *
     * @return 时间字符串(返回格式为 HH : mm : ss)
     */
    public static String getLocalTimeString() {
        return LocalTime.now().format(TIME_FORMATTER);
    }

    /**
     * 获取当前系统日期字符串
     *
     * @return 时间字符串(返回格式为 yyyy - MM - dd)
     */
    public static String getLocalDateString() {
        return LocalDate.now().format(DATE_FORMATTER);
    }

    /**
     * 获取当前系统日期时间字符串
     *
     * @return 时间字符串(返回格式为 yyyy - MM - dd HH : mm : ss)
     */
    public static String getLocalDateTimeString() {
        return LocalDateTime.now().format(DATETIME_FORMATTER);
    }

    /**
     * 字符串转LocalTime
     *
     * @param time 时间字符串(格式为 HH:mm:ss)
     * @return 纯时间对象
     */
    public static LocalTime string2LocalTime(String time) {
        return LocalTime.parse(time, TIME_FORMATTER);
    }

    /**
     * 字符串转LocalDate
     *
     * @param date 时间字符串(格式为 yyyy-MM-dd)
     * @return 纯日期对象
     */
    public static LocalDate string2LocalDate(String date) {
        return LocalDate.parse(date, DATE_FORMATTER);
    }

    /**
     * 字符串转LocalDateTime
     *
     * @param dateTime 时间字符串(格式为 yyyy-MM-dd HH:mm:ss)
     * @return 时间+日期对象
     */
    public static LocalDateTime string2LocalDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DATETIME_FORMATTER);
    }

    /**
     * Date转LocalDateTime
     *
     * @param date Data 对象
     * @return 时间+日期对象
     */
    public static LocalDateTime date2LocalDateTime(Date date) {
        int year = getYear(date);
        int monthValue = getMonth(date);
        int day = getDay(date);
        int hour = getHour(date);
        int minute = getMinute(date);
        int second = getSecond(date);
        return LocalDateTime.of(year, monthValue, day, hour, minute, second);
    }

    /**
     * Date转LocalDate
     *
     * @param date Data 对象
     * @return 纯日期对象
     */
    public static LocalDate date2LocalDate(Date date) {
        int year = getYear(date);
        int monthValue = getMonth(date);
        int day = getDay(date);
        return LocalDate.of(year, monthValue, day);
    }

    /**
     * Date转LocalDate
     *
     * @param date Data 对象
     * @return 纯时间对象
     */
    public static LocalTime date2LocalTime(Date date) {
        int hour = getHour(date);
        int minute = getMinute(date);
        int second = getSecond(date);
        return LocalTime.of(hour, minute, second);
    }

    /**
     * LocalDateTime转换为Date
     *
     * @param localDateTime 日期+时间对象
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        String year = getYear(localDateTime);
        String monthValue = getMonth(localDateTime);
        String day = getDay(localDateTime);
        String hour = getHour(localDateTime);
        String minute = getMinute(localDateTime);
        String second = getSecond(localDateTime);
        return string2Date(year + monthValue + day + hour + minute + second, "yyyyMMddHHmmss");
    }

    public static String getYear(LocalDateTime localDateTime) {
        return format(localDateTime.getYear());
    }

    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static String getMonth(LocalDateTime localDateTime) {
        return format(localDateTime.getMonthValue());
    }

    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static String getDay(LocalDateTime localDateTime) {
        return format(localDateTime.getDayOfMonth());
    }

    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    public static String getHour(LocalDateTime localDateTime) {
        return format(localDateTime.getHour());
    }

    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static String getMinute(LocalDateTime localDateTime) {
        return format(localDateTime.getMinute());
    }

    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    public static String getSecond(LocalDateTime localDateTime) {
        return format(localDateTime.getSecond());
    }

    public static int getSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 数据值字符串格式化，低于10前面补0
     *
     * @param number 数据值
     * @return java.lang.String
     */
    private static String format(int number) {
        String num = String.valueOf(number);
        return number < 10?"0" + num:num;
    }

    /**
     * 判断字符串日期是否匹配指定的格式化日期
     *
     * @param strDate 时间字符串
     * @param formatter 日期格式
     * @return boolean
     */
    public static boolean isValidDate(String strDate, String formatter) {
        ParsePosition pos = new ParsePosition(0);
        if(StringUtils.isBlank(strDate) || StringUtils.isBlank(formatter)){
            return false;
        }
        try{
            SimpleDateFormat sdf = new SimpleDateFormat(formatter);
            sdf.setLenient(false);
            Date date = sdf.parse(strDate, pos);
            if(date == null){
                return false;
            }else{
                return pos.getIndex() <= sdf.format(date).length();
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * java.util.Date
     *
     * @param strDate 时间字符串
     * @param format 编码格式
     * @return Date对象
     */
    public static Date string2Date(String strDate, String format) {
        if(strDate == null){
            return null;
        }
        DateFormat df;
        try{
            if(format == null){
                df = new SimpleDateFormat();
            }else{
                df = new SimpleDateFormat(format);
            }
            df.setLenient(false);
            return df.parse(strDate);
        }catch(ParseException e){
            e.printStackTrace();
            return null;
        }
    }

    // public static void main(String[] args) {
    // Date date = DateUtil.stringToDate("1900-01-01");
    // System.out.println(date);
    // LocalDateTime localDateTime = date2LocalDateTime(date);
    // System.out.println(localDateTime);
    // LocalDateTime localDate = JamieDateUtils.string2LocalDateTime("1900-01-01 00:00:00");
    // Date date1 = JamieDateUtils.localDateTime2Date(localDate);
    // System.out.println(date1);
    //
    // Calendar calendar = Calendar.getInstance();
    // calendar.setTime(date);
    // System.out.println(calendar.get(Calendar.YEAR));
    // System.out.println(calendar.get(Calendar.MONTH) + 1);
    // System.out.println(calendar.get(Calendar.DATE));
    //
    // System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
    // System.out.println(calendar.get(Calendar.MINUTE));
    // System.out.println(calendar.get(Calendar.SECOND));
    //
    // Date date2 = new Date();
    // System.out.println(date2);
    // LocalTime localTime = date2LocalTime(date2);
    // System.out.println(localTime);
    //
    // System.out.println("------------------");
    // calendar = Calendar.getInstance();
    // calendar.setTime(date2);
    // System.out.println(calendar.get(Calendar.YEAR));
    // System.out.println(calendar.get(Calendar.MONTH) + 1);
    // System.out.println(calendar.get(Calendar.DATE));
    // System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
    // System.out.println(calendar.get(Calendar.MINUTE));
    // System.out.println(calendar.get(Calendar.SECOND));
    // LocalDate localDate1 = date2LocalDate(date2);
    // System.out.println(localDate1);
    // LocalDateTime localDateTime1 = date2LocalDateTime(date2);
    // System.out.println(localDateTime1);
    // }
}
