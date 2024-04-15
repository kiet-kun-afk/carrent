package com.asm.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.asm.dao.KhachHangDAO;
import com.asm.entity.KhachHang;
import com.asm.service.SessionService;

@Controller
public class ThongtinController {
	@Autowired
	KhachHangDAO dao;
	@Autowired
	SessionService session;

	@RequestMapping("/guest")
	public String index(Model model) {
		KhachHang guest = session.getAttribute("currentAccount");
		model.addAttribute("guests", guest);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		model.addAttribute("ngaySinh", sdf.format(guest.getNgaySinh()));
		return "thongtin";
	}

	@RequestMapping("/guest/edit")
	public String edit(Model model,
			@RequestParam("licenseName") String tenKH,
			@RequestParam("licenseFirstName") String hoKH,
			@RequestParam("licenseNumber") Integer soGPLX,
			@RequestParam("birthDate") String ngaySinh,
			@RequestParam("gender") String gioiTinh,
			@RequestParam("phoneNumber") String soDienThoai,
			@RequestParam("email") String email) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Boolean gender = true;
		if (gioiTinh.equalsIgnoreCase("nữ") || gioiTinh.equalsIgnoreCase("nu")) {
			gender = false;
		}
		KhachHang guest = session.getAttribute("currentAccount");

		guest.setNgaySinh(sdf.parse(ngaySinh));
		guest.setGioiTinh(gender);
		guest.setEmail(email);
		guest.setTenKH(tenKH);
		guest.setHoKH(hoKH);
		guest.setSoDienThoai(soDienThoai);
		guest.setSoGPLX(soGPLX);
		dao.save(guest);

		model.addAttribute("guests", guest);
		return "redirect:/guest";
	}
}
