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
import com.asm.dao.GiaoXeDAO;
import com.asm.dao.XeDAO;
import com.asm.entity.HopDong;
import com.asm.entity.GiaoXe;
import com.asm.entity.Xe;
import com.asm.helper.excelHelper;
import com.asm.service.SessionService;

@Controller
public class GiaoXeController {

	@Autowired
	SessionService session;
	@Autowired
	HopDongDAO hopDongDAO;
	@Autowired
	XeDAO xeDAO;
	@Autowired
	GiaoXeDAO NXDAO;

	@RequestMapping("/car/giaoxe")
	public String qllsTraXe(Model model) {
		GiaoXe nxe = new GiaoXe();
		model.addAttribute("giaoxemodel", nxe);
		return "giaoxe/bbgiaoxe";
	}

	@RequestMapping("/car/giaoxe/add")
	public String qlTraxeAdd(Model model, @ModelAttribute("giaoxemodel") GiaoXe giaoxeone,
			@RequestParam("id") Integer maHD) {

		boolean flag = true;

		if (giaoxeone.getNgayGiaoXe() == null || giaoxeone.getNgayGiaoXe().equals("")) {
			model.addAttribute("errorMessageD", "Vui lòng nhập ngày!");
			flag = false;
		}
		if (giaoxeone.getGiayToXe() == null || giaoxeone.getGiayToXe().equals("")) {
			model.addAttribute("errorMessageG", "Vui lòng không bỏ trống!");
			flag = false;
		}
		if (giaoxeone.getTinhTrang() == null || giaoxeone.getTinhTrang().equals("")) {
			model.addAttribute("errorMessageT", "Vui lòng không bỏ trống!");
			flag = false;
		}
		if (giaoxeone.getNgoaiThat() == null || giaoxeone.getNgoaiThat().equals("")) {
			model.addAttribute("errorMessageNN", "Vui lòng không bỏ trống!");
			flag = false;
		}
		if (giaoxeone.getNoiThat() == null || giaoxeone.getNoiThat().equals("")) {
			model.addAttribute("errorMessageNT", "Vui lòng không bỏ trống!");
			flag = false;
		}
		if (giaoxeone.getDongCo() == null || giaoxeone.getDongCo().equals("")) {
			model.addAttribute("errorMessageDC", "Vui lòng không bỏ trống!");
			flag = false;
		}
		if (giaoxeone.getNoiDung() == null || giaoxeone.getNoiDung().equals("")) {
			model.addAttribute("errorMessageND", "Vui lòng không bỏ trống!");
			flag = false;
		}

		HopDong hopdong = hopDongDAO.findById(maHD).get();
		Xe xe = xeDAO.findById(hopdong.getXe().getBienSo()).get();

		if (flag == false) {
			model.addAttribute("hd", hopdong);
			return "giaoxe/bbgiaoxe";
		} else {
			giaoxeone.setHopDong(hopdong);
			xe.setTrangThai(true);

			xeDAO.save(xe);
			NXDAO.save(giaoxeone);

			List<GiaoXe> nxe = NXDAO.findAll();
			model.addAttribute("dsnxe", nxe);
			return "redirect:/car/Listgiaoxe";
		}

	}

	@RequestMapping("/car/giaoxe/edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		HopDong hopdong = hopDongDAO.findById(id).get();
		GiaoXe nxe = new GiaoXe();
		try {
			nxe = NXDAO.findByHopDong(hopdong);
		} catch (Exception e) {
		}
		if (nxe == null) {
			nxe = new GiaoXe();
		}
		model.addAttribute("giaoxemodel", nxe);
		model.addAttribute("hd", hopdong);
		return "giaoxe/bbgiaoxe";
	}

	@RequestMapping("/car/Listgiaoxe")
	public String listTraXe(Model model) {
		List<GiaoXe> nxe = NXDAO.findAll();
		model.addAttribute("dsnxe", nxe);
		return "giaoxe/Listgiaoxe";
	}

	@RequestMapping("/car/Listgiaoxe/export")
	public String xuatExcel(Model model) throws IOException {
		List<GiaoXe> nxe = NXDAO.findAll();
		excelHelper.exportlsGiaoXe(nxe);
		model.addAttribute("dsnxe", nxe);
		return "giaoxe/Listgiaoxe";
	}
}
