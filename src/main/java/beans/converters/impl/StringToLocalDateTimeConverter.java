package beans.converters.impl;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {

	private static final String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm";

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

	@Override
	public LocalDateTime convert(String localDateTime) {
		return LocalDateTime.parse(localDateTime, formatter);
	}

}
