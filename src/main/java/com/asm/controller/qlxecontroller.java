package com.asm.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.asm.dao.XeDAO;
import com.asm.entity.Xe;
import com.asm.service.SessionService;

@Controller
public class qlxecontroller {
	@Autowired
	SessionService session;
	@Autowired
	XeDAO dao;

	@RequestMapping("/car/dsxe")
	public String index(Model model) {
		Xe item = new Xe();
		model.addAttribute("item", item);
		List<Xe> items = dao.findAll();
		model.addAttribute("items", items);
		return "qlxe";
	}

	@RequestMapping("/qlxe/findhx")
	public String findhxe(Model model, @RequestParam("keywords") Optional<String> kw) {
		String kwords = kw.orElse((String) session.getAttribute("keywords"));
		session.setAttribute("keywords", kwords);
		List<Xe> items = dao.findByHangXe("%" + kwords + "%");
		model.addAttribute("items", items);
		return "qlxe";
	}

//	  @RequestMapping("/qlxe/findlx")
//	  public String findlxe (Model model) {
//			List<FindLX> loai = dao.getInventoryByLoaiXe();
//			model.addAttribute("loai", loai);
//			return "qlxe";
	// }
	@RequestMapping("/qlxe/findlx")
	public String findlxe(Model model, @RequestParam("keywords") Optional<String> kw) {
		String kwords = kw.orElse((String) session.getAttribute("keywords"));
		session.setAttribute("keywords", kwords);
		List<Xe> items = dao.findByLoaiXe(kwords);
		model.addAttribute("items", items);
		return "qlxe";
	}

	@RequestMapping("/qlxe/findxedien")
	public String findxedien(Model model, @RequestParam("keywords") Optional<String> kw) {
		String kwords = kw.orElse((String) session.getAttribute("keywords"));
		session.setAttribute("keywords", kwords);
		List<Xe> items = dao.findByXeDien("%" + kwords + "%");
		model.addAttribute("items", items);
		return "qlxe";
	}
	@RequestMapping("/qlxe/findtruyendong")
	public String findtruyendong(Model model, @RequestParam("keywords") Optional<String> kw) {
		String kwords = kw.orElse((String) session.getAttribute("keywords"));
		session.setAttribute("keywords", kwords);
		List<Xe> items = dao.findByTruyenDong("%" + kwords + "%");
		model.addAttribute("items", items);
		return "qlxe";
	}

	@RequestMapping("/qlxe/findxechuathue")
	public String findfindxechuathue(Model model, @RequestParam("keywords") Optional<String> kw) {
		String kwords = kw.orElse((String) session.getAttribute("keywords"));
		session.setAttribute("keywords", kwords);
		List<Xe> items = dao.findByXeChuaThue(false);
		model.addAttribute("items", items);
		return "qlxe";
	}

	@RequestMapping("/qlxe/asc")
	public String index(Model model, @RequestParam("field") Optional<String> field) {
		Sort sort = Sort.by(Direction.ASC, field.orElse("name"));
		model.addAttribute("field", field.orElse("name"));
		List<Xe> shoes = dao.findAll(sort);
		model.addAttribute("items", shoes);
		return "qlxe";
	}

	@RequestMapping("/qlxe/desc")
	public String index1(Model model, @RequestParam("field") Optional<String> field) {
		Sort sort = Sort.by(Direction.DESC, field.orElse("name"));
		model.addAttribute("field", field.orElse("name"));
		List<Xe> items = dao.findAll(sort);
		model.addAttribute("items", items);
		return "qlxe";
	}
//	  @RequestMapping("/qlxe/page")
//		public String paginate(Model model, @RequestParam("page") Optional<Integer> page) {
//			Pageable pageable = PageRequest.of(page.orElse(0), 9);
//			Page<Xe> items= dao.findAll(pageable); 
//			model.addAttribute("page", items);
//			return "qlxe"; 
//		}

}
