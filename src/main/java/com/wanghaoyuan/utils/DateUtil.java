package com.wanghaoyuan.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * @Title: format   
	 * @Description: ʱ���ʽ��  
	 * @param: @param theDate
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String format(Date theDate) {
		return dateFormat.format(theDate);
	}
	
	/**
	 * �������ռ�������
	 * @param birthDate
	 * @return
	 */
	public static int getAge(Date birthDate) {
		//��������ؼ�
		Calendar calendar = Calendar.getInstance();
		//����ꡢ�¡���
		int nowYear = calendar.get(Calendar.YEAR);
		int nowMonth = calendar.get(Calendar.MONTH);
		int nowDay = calendar.get(Calendar.DAY_OF_MONTH);
		//���������ؼ�Ϊ���յ�ʱ��
		calendar.setTime(birthDate);
		int birthYear = calendar.get(Calendar.YEAR);
		int birthMonth = calendar.get(Calendar.MONTH);
		int birthDay = calendar.get(Calendar.DAY_OF_MONTH);
		//��������
		int age = nowYear-birthYear;
		//������յ��·ݴ��ڵ�ǰ�·�ʱ������-1
		if(birthMonth>nowMonth) {
			age--;
		}
		//����·���ȣ��ж�����
		if(birthMonth==nowMonth && nowDay<birthDay) {
			age--;
		}
		return age;
	}
	/**
	 * ���ݳ������ڼ�������
	 * @param birthDateStr "2019-11-08"
	 * @return
	 */
	public static int getAge(String birthDateStr) {
		Date birthDate = null;
		try {
			//���������ַ���ΪDate����
			birthDate = dateFormat.parse(birthDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//�������ڼ��㷽��
		return getAge(birthDate);
	}
	/**
	 * @Title: getDayNum   
	 * @Description: ��ȡ��ʼ���ںͽ�������֮���ж�����   
	 * @param: @param startDate
	 * @param: @param endDate
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public static int getDayNum(Date date1,Date date2) {
		//һ���ж��ٺ���
		Long dayTime = 1000*60*60*24L;
		Long startTime = date1.getTime();
		Long endTime = date2.getTime();
//		System.out.println(startTime);
//		System.out.println(endTime);
		Double dayNum = Math.abs(((endTime-startTime)/dayTime*1.0));
//		dayNum = Math.ceil(dayNum);
//		System.out.println(dayNum);
		return dayNum.intValue()+1;
	}
	/**
	 * @Title: getDayNum   
	 * @Description: ����ָ�����ھ�����죬��ȥ�˶�������ж�����   
	 * @param: @param date
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public static int getDayNum(Date date) {
		Date date2 = new Date();
		return getDayNum(date,date2);
	}
	/**
	 * @Title: isToday   
	 * @Description: ��ָ֤�������Ƿ�Ϊ����   
	 * @param: @param theDate
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public static boolean isToday(Date theDate) {
		Date nowDate = new Date();
		String nowDateStr = dateFormat.format(nowDate);
		String theDateStr = dateFormat.format(theDate);
		return nowDateStr.equals(theDateStr);
	}
	/**
	 * @Title: isToday   
	 * @Description: ��ָ֤�������Ƿ�Ϊ����    
	 * @param: @param theDateStr "2019-11-30"
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public static boolean isToday(String theDateStr) {
		try {
			Date theDate = dateFormat.parse(theDateStr);
			return isToday(theDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * @Title: isInWeek   
	 * @Description: �ж�ָ�������Ƿ��ڱ���   
	 * @param: @param theDate
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public static boolean isInWeek(Date theDate) {
		Date nowDate = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(nowDate);
		//���ܵĵڼ���
		int dayofweek = c.get(Calendar.DAY_OF_WEEK);
		//���ñ��ܵ�һ���ʱ��
		c.add(Calendar.DAY_OF_YEAR, 1-dayofweek);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		Date firstDate = c.getTime();
		System.out.println(dateTimeFormat.format(firstDate));
		//���ñ������һ���ʱ��
		c.add(Calendar.DAY_OF_YEAR, 6);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date lastDate = c.getTime();
		System.out.println(dateTimeFormat.format(theDate));
		System.out.println(dateTimeFormat.format(lastDate));
		return compareTime(theDate,firstDate)>=0 && compareTime(theDate,lastDate)<=0;
	}
	/**
	 * @Title: getFirstDateInMonth   
	 * @Description: ��ȡָ�������·ݵĵ�һ�� 
	 * 2019-12-04 12:22:45  -> 2019-12-01 00:00:00
	 * @param: @param theDate
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public static Date getFirstDateInMonth(Date theDate) {
		/*SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-01 00:00:00");
		String dateStr = format.format(theDate);
		try {
			return format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
		Calendar c = Calendar.getInstance();
		c.setTime(theDate);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}
	/**
	 * @Title: getLastDateInMonth   
	 * @Description: ���ָ�����ڵ����һ��    
	 * 2019-12-04 12:22:45  -> 2019-12-31 23:59:59
	 * @param: @param theDate
	 * @param: @return      
	 * @return: Date      
	 * @throws
	 */
	public static Date getLastDateInMonth(Date theDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(theDate);
		c.add(Calendar.MONTH, 1);
		Date firstDateInMonth = getFirstDateInMonth(c.getTime());
		c.setTime(firstDateInMonth);
		c.add(Calendar.SECOND, -1);
		return c.getTime();
	}
	/**
	 * @Title: compareTime   
	 * @Description: TODO(�����������������)   
	 * @param: @param date1
	 * @param: @param date2
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public static int compareTime(Date date1,Date date2) {
		long time1 = date1.getTime();
		long time2 = date2.getTime();
		if(time1==time2) {
			return 0;
		}
		if(time1>time2) {
			return 1;
		}
		return -1;
		
	}
	
	public static void main(String[] args) throws ParseException {
		Date firstDateInMonth = getLastDateInMonth(new Date());
		System.out.println();
		Date theDate = dateFormat.parse("2019-12-20");
		System.out.println(isInWeek(theDate));
	}
}
