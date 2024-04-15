package com.asm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asm.dao.HopDongDAO;
import com.asm.dao.KhachHangDAO;
import com.asm.entity.HopDong;
import com.asm.entity.NhanVien;
import com.asm.service.SessionService;

@Controller
@RequestMapping("contract")
public class HopDongController {

	@Autowired
	KhachHangDAO khachHangDAO;

	@Autowired
	HopDongDAO hopDongDAO;

	@Autowired
	SessionService session;

	@RequestMapping("rent")
	public String clickThue(Model model, RedirectAttributes redirectAttributes,
			@ModelAttribute("contract") HopDong hopDong) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication.getPrincipal() == "anonymousUser") {
			redirectAttributes.addFlashAttribute("uNeedLogin", "Hãy đăng nhập");
			return "redirect:/detail?id=" + hopDong.getXe().getBienSo();
		}
		if (sdf.format(hopDong.getNgayKetThuc()).equals(sdf.format(new Date()))) {
			redirectAttributes.addFlashAttribute("uNeedLogin", "Chọn lại ngày");
			return "redirect:/detail?id=" + hopDong.getXe().getBienSo();
		}
		System.out.println(authentication.getName());
		hopDong.setKhachHang(khachHangDAO.findByEmail(authentication.getName()));
		SimpleDateFormat sdfdmy = new SimpleDateFormat("dd/MM/yyyy");
		model.addAttribute("createDate", sdfdmy.format(hopDong.getNgayTao()));

		model.addAttribute("addressA", hopDong.getXe().getTruSo().getDuong() + ", " + hopDong.getXe().getTruSo().getXa()
				+ ", " + hopDong.getXe().getTruSo().getHuyen() + ", " + hopDong.getXe().getTruSo().getTinh());
		model.addAttribute("phoneNumberA", hopDong.getXe().getTruSo().getSoDienThoai());
		model.addAttribute("emailAddressA", hopDong.getXe().getTruSo().getEmail());

		model.addAttribute("addressB", hopDong.getKhachHang().getDuong() + hopDong.getKhachHang().getXa()
				+ hopDong.getKhachHang().getHuyen() + hopDong.getKhachHang().getTinh());
		model.addAttribute("phoneNumberB", hopDong.getKhachHang().getSoDienThoai());
		model.addAttribute("emailAddressB", hopDong.getKhachHang().getEmail());
		model.addAttribute("nameB", hopDong.getKhachHang().getTenKH());

		model.addAttribute("nameCar", hopDong.getXe().getTenXe());
		model.addAttribute("licensePlates", hopDong.getXe().getBienSo());
		model.addAttribute("pricePerDay", hopDong.getGiaThue());
		model.addAttribute("startDate", sdfdmy.format(hopDong.getNgayBatDau()));
		model.addAttribute("endDate", sdfdmy.format(hopDong.getNgayKetThuc()));
		model.addAttribute("totalPrice", hopDong.getTongTien());
		session.setAttribute("hopDong", hopDong);
		return "success";
	}

	@RequestMapping("confirm")
	public String confirmRent(Model model) {
		HopDong hopDong = session.getAttribute("hopDong");
		hopDongDAO.save(hopDong);
		return "redirect:/car/index";
	}

	@RequestMapping("list-pre-contract")
	public String listPreContract(Model model) {
		List<HopDong> list = hopDongDAO.findByNhanVienIsNull();
		model.addAttribute("listContract", list);
		return "contract/list-pre-contract";
	}

	@RequestMapping("list-post-contract")
	public String listPostContract(Model model) {
		List<HopDong> list = hopDongDAO.findByNhanVienIsNotNull();
		model.addAttribute("listContract", list);
		return "contract/list-post-contract";
	}

	@RequestMapping("view-contract")
	public String viewContract(Model model, @RequestParam("id") Integer maHopDong) {
		HopDong hopDong = hopDongDAO.findById(maHopDong).get();
		SimpleDateFormat sdfdmy = new SimpleDateFormat("dd/MM/yyyy");
		model.addAttribute("createDate", sdfdmy.format(hopDong.getNgayTao()));

		model.addAttribute("addressA", hopDong.getXe().getTruSo().getDuong() + ", " + hopDong.getXe().getTruSo().getXa()
				+ ", " + hopDong.getXe().getTruSo().getHuyen() + ", " + hopDong.getXe().getTruSo().getTinh());
		model.addAttribute("phoneNumberA", hopDong.getXe().getTruSo().getSoDienThoai());
		model.addAttribute("emailAddressA", hopDong.getXe().getTruSo().getEmail());

		model.addAttribute("addressB", hopDong.getKhachHang().getDuong() + hopDong.getKhachHang().getXa()
				+ hopDong.getKhachHang().getHuyen() + hopDong.getKhachHang().getTinh());
		model.addAttribute("phoneNumberB", hopDong.getKhachHang().getSoDienThoai());
		model.addAttribute("emailAddressB", hopDong.getKhachHang().getEmail());
		model.addAttribute("nameB", hopDong.getKhachHang().getTenKH());

		model.addAttribute("nameCar", hopDong.getXe().getTenXe());
		model.addAttribute("licensePlates", hopDong.getXe().getBienSo());
		model.addAttribute("pricePerDay", hopDong.getGiaThue());
		model.addAttribute("startDate", sdfdmy.format(hopDong.getNgayBatDau()));
		model.addAttribute("endDate", sdfdmy.format(hopDong.getNgayKetThuc()));
		model.addAttribute("totalPrice", hopDong.getTongTien());
		model.addAttribute("idContract", hopDong.getMaHopDong());
		session.setAttribute("hopDong", hopDong);
		return "contract/view-contract";
	}

	@RequestMapping("add-user")
	public String addUserToContract(@RequestParam("id") Integer maHopDong) {
		HopDong hopDong = hopDongDAO.findById(maHopDong).get();
		NhanVien nhanVien = session.getAttribute("nvAccount");
		hopDong.setNhanVien(nhanVien);
		hopDongDAO.save(hopDong);
		return "redirect:/contract/list-pre-contract";
	}
}
