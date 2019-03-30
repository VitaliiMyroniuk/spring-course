package web.form;

import org.springframework.format.annotation.DateTimeFormat;
import util.CsvUtil;

import java.time.LocalDateTime;
import java.util.List;

public class BookingForm {

	private static final String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm";

	private String eventName;
	@DateTimeFormat(pattern = DATE_TIME_PATTERN)
	private LocalDateTime eventDateTime;
	private String auditoriumName;
	private String userEmail;
	private String seats;

	public List<Integer> getSeatNumbers() {
		return CsvUtil.fromCsvToList(seats, Integer::valueOf);
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public LocalDateTime getEventDateTime() {
		return eventDateTime;
	}

	public void setEventDateTime(LocalDateTime eventDateTime) {
		this.eventDateTime = eventDateTime;
	}

	public String getAuditoriumName() {
		return auditoriumName;
	}

	public void setAuditoriumName(String auditoriumName) {
		this.auditoriumName = auditoriumName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getSeats() {
		return seats;
	}

	public void setSeats(String seats) {
		this.seats = seats;
	}

}
