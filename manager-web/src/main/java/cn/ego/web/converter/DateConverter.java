package cn.ego.web.converter;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
/**
 * springmvc的时间装换器
 * 将时间毫秒值转化成Date对象
 * @author 起灬風了
 *
 */
public class DateConverter implements Converter<String, Date>{

	@Override
	public Date convert(String source) {
		long time = Long.parseLong(source);
		return new Timestamp(time);
	}

}
