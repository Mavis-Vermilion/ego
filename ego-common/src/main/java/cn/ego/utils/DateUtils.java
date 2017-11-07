package cn.ego.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 对时间对象操作的工具类
 * @author 起灬風了
 *
 */
public class DateUtils {
	/**
	 * 将字符串转化为Date戳对象
	 * @param pattern要格式化的字符串格式
	 * @param source要格式化的时间字符串
	 * @return date时间对象
	 */
	public static Date formatString(String pattern,String source){
		Date date=null;
		SimpleDateFormat  simpledateformat=new SimpleDateFormat(pattern);
		try {
			date = simpledateformat.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * 将字符串转化为时间戳对象
	 * @param pattern要格式化的字符串格式
	 * @param source要格式化的时间字符串
	 * @return Timestamp时间戳对象
	 */
	public static Timestamp formatDateString(String pattern,String source){
		Timestamp timestamp=null;
		Date date = formatString(pattern, source);
		long time=date.getTime();
		timestamp=new Timestamp(time);
		return timestamp;
	}
	
	/**
	 * 根据指定的格式对当前时间进行格式化并返回字符串的形式
	 * @param pattern指定的格式化格式
	 * @return
	 */
	public static String getCurrentDateTime(String pattern){
		SimpleDateFormat simpledateformat=new SimpleDateFormat(pattern);
		Date date=new Date();
		String dateSrt = simpledateformat.format(date);
		return dateSrt;
		
	}
}
