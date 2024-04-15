package com.asm.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.asm.dao.KhachHangDAO;
import com.asm.dao.NhanVienDAO;
import com.asm.entity.KhachHang;
import com.asm.entity.NhanVien;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    KhachHangDAO khachHangDAO;

    @Autowired
    NhanVienDAO nhanVienDAO;

    public MyUserDetailsService(KhachHangDAO khachHangDAO, NhanVienDAO nhanVienDAO) {
        this.khachHangDAO = khachHangDAO;
        this.nhanVienDAO = nhanVienDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        KhachHang khachHang = khachHangDAO.findByEmail(username);
        if (khachHang == null) {
            NhanVien nhanVien = nhanVienDAO.findByMaNV(username);
            return NhanVienRoot.create(nhanVien);
        }
        return KhachHangRoot.create(khachHang);
    }

}
