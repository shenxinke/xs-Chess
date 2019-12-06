package com.xswq.chess.myapplication.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtil {

    /**
     * 获取当前时间戳
     *
     * @return
     */
    public long getCurTimeLong() {
        long time = System.currentTimeMillis();
        return time;
    }

    /**
     * 获取当前时间
     *
     * @param pattern
     * @return
     */
    public static String getCurDate(String pattern) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat(pattern);
        return sDateFormat.format(new java.util.Date());
    }

    /**
     * 时间戳转换成字符窜
     *
     * @param milSecond
     * @param pattern
     * @return "yyyy-MM-dd HH:mm:ss"
     */
    public static String getDateToString(long milSecond, String pattern) {
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    /**
     * 将字符串转为时间戳
     *
     * @param dateString
     * @param pattern
     * @return
     */
    public static long getStringToDate(String dateString, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static String stringForTime(long position) {
        int totalSeconds = (int) ((position / 1000.0) + 0.5);
        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;
        return String.format(Locale.US, "%02d:%02d:%02d", hours, minutes, seconds);
    }

    /**
     * 获取今天还剩下多少秒
     * @return
     */
    public static int getMiao(){
        Calendar curDate = Calendar.getInstance();
        Calendar tommorowDate = new GregorianCalendar(curDate
                .get(Calendar.YEAR), curDate.get(Calendar.MONTH), curDate
                .get(Calendar.DATE) + 1, 0, 0, 0);
        return (int)(tommorowDate.getTimeInMillis() - curDate.getTimeInMillis()) / 1000;
    }

    /**
     * 距离指定时间还有多久
     * @return
     */
    public static String getSjc(long date){
        long miao = (date)/1000;
        int day = (int)(miao / (60*60*24));				//天
        int hour = (int)(miao % (60*60*24) / (60*60));	//时
        int mm = (int)(miao % (60*60) / 60);			//分
        int sec = (int)(miao % 60);						//秒
        return day+"天"+hour+"时"+mm+"分"+sec+"秒";
    }

    public static String getSjcSec(long date){
        long miao = (date)/1000;
        int mm = (int)(miao % (60*60) / 60);			//分
        int sec = (int)(miao % 60);						//秒
        return mm+"分"+sec+"秒";
    }
}

