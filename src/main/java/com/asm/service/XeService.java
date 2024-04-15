package com.asm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asm.dao.XeDAO;
import com.asm.entity.Xe;

@Service
public class XeService {

	@Autowired
	private XeDAO xeDao;
	
	public List<Xe> getRandomCars() {
        return xeDao.findRandomRecords();
    }
	
	public Xe getInfoXe(String bienSo) {
		return xeDao.findById(bienSo).get();
	}
}
