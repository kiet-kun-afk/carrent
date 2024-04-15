package com.asm.util;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailInfo {

	String from;
	@NotBlank(message = "Email Không Được Để Trống")
	String to;
	// String[] cc;
	String subject;
	String body;
	// List<File> files=new ArrayList<>();

	public MailInfo(String to, String subject, String body) {
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
}
