package com.asm.oauth2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.asm.auth.KhachHangRoot;
import com.asm.auth.NhanVienRoot;
import com.asm.dao.KhachHangDAO;
import com.asm.dao.NhanVienDAO;
import com.asm.entity.KhachHang;
import com.asm.entity.NhanVien;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private KhachHangDAO khDAO;

	@Autowired
	private NhanVienDAO nhanVienDAO;

	public CustomUserDetailService(KhachHangDAO khDAO, NhanVienDAO nhanVienDAO) {
		this.khDAO = khDAO;
		this.nhanVienDAO = nhanVienDAO;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		KhachHang kh = khDAO.findByEmail(username);
		if (kh == null) {
			NhanVien nhanVien = nhanVienDAO.findByMaNV(username);
			return NhanVienRoot.create(nhanVien);
		}
		List<SimpleGrantedAuthority> list = new ArrayList<>();

		List<SimpleGrantedAuthority> roleName = list;
		System.out.println(roleName);
		return KhachHangRoot.create(kh);
	}

}
