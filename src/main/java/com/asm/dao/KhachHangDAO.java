package com.asm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asm.entity.KhachHang;

public interface KhachHangDAO extends JpaRepository<KhachHang, Integer> {
	KhachHang findByEmail(String email);
}
