package com.asm.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.asm.dao.HopDongDAO;
import com.asm.dao.XeDAO;
import com.asm.dao.lsTraXeDAO;
import com.asm.entity.HopDong;
import com.asm.entity.Xe;
import com.asm.entity.lsTraXe;
import com.asm.helper.excelHelper;

@Controller
public class qlTraXeController {

	@Autowired
	lsTraXeDAO lsDAO;

	@Autowired
	HopDongDAO hopDongDAO;

	@Autowired
	XeDAO xeDAO;

	@RequestMapping("/car/qlTraXe")
	public String qllsTraXe(Model model) {
		lsTraXe lsTX = new lsTraXe();
		model.addAttribute("traxemodel", lsTX);
		return "TraXe/qlTraXe";
	}

	@RequestMapping("/car/qlTraXe/add")
	public String qlTraxeAdd(Model model, @ModelAttribute("traxemodel") lsTraXe txone,
			@RequestParam("id") Integer maHD) {

		boolean flag = true;

		if (txone.getNgayNhanxe() == null || txone.getNgayNhanxe().equals("")) {
			model.addAttribute("errorMessageD", "Vui lòng nhập ngày!");
			flag = false;
		}
		if (txone.getGiayToxe() == null || txone.getGiayToxe().equals("")) {
			model.addAttribute("errorMessageG", "Vui lòng không bỏ trống!");
			flag = false;
		}
		if (txone.getTinhTrang() == null || txone.getTinhTrang().equals("")) {
			model.addAttribute("errorMessageT", "Vui lòng không bỏ trống!");
			flag = false;
		}
		if (txone.getNgoaiThat() == null || txone.getNgoaiThat().equals("")) {
			model.addAttribute("errorMessageNN", "Vui lòng không bỏ trống!");
			flag = false;
		}
		if (txone.getNoiThat() == null || txone.getNoiThat().equals("")) {
			model.addAttribute("errorMessageNT", "Vui lòng không bỏ trống!");
			flag = false;
		}
		if (txone.getDongCo() == null || txone.getDongCo().equals("")) {
			model.addAttribute("errorMessageDC", "Vui lòng không bỏ trống!");
			flag = false;
		}
		if (txone.getNoiDung() == null || txone.getNoiDung().equals("")) {
			model.addAttribute("errorMessageND", "Vui lòng không bỏ trống!");
			flag = false;
		}

		HopDong hopdong = hopDongDAO.findById(maHD).get();
		Xe xe = xeDAO.findById(hopdong.getXe().getBienSo()).get();

		if (flag == false) {
			model.addAttribute("hd", hopdong);
			return "TraXe/qlTraXe";
		} else {

			xe.setTrangThai(false);
			txone.setHd(hopdong);

			xeDAO.save(xe);
			lsDAO.save(txone);

			List<lsTraXe> lsTX = lsDAO.findAll();
			model.addAttribute("dsTraXe", lsTX);
			return "redirect:/car/listTraXe";
		}

	}

	@RequestMapping("/car/qlTraXe/edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		HopDong hopdong = hopDongDAO.findById(id).get();
		lsTraXe lsTX = new lsTraXe();
		try {
			lsTX = lsDAO.findByHopDong(hopdong);
		} catch (Exception e) {
		}
		if (lsTX == null) {
			lsTX = new lsTraXe();
		}
		model.addAttribute("traxemodel", lsTX);
		model.addAttribute("hd", hopdong);
		return "TraXe/qlTraXe";
	}

	@RequestMapping("/car/listTraXe")
	public String listTraXe(Model model) {
		List<lsTraXe> lsTX = lsDAO.findAll();
		model.addAttribute("dsTraXe", lsTX);
		return "TraXe/listTraXe";
	}

	@RequestMapping("/car/listTraXe/export")
	public String xuatExcel(Model model) throws IOException {
		List<lsTraXe> lsTX = lsDAO.findAll();
		excelHelper.exportlsTraXe(lsTX);
		model.addAttribute("dsTraXe", lsTX);
		return "TraXe/listTraXe";
	}
}
