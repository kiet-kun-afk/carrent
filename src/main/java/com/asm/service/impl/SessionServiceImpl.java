package com.asm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asm.service.SessionService;
import com.asm.util.Session;

import jakarta.servlet.http.HttpSession;

@Service
public class SessionServiceImpl implements SessionService {

	@Autowired
	HttpSession session;

	public static List<Session> listSession = new ArrayList<>();

	@Override
	public void setAttribute(String name, Object value) {
		session.setAttribute(name, value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getAttribute(String key) {
		if (session.getAttribute(key) != null)
			return (T) session.getAttribute(key);
		else
			return null;
	}

	@Override
	public void removeAttribute(String name) {
		session.removeAttribute(name);
	}

}
