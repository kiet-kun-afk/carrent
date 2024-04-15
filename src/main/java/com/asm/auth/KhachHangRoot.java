package com.asm.auth;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.asm.entity.KhachHang;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KhachHangRoot implements UserDetails, OAuth2User {
	private KhachHang khachHang;
	private Collection<? extends GrantedAuthority> authorities;
	private Map<String, Object> attributes;

	public static KhachHangRoot create(KhachHang khachHang) {

		return KhachHangRoot.builder().khachHang(khachHang).build();
	}

	public static KhachHangRoot create(KhachHang khachHang, Map<String, Object> attributes) {
		KhachHangRoot khachHangRoot = KhachHangRoot.create(khachHang);
		khachHangRoot.setAttributes(attributes);
		return khachHangRoot;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	@Override
	public String getName() {
		return this.khachHang.getTenKH();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.khachHang.getMatKhau();
	}

	@Override
	public String getUsername() {
		return this.khachHang.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getPhoto() {
		return this.khachHang.getAvatar();
	}

	public Integer getId() {
		return this.khachHang.getMaKH();
	}

	public String getEmail() {
		return this.khachHang.getEmail();
	}

	public String getFullname() {
        return this.khachHang.getTenKH();
    }

	public boolean getRoles(){
		return false;
	}

}
