package com.mcoding.emis.goods.common.utils.jackson;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomDateTimeDeSerializer extends JsonDeserializer<Date> {

	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public Date deserialize(JsonParser jsonParser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		Date date = null;
		try {
			date = simpleDateFormat.parse(jsonParser.getText());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

}
