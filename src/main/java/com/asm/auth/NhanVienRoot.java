package com.asm.auth;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.asm.entity.NhanVien;

import lombok.Builder;
import lombok.Data;

@SuppressWarnings("serial")
@Data
@Builder
public class NhanVienRoot implements UserDetails, OAuth2User {	
    private NhanVien nhanVien;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    public static NhanVienRoot create(NhanVien nhanVien){
        return NhanVienRoot.builder().nhanVien(nhanVien).build();
    }

    public static NhanVienRoot create(NhanVien nhanVien, Map<String, Object> attributes){
        NhanVienRoot nhanVienRoot = NhanVienRoot.create(nhanVien);
        nhanVienRoot.setAttributes(attributes);
        return nhanVienRoot;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getName() {
        return this.nhanVien.getTenNV();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.nhanVien.getMatKhau();
    }

    @Override
    public String getUsername() {
        return this.nhanVien.getMaNV();
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
		return this.nhanVien.getAvatar();
	}

	public String getId() {
		return this.nhanVien.getMaNV();
	}

	public String getEmail() {
		return this.nhanVien.getEmail();
	}

	public String getFullname() {
        return this.nhanVien.getTenNV();
    }

    public boolean getRoles(){
		return true;
	}
}

