package beans.converters.impl;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StringToLocalDateConverter implements Converter<String, LocalDate> {

	private final static String DATE_PATTERN = "yyyy-MM-dd";

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);

	@Override
	public LocalDate convert(String localDate) {
		return LocalDate.parse(localDate, formatter);
	}

}
