package com.fsd.pojo;

import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.util.Assert;

@Embeddable
public class EmailAddress {
	private static final String EMAIL_REGEX = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-ZA-Z]{2,})$";
	private static final Pattern PATTERN = Pattern.compile(EMAIL_REGEX);

	@Column(name = "email")
	private String value;

	protected EmailAddress() {

	}

	public EmailAddress(String emailAdddress) {
		Assert.isTrue(isValid(emailAdddress), "Invalid email address");
		this.value = emailAdddress;
	}

	public static boolean isValid(String candidate) {
		return candidate == null ? false : PATTERN.matcher(candidate).matches();
	}
}
