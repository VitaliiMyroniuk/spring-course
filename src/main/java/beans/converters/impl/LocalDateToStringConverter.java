package beans.converters.impl;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateToStringConverter implements Converter<LocalDate, String> {

	private final static String DATE_PATTERN = "yyyy-MM-dd";

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);

	@Override
	public String convert(LocalDate localDate) {
		return localDate.format(formatter);
	}

}
