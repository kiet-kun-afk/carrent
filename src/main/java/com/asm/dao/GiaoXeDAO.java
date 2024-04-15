package com.asm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.asm.entity.GiaoXe;
import com.asm.entity.HopDong;


public interface GiaoXeDAO extends JpaRepository<GiaoXe, Integer> {
    @Query("SELECT t FROM lsTraXe t WHERE hd = ?1")
    GiaoXe findByHopDong(HopDong hopDong);
}
