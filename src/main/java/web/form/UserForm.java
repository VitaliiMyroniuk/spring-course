package web.form;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class UserForm {

	private final static String DATE_PATTERN = "yyyy-MM-dd";

	private String email;
	private String name;
	@DateTimeFormat(pattern = DATE_PATTERN)
	private LocalDate birthday;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

}
