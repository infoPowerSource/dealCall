package com.deal.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.codehaus.plexus.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

/**
 * 日期时间 公共工具类
 * 
 * @author zhipeng.xu
 *
 */
public class DateFormatUtil{
	private static Logger logger = LoggerFactory.getLogger(DateFormatUtil.class);

	public DateFormatUtil() {
	}

	public static java.sql.Date getNowTime(){
		return new java.sql.Date(Calendar.getInstance().getTime().getTime());
	}

	public static String getTimeAsDate(){
		String nowTime;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
		nowTime = formatter.format(cal.getTime());
		return nowTime;
	}
	
	public static String getTimeASKey(){
		String nowTime;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd  HH:mm");
		nowTime = formatter.format(cal.getTime());
		return nowTime;
	}

	// 获取页面显示的时间格式 例：2017-05-26 09:30-10:30
	public static String getConfTime(String beginTime, String endTime){
		String confTime = beginTime.substring(0, 16) + "-" + endTime.substring(11, 16);
		return confTime;
	}

	// 计算会议开始时间距现在的分钟数
	public static Long getDisplauMinutes(Timestamp beginTime){
		Long minutes = (beginTime.getTime() - new Date().getTime()) / 60000;
		return minutes;
	}

	public static String getTimeDate(){
		String nowTime;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		nowTime = formatter.format(cal.getTime());
		return nowTime;
	}

	public static Date Str2Date(String date){
		try{
			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date d = ft.parse(date);
			return d;
		}catch (Exception ex){
			return new Date(Calendar.getInstance().getTime().getTime());
		}
	}

	public static Timestamp str2TimeStamp(String date){
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		try{
			ts = Timestamp.valueOf(date);
		}catch (Exception e){
			e.printStackTrace();
			logger.error("String型data转化Timestamp错误，异常信息：" + e);
			return null;
		}
		return ts;
	}

	public static Date Str2Date1(String date){
		try{
			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
			if(date.length() > 10){
				ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			}
			java.util.Date d = ft.parse(date);
			return d;
		}catch (Exception ex){
			return null;
		}
	}

	public static Date Str2DateX(String date, String pattern){
		try{
			SimpleDateFormat ft = new SimpleDateFormat(pattern);
			java.util.Date d = ft.parse(date);
			return d;
		}catch (Exception ex){
			return null;
		}
	}

	public static String Date4Seq(){
		return DateToStr(getNowTime(), "yyyyMMddHHmmss");
	}

	public static String Date4Seq(String strdate){
		return DateToStr(DateFormatUtil.Str2Date1(strdate), "yyyyMMddHHmmss");
	}

	public static String DefaultDateTOStr(java.util.Date date){
		if(date == null)
			return "";
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		return ft.format(date);
	}

	public static String DefaultDateTOStr(java.sql.Date date){
		if(date == null)
			return "";
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		return ft.format(date);
	}

	public static String DefaultDateTOStr2(java.sql.Date date){
		if(date == null)
			return "";
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM");
		return ft.format(date);
	}

	public static String DefaultDateTOStr2(java.util.Date date){
		if(date == null)
			return "";
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM");
		return ft.format(date);
	}

	public static String DateToStr(java.sql.Date date, String pattern){
		if(date == null)
			return "";
		SimpleDateFormat ft = new SimpleDateFormat(pattern);
		return ft.format(date);
	}

	public static String DateToStr(java.util.Date date, String pattern){
		if(date == null)
			return "";
		SimpleDateFormat ft = new SimpleDateFormat(pattern);
		return ft.format(date);
	}

	public static String getYear(java.util.Date date){
		SimpleDateFormat ft = new SimpleDateFormat("yyyy");
		return ft.format(date);
	}

	public static String getMonth(java.sql.Date date){
		SimpleDateFormat ft = new SimpleDateFormat("MM");
		return ft.format(date);
	}

	public static String getDay(java.sql.Date date){
		SimpleDateFormat ft = new SimpleDateFormat("dd");
		return ft.format(date);
	}

	public static String getHour(java.util.Date date){
		SimpleDateFormat ft = new SimpleDateFormat("HH");
		return ft.format(date);
	}

