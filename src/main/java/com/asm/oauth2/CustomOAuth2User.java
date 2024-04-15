package com.asm.oauth2;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomOAuth2User implements OAuth2User{

	private OAuth2User oauth2User;
	private String clientName;
	

	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return oauth2User.getAttributes();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return oauth2User.getAuthorities();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return getEmail();
	}
	
	
	public String getEmail() {
		return oauth2User.getAttribute("email");
	}
	
	public String getPhoto() {
		return oauth2User.getAttribute("picture");
	}
	
	public String getclientName() {
		return this.clientName;
	}
	
	public String getFullname(){
		return oauth2User.getAttribute("name");
	}

	public boolean getRoles(){
		return false;
	}

}
