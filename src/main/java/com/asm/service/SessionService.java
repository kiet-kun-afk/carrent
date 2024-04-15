package com.asm.service;

public interface SessionService {
	//public Object get(String key); 
	//public void set(String key, Object value); 
	
	public void setAttribute(String key, Object value);
	public <T> T getAttribute(String key);
	public void removeAttribute(String key);
}