	public static String getMinute(java.util.Date date){
		SimpleDateFormat ft = new SimpleDateFormat("mm");
		return ft.format(date);
	}

	/**
	 * 取得当前或者之前或者之后几个月的时间
	 * 
	 * @param dataDate
	 * @param mon
	 * @return
	 * @req ROADSHOW-SRS-Password-1
	 * @throws ParseException
	 */
	public static Date getBeforeMonth(String dataDate, int mon) throws ParseException{
		if(dataDate == null || dataDate.equals("")){
			dataDate = DefaultDateTOStr(getNowTime());
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date1 = format.parse(dataDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		cal.add(Calendar.MONDAY, mon);
		return cal.getTime();
	}

	public static String getLastMonth(String dataDate) throws ParseException{
		if(dataDate == null || dataDate.equals("")){
			dataDate = DefaultDateTOStr(getNowTime());
		}
		String lastMonth = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date1 = format.parse(dataDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		cal.add(Calendar.MONDAY, -1);
		lastMonth = DefaultDateTOStr2(cal.getTime());
		return lastMonth;
	}

	public static String getNextMonth(String dataDate) throws ParseException{
		if(dataDate == null || dataDate.equals("")){
			dataDate = DefaultDateTOStr(getNowTime());
		}
		String lastMonth = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date1 = format.parse(dataDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		cal.add(Calendar.MONDAY, 1);
		lastMonth = DefaultDateTOStr2(cal.getTime());
		return lastMonth;
	}

	/**
	 * 获得日期的下月日期
	 * 
	 * @param dataDate
	 * @return
	 * @throws ParseException
	 */
	public static String getNextMonthDate(String dataDate) throws ParseException{
		if(dataDate == null || dataDate.equals("")){
			dataDate = DefaultDateTOStr(getNowTime());
		}
		String nextMonthDate = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date1 = format.parse(dataDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		cal.add(Calendar.MONDAY, 1);
		nextMonthDate = DefaultDateTOStr(cal.getTime());
		return nextMonthDate;
	}

	/**
	 * 获得日期的提前日期
	 * 
	 * @param dataDate
	 * @return
	 * @throws ParseException
	 * @throws ParseException
	 */
	public static String getAheadDayDate(String dataDate, String day) throws ParseException{
		if(dataDate == null || dataDate.equals("")){
			dataDate = DefaultDateTOStr(getNowTime());
		}
		String aheadDayDate = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date1 = format.parse(dataDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		int day1 = Integer.parseInt(day);
		cal.add(Calendar.DAY_OF_MONTH, -day1);
		aheadDayDate = DefaultDateTOStr(cal.getTime());
		return aheadDayDate;
	}

	public static String getLastDay(String dataDate) throws ParseException{
		if(dataDate == null || dataDate.equals("")){
			dataDate = DefaultDateTOStr(getNowTime());
		}
		String lastDay = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date1 = format.parse(dataDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		cal.add(Calendar.DATE, -1);
		lastDay = DefaultDateTOStr(cal.getTime());
		return lastDay;
	}

	public static String getNextDay(String dataDate) throws ParseException{
		if(dataDate == null || dataDate.equals("")){
			dataDate = DefaultDateTOStr(getNowTime());
		}
		String nextDay = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date1 = format.parse(dataDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		cal.add(Calendar.DATE, 1);
		nextDay = DefaultDateTOStr(cal.getTime());
		return nextDay;
	}

	public static String getLastHour(String dateData) throws ParseException{
		if(dateData == null || dateData.equals("")){
			dateData = DefaultDateTOStr(getNowTime());
		}
		String nextHour = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date1 = format.parse(dateData);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		cal.add(Calendar.HOUR, -1);
		nextHour = DateToString(cal.getTime());
		return nextHour;
	}

	/**
	 * 对传入的时间进行按小时提前或者往后计算n小时
	 * 
	 * @param date
	 * @param count
	 * @return
	 * @throws ParseException
	 * @req ROADSHOW-SRS-Password-2
	 * @des ROADSHOW-HLD-Password-2
	 */
	public static Date getChangeHour(Date date, Integer count) throws ParseException{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, count);
		return cal.getTime();
	}

	public static Date getChangeMinute(Date date, Integer count) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date1 = format.parse(date.toString());
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		cal.add(Calendar.MINUTE, count);
		return cal.getTime();
	}

	public static String getChangeMinute(String dateData, Integer count) throws ParseException{
		if(dateData == null || dateData.equals("")){
			dateData = DefaultDateTOStr(getNowTime());
		}
		String nextHour = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date1 = format.parse(dateData);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		cal.add(Calendar.MINUTE, count);
		nextHour = DateToString(cal.getTime());
		return nextHour;
	}

	public static String DateToString(java.util.Date date){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datestr = null;
		try{
			datestr = formatter.format(date);
		}catch (Exception ex){
			System.out.println(ex.getMessage());
		}
		return datestr;
	}

	/**
	 * 时区时间转换
	 * 
	 * @param localTimeZone
	 *            本地时区
	 * @param localTime
	 *            本地时间
	 * @param changingTimeZone
	 *            需要转换的时区
	 * @return
	 * @throws ParseException
	 */
	public static String TimeZoneExchange(String localTimeZone, String localTime, String changingTimeZone) throws ParseException{
		String datestr = null;
		int localZone = Integer.parseInt(localTimeZone.substring(4, localTimeZone.length()));
		if(localTimeZone.substring(3, 4).equals("-")){
			localZone = localZone * (-1);
		}
		int changeZone = Integer.parseInt(changingTimeZone.substring(4, changingTimeZone.length()));
		if(changingTimeZone.substring(3, 4).equals("-")){
			changeZone = changeZone * (-1);
		}
		int hour = changeZone - localZone;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dt = format.parse(localTime, new ParsePosition(0));
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(dt);
		rightNow.add(Calendar.HOUR, hour);
		Date dt1 = rightNow.getTime();
		datestr = DateFormatUtil.DateToStr(dt1, "yyyy-MM-dd HH:mm:ss");
		return datestr;
	}

	/**
	 * 时区时间转换
	 * 
	 * @param localTimeZone
	 *            本地时区
	 * @param localTime
	 *            本地时间
	 * @param changingTimeZone
	 *            需要转换的时区
	 * @return
	 */
	public static String TimeZoneExchangeByDate(String localTimeZone, Date localTime, String changingTimeZone){
		String datestr = null;
		int localZone = Integer.parseInt(localTimeZone.substring(4, localTimeZone.length()));
		if(localTimeZone.substring(3, 4).equals("-")){
			localZone = localZone * (-1);
		}
		int changeZone = Integer.parseInt(changingTimeZone.substring(4, changingTimeZone.length()));
		if(changingTimeZone.substring(3, 4).equals("-")){
			changeZone = changeZone * (-1);
		}
		int hour = changeZone - localZone;
		// 时间格式
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(localTime);
		rightNow.add(Calendar.HOUR, hour);
		Date dt1 = rightNow.getTime();
		datestr = DateFormatUtil.DateToStr(dt1, "yyyy-MM-dd HH:mm:ss");
		return datestr;
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param begin
	 * @param end
	 * @return
	 */
	public static int compareDate(Date begin, Date end){
		long l = end.getTime() - begin.getTime();
		return (int) (l / 60 / 60 / 1000 / 24);
	}

	/**
	 * 计算两个日期之间相差的天数只精确到天
	 * 
	 * @param begin
	 * @param end
	 * @req ROADSHOW-SRS-Password-1
	 * @return
	 */
	public static int compareDateByDay(Date begin, Date end){
		// 转换成YYYY-MM-DD的日期
		begin = Str2Date(DefaultDateTOStr(begin));
		end = Str2Date(DefaultDateTOStr(end));
		long l = end.getTime() - begin.getTime();
		return (int) (l / 60 / 60 / 1000 / 24);
	}

	public static int compareSecond(Date begin, Date end){
		long l = end.getTime() - begin.getTime();
		return (int) l / 1000;
	}

	/**
	 * 计算两个日期之间相差的分钟数
	 * 
	 * @param begin
	 * @param end
	 * @return
	 */
	public static int compareMinute(Timestamp begin, Timestamp end){
		long l = end.getTime() - begin.getTime();
		return (int) l / 1000 / 60;
	}
	
	public static int compareToMinute(Date begin, Date end){
		int minutes=0;
		long ltime = end.getTime() - begin.getTime();
		if(ltime%60000!=0){
			minutes=(int)ltime/60000+1;
        }else{
        	minutes=(int)ltime/60000;
        }
		return minutes;
	}

	/**
	 * 将指定的时间字符串加上分钟取得新时间字符串
	 * 
	 * @param beginTime
	 *            开始时间
	 * @param minute
	 *            增加的分钟数
	 * @return endTime 转换后的时间字符串
	 */
	public static String dateTimeAddMinute(String beginTime, Integer minute){
		String endTime = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		Calendar cal = Calendar.getInstance();
		try{
			date = formatter.parse(beginTime);
			cal.setTime(date);
			cal.add(Calendar.MINUTE, minute);
			endTime = formatter.format(cal.getTime());
			System.out.println("endTime:" + endTime);
		}catch (ParseException e){
			e.printStackTrace();
		}
		return endTime;
	}

	/**
	 * 取得当前日期的0点0分0秒
	 * 
	 * @return 当前日期的0点0分0秒
	 * @req ROADSHOW-SRS-Password-1
	 */
	public static Timestamp today(){
		Timestamp ts;
		try{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			// 取得Timestamp类型的当前系统时间
			Timestamp today = new Timestamp(System.currentTimeMillis());
			String str = df.format(today);
			Date date11 = df.parse(str);
			String time = df.format(date11);
			ts = Timestamp.valueOf(time);
			return ts;
		}catch (ParseException e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 取得当前日期
	 * 
	 * @return 当前日期
	 * @req ROADSHOW-SRS-Password-1
	 */
	public static String getToday(){
		try{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			// 取得Timestamp类型的当前系统时间
			Timestamp today = new Timestamp(System.currentTimeMillis());
			String str = df.format(today);
			Date date11 = df.parse(str);
			
			return str;
		}catch (ParseException e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 取输入参数日期过去的多少天
	 * 
	 * @param beginDate
	 *            日期
	 * @param dayNum
	 *            天数
	 * @req ROADSHOW-SRS-Password-1
	 * @return
	 */
	public static Timestamp dateReductionDay(Date beginDate, Integer dayNum){
		if(ObjectUtils.isEmpty(beginDate)){
			return null;
		}
		Timestamp ts;
		try{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar date = Calendar.getInstance();
			date.setTime(beginDate);
			date.set(Calendar.DATE, date.get(Calendar.DATE) - dayNum);
			Date endDate = df.parse(df.format(date.getTime()));
			String time = df.format(endDate);
			ts = Timestamp.valueOf(time);
			return ts;
		}catch (ParseException e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 取输入参数日期过去的多少小时
	 * 
	 * @param beginDate
	 *            日期
	 * @param hourNum
	 *            小时数
	 * @req ROADSHOW-SRS-Password-1
	 * @return
	 */
	public static Timestamp dateReductionHour(Date beginDate, Integer hourNum){
		Timestamp ts;
		try{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar date = Calendar.getInstance();
			date.setTime(beginDate);
			date.set(Calendar.HOUR, date.get(Calendar.HOUR) - hourNum);
			Date endDate = df.parse(df.format(date.getTime()));
			String time = df.format(endDate);
			ts = Timestamp.valueOf(time);
			return ts;
		}catch (ParseException e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 取输入参数日期过去的多少分钟
	 * 
	 * @param beginDate
	 *            日期
	 * @param minuteNum
	 *            分钟数
	 * @return
	 */
	public static Timestamp dateReductionMinute(Date beginDate, Integer minuteNum){
		Timestamp ts;
		try{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar date = Calendar.getInstance();
			date.setTime(beginDate);
			date.set(Calendar.MINUTE, date.get(Calendar.MINUTE) - minuteNum);
			Date endDate = df.parse(df.format(date.getTime()));
			String time = df.format(endDate);
			ts = Timestamp.valueOf(time);
			return ts;
		}catch (ParseException e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取指定日期的0点0分0秒 2017-5-25下午4:16:36
	 * 
	 * @param time
	 * @return Timestamp
	 */
	public static Timestamp startDay(Timestamp timeStamp){
		Timestamp ts;
		try{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			String str = df.format(timeStamp);
			Date date11 = df.parse(str);
			String time = df.format(date11);
			ts = Timestamp.valueOf(time);
			return ts;
		}catch (ParseException e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 检查指定时间是否在当天范围
	 * 
	 * @param time
	 * @return
	 */
	public static boolean checkTimeStampInToday(Timestamp time){
		Calendar beginTime = Calendar.getInstance();
		beginTime.set(Calendar.HOUR_OF_DAY, 0);
		beginTime.set(Calendar.MINUTE, 0);
		beginTime.set(Calendar.SECOND, 0);
		Calendar endTime = Calendar.getInstance();
		endTime.set(Calendar.HOUR_OF_DAY, 23);
		endTime.set(Calendar.MINUTE, 59);
		endTime.set(Calendar.SECOND, 59);
		Calendar t = Calendar.getInstance();
		t.setTimeInMillis(time.getTime());
		int result1 = t.compareTo(beginTime);
		int result2 = t.compareTo(endTime);
		if(result1 > 0 && result2 < 0){
			return true;
		}
		return false;
	}

	/**
	 * 将timeStamp转化为字符串
	 * 
	 * @param time
	 * @param format
	 * @return
	 */
	public static String timeStampToString(Timestamp time, String format){
		String tsStr = "";
		if(time != null && format != null){
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			tsStr = sdf.format(time);
		}
		return tsStr;
	}

	public static void main(String[] args){
		int c_d = DateFormatUtil.compareDateByDay(Str2Date("2017-05-26 11:08:00"), new Date());
		System.out.println(c_d);
	}

	/**
	 * 时间加秒
	 * 
	 * @param valuedate
	 *            输入时间
	 * @param second
	 *            加的秒数
	 * @return 返回加秒后的时间
	 */
	public static Date addSecond(Date valuedate, int second){
		if(valuedate == null){
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(valuedate);
		// valuedate 时间加 second 秒
		calendar.add(Calendar.SECOND, second);
		return calendar.getTime();
	}

	/**
	 * 时间加分钟
	 * 
	 * @param valuedate
	 *            输入时间
	 * @param second
	 *            加的分钟数
	 * @return 返回加分钟后的时间
	 */
	public static Date addMinitue(Date valuedate, int minitue){
		if(valuedate == null){
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(valuedate);
		// valuedate 时间加 minitue 分钟
		calendar.add(Calendar.MINUTE, minitue);
		return calendar.getTime();
	}

	/**
	 * 时间加月份
	 * 
	 * @param valuedate
	 *            输入时间
	 * @param second
	 *            加的月份数
	 * @return 返回加月份数后的时间
	 */
	public static Date addMonth(Date valuedate, int month){
		if(valuedate == null){
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(valuedate);
		calendar.add(Calendar.MONTH, month);
		return calendar.getTime();
	}

	/**
	 * 时间加天数
	 * 
	 * @param valuedate
	 *            输入时间
	 * @param day
	 *            添加的天数
	 * @return 返回添加天数后的时间
	 */
	public static Date addDay(Date valuedate, int day){
		if(null == valuedate){
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(valuedate);
		calendar.add(Calendar.DATE, day);
		return calendar.getTime();
	}

	public static Timestamp addMinutesInTimestamp(Timestamp beginTime, int minutes){
		if(ObjectUtils.isEmpty(beginTime)){
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(beginTime);
		cal.add(Calendar.MINUTE, minutes);
		return new Timestamp(cal.getTimeInMillis());
	}
	
	public static Timestamp addMonthInTimestamp(Timestamp beginTime,int months){
		if(ObjectUtils.isEmpty(beginTime)){
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(beginTime);
		cal.add(Calendar.MONTH, months);
		return new Timestamp(cal.getTimeInMillis());
	}

	public static Timestamp strData2Timestamp(String data){
		if(StringUtils.isEmpty(data)){
			return null;
		}
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		Pattern p = Pattern.compile("\\s+");
		Matcher m = p.matcher(data);
		data = m.replaceAll(" ");
		String temp = data.replace("/", "-");
		String[] tempStr = temp.split(":");
		temp = tempStr[0].trim() + ":" + tempStr[1].trim();
		stamp = Timestamp.valueOf(temp + ":00");
		return stamp;
	}
	
	public static Timestamp str2Timestamp(String data){
		if(StringUtils.isEmpty(data)){
			return null;
		}
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		stamp = Timestamp.valueOf(data + " 00:00:00");
		return stamp;
	}

	public static int strHours2Minutes(String duration){
		int du = 0;
		if(StringUtils.isEmpty(duration)){
			logger.error("创建会议的会议时长空");
		}else{
			du = Integer.valueOf(duration.split(":")[0]) * 60 + Integer.valueOf(duration.split(":")[1]);
		}
		return du;
	}

	public static String timestamps2StringWithoutSecond(Timestamp beginTime){
		if(ObjectUtils.isEmpty(beginTime)){
			logger.error("修改会议，转换timestamps到String，会议开始时间为空");
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd   HH:  mm");
		return df.format(beginTime);
	}
	
	public static String timestamps2StringData(Timestamp beginTime){
		if(ObjectUtils.isEmpty(beginTime)){
			logger.error("修改会议，转换timestamps到String，会议开始时间为空");
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return df.format(beginTime);
	}

	public static String minutes2Data(int minutes){
		if(minutes == 0){
			logger.error("修改会议,装换会议时长时，数据库取出时长为0");
			return null;
		}
		int hours = minutes / 60;
		int minute = minutes % 60;
		String minuteStr = minute < 10 ? "0" + String.valueOf(minute) : String.valueOf(minute);
		String minuteResult = minute == 0 ? "00" : minuteStr;
		String data = "0" + String.valueOf(hours) + ":" + minuteResult;
		return data;
	}
	public static String timeStamaps2StrFormat(Timestamp beginTime){
		String strData="";
		if(ObjectUtils.isEmpty(beginTime)){
			logger.error("修改会议，转换timestamps到String，会议开始时间为空");
			return strData;
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String temp=df.format(beginTime);
		String [] dataArray=temp.split("-");
		strData=dataArray[0]+"/"+dataArray[1]+"/"+dataArray[2];
		return strData;
	}
	
	public static String getDurationTime(Timestamp beginTime, int duration){
		if(ObjectUtils.isEmpty(beginTime)){
			return "";
		}
		Timestamp endTime=addMinutesInTimestamp(beginTime,duration);
		return DateToStr(beginTime,"HH:mm")+"~"+DateToStr(endTime,"HH:mm");
	}
	
	public static String getDurationTimeForReport(Timestamp beginTime, int duration){
		if(ObjectUtils.isEmpty(beginTime)){
			return "";
		}
		Timestamp endTime=addMinutesInTimestamp(beginTime,duration);
		return DateToStr(beginTime,"HH:mm")+"-"+DateToStr(endTime,"HH:mm");
	}
	
	public static String timeStamaps2StrFormatForReport(Timestamp beginTime){
		String strData="";
		if(ObjectUtils.isEmpty(beginTime)){
			logger.error("修改会议，转换timestamps到String，会议开始时间为空");
			return strData;
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String temp=df.format(beginTime);
		String [] dataArray=temp.split("-");
		strData=dataArray[0]+"年"+dataArray[1]+"月"+dataArray[2]+"日";
		return strData;
	}
	
	public static String minutes2DataForReport(int minutes){
		if(minutes == 0){
			logger.error("修改会议,装换会议时长时，数据库取出时长为0");
			return null;
		}
		int hours = minutes / 60;
		int minute = minutes % 60;
		String minuteResult = minute == 0 ? " ":String.valueOf(minute)+"分钟";
		String data = String.valueOf(hours) + "小时" + minuteResult;
		return data;
	}
	
	public static String data2StringForReport(java.util.Date date){
		SimpleDateFormat ft = new SimpleDateFormat("HH:mm:ss");
		return ft.format(date);
	}

}
