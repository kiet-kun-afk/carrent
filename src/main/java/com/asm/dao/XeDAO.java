package com.asm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.asm.entity.Xe;

public interface XeDAO extends JpaRepository<Xe, String> {
	@Query(value = "SELECT TOP 4 * FROM xe ORDER BY NEWID()", nativeQuery = true)
	List<Xe> findRandomRecords();

	@Query("SELECT o FROM Xe o WHERE o.tenXe LIKE ?1")
	List<Xe> findByHangXe(String keywords);

	@Query("SELECT o FROM Xe o WHERE o.loaiXe.maLX = ?1")
	List<Xe> findByLoaiXe(String keywords);

	@Query("SELECT o FROM Xe o WHERE o.nhienLieu LIKE ?1")
	List<Xe> findByXeDien(String keywords);

	@Query("SELECT o FROM Xe o WHERE o.truyenDong LIKE ?1")
	List<Xe> findByTruyenDong(String keywords);

	@Query("SELECT o FROM Xe o WHERE o.trangThai = ?1")
	List<Xe> findByXeChuaThue(Boolean keywords);

	// @Query("SELECT new Report(o.hangXe , count(o))"
	// + "FROM Xe o Group By o.hangXe Order By count(o) DESC ")
	// List<Report> getReportByHangXe();
}
