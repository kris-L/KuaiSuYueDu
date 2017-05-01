package com.kris.kuaisuyuedu.adapter;

import java.io.IOException;   
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.kris.kuaisuyuedu.data.Const;

/**
 * 日期枚举类型适配器 转换功能：日期时间java.util.Date - 字符串java.lang.String(yyyyMMddHHmmss)
 * 
 * @author XiaOt
 * 
 */
public class DateTypeAdapter extends TypeAdapter<Date> {

	/**
	 * 日期时间格式：yyyy-MM-dd HH:mm:ss
	 */
	@SuppressLint("SimpleDateFormat")
	public static SimpleDateFormat format = new SimpleDateFormat(Const.FORMAT_SERVER_RESPONSE);

	@Override
	public Date read(JsonReader reader) throws IOException {
		if (reader.peek() == JsonToken.NULL) {
			reader.nextNull();
			return null;
		}

		Date date = null;
		try {
			date = format.parse(reader.nextString());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

	@Override
	public void write(JsonWriter writer, Date date) throws IOException {
		if (date == null) {
			writer.nullValue();
			return;
		}
		writer.value(format.format(date));
	}

}
