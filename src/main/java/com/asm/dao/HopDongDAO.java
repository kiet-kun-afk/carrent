package com.asm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.asm.entity.HopDong;

import java.util.Date;

public interface HopDongDAO extends JpaRepository<HopDong, Integer> {
	// @Query(value = "select *from hopdong where ngayTao = CONVERT(DATE, GETDATE())
	// ",nativeQuery = true)
	// List<HopDong> listHopDong();

	@Query(value = "select o FROM HopDong o where o.ngayTao = ?1 ")
	List<HopDong> findkw(Date key);
	// @Query(value="select o.ngayTao from HopDong o group by o.ngayTao")
	// List<HopDong> listHopDong();

	List<HopDong> findByNhanVienIsNull();

	List<HopDong> findByNhanVienIsNotNull();

}
