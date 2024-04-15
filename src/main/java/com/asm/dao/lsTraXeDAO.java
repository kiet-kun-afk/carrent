package com.asm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.asm.entity.HopDong;
import com.asm.entity.lsTraXe;

public interface lsTraXeDAO extends JpaRepository<lsTraXe, Integer>{

    @Query("SELECT t FROM lsTraXe t WHERE hd = ?1")
    lsTraXe findByHopDong(HopDong hopDong);
}
