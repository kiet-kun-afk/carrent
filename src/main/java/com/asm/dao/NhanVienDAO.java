package com.asm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asm.entity.NhanVien;

public interface NhanVienDAO extends JpaRepository<NhanVien, String> {
	NhanVien findByEmail(String email);
	NhanVien findByMaNV(String maNV);
}
