package com.asm.util;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.servlet.http.HttpServletRequest;

public class ParamService {
	@Autowired
	HttpServletRequest request;
	
	public String getString(String name, String value) {
		return value;
	}
}
