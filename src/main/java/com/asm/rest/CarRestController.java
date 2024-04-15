package com.asm.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.asm.dao.XeDAO;
import com.asm.entity.*;






@CrossOrigin("*") // dùng để chạy trên nhiều domain
@RestController
public class CarRestController {
	
		@Autowired
		XeDAO xedao;
		
		@GetMapping("/rest/car")
		public List<Xe> getAll(Model model){
			return xedao.findAll();
		}
	
		@GetMapping("/rest/car/{bienSo}")
		public Xe getOne(@PathVariable("bienSo") String bienSo) {
			return xedao.findById(bienSo).get();
		}
		
		@PostMapping("/rest/car")
		public Xe post(@RequestBody Xe xe) {
			xedao.save(xe);
			return xe;
		}
		
		@PutMapping("/rest/car/{bienSo}")
		public Xe put(@PathVariable("bienSo") String bienSo , @RequestBody Xe xe) {
			xedao.save(xe);
			return xe;
		}
		
		@DeleteMapping("/rest/car/{bienSo}")
		public void delete (@PathVariable("bienSo") String bienSo) {
			xedao.deleteById(bienSo);
		}
}
