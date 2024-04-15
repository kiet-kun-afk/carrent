package com.asm.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.asm.config.WebSecurityConfig;
import com.asm.dao.HangXeDAO;
import com.asm.dao.HopDongDAO;
import com.asm.dao.LoaiXeDAO;
import com.asm.dao.NhanVienDAO;
import com.asm.dao.TruSoDAO;
import com.asm.dao.XeDAO;
import com.asm.entity.HangXe;
import com.asm.entity.HopDong;
import com.asm.entity.LoaiXe;
import com.asm.entity.NhanVien;
import com.asm.entity.TruSo;
import com.asm.entity.Xe;
import com.asm.helper.excelHelper;
import com.asm.service.SessionService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AdminController {
	// @Autowired
	// CarDao xedao;

	@Autowired
	SessionService session;

	@Autowired
	XeDAO xedao;

	@Autowired
	HangXeDAO hangxedao;

	@Autowired
	LoaiXeDAO loaixedao;

	@Autowired
	TruSoDAO trusodao;

	@Autowired
	NhanVienDAO nvdao;

	@Autowired
	HopDongDAO hddao;

	@Autowired
	HttpServletRequest req;

	@Autowired
	WebSecurityConfig wconfig;

	SimpleDateFormat sm = new SimpleDateFormat("MM/dd/yyyy");
	private static final String SDT_Vali = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
	private static final String Email_Vali = "[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	private static final String Pass_Vali = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=]).{8,16}$";

	public void ClearForm(@ModelAttribute("carmodel") Xe car) {
		car.setBienSo("");
		car.setNgayDangKiem(null);
		car.setTenXe("");
		car.setGiaThue(null);
		car.setMoTa("");
		car.setSoCho(null);
		car.setTruyenDong(null);
		car.setImgDauXe("");
		car.setImgDuoiXe("");
		car.setImgSuonPXe("");
		car.setImgSuonTXe("");
	}

	public void ClearFormStaff(@ModelAttribute("staffmodel") NhanVien staff) {
		staff.setMaNV("");
		staff.setMatKhau("");
		staff.setHoNV("");
		staff.setTenNV("");
		staff.setGioiTinh(null);
		staff.setSoDienThoai("");
		staff.setNgaySinh(null);
		staff.setEmail("");
		staff.setTinh("");
		staff.setHuyen("");
		staff.setXa("");
		staff.setDuong("");
		staff.setChucVu(null);
		staff.setTrangThai(null);
	}

	// hàng xe
	@ModelAttribute("hangXes")
	public List<HangXe> gethangxe1() {
		List<HangXe> listhangxe = hangxedao.findAll();
		return listhangxe;
	}

	// loại xe
	@ModelAttribute("loaiXes")
	public List<LoaiXe> getloaixe1() {
		List<LoaiXe> listloaixe = loaixedao.findAll();
		return listloaixe;
	}

	// trụ sở
	@ModelAttribute("truSos")
	public List<TruSo> gettrusos() {
		List<TruSo> listtruso = trusodao.findAll();
		return listtruso;
	}

	@RequestMapping("car/home")
	public String homepage() {

		return "home";
	}

	@GetMapping("car/list")
	public String listCar(Model model) {

		List<Xe> cars = xedao.findAll();
		model.addAttribute("lscar", cars);
		return "listcar";
	}

	@GetMapping("car/add")
	public String indexadd(Model model, @ModelAttribute("carmodel") Xe car) {

		return "addcar";
	}

	@GetMapping("/car/sort")
	public String sortPrice(Model model, @RequestParam("field") Optional<String> field) {
		Sort sort = Sort.by(Direction.DESC, field.orElse("giaThue"));
		model.addAttribute("field", field.orElse("giaThue").substring(0, 1).toUpperCase()
				+ field.orElse("giaThue").substring(1, field.orElse("giaThue").length()));
		model.addAttribute("lscar", xedao.findAll(sort));

		return "listcar";
	}

	//
	@GetMapping("/car/edit/{bienSo}")
	public String editCar(Model model, @PathVariable("bienSo") String bienSo, @ModelAttribute("carmodel") Xe car) {
		if (xedao.findById(car.getBienSo()).isEmpty()) {
			return "redirect: /listcar";
		} else {
			Xe cars = xedao.findById(bienSo).get();
			car.setBienSo(cars.getBienSo());
			car.setTenXe(cars.getTenXe());
			car.setHangXe(cars.getHangXe());
			car.setLoaiXe(cars.getLoaiXe());
			car.setGiaThue(cars.getGiaThue());
			car.setSoCho(cars.getSoCho());
			car.setNgayDangKiem(cars.getNgayDangKiem());
			car.setTruyenDong(cars.getTruyenDong());
			car.setNhienLieu(cars.getNhienLieu());
			car.setNlTieuHao(cars.getNlTieuHao());
			car.setTienNghi(cars.getTienNghi());
			car.setTruSo(cars.getTruSo());
			car.setTrangThai(cars.getTrangThai());
			car.setImgDauXe(cars.getImgDauXe());
			car.setImgSuonTXe(cars.getImgSuonTXe());
			car.setImgSuonPXe(cars.getImgSuonPXe());
			car.setImgDuoiXe(cars.getImgDuoiXe());
			car.setMoTa(cars.getMoTa());

		}

		return "addcar";
	}

	@PostMapping("/car/create")
	public String addCar(Model model, @ModelAttribute("carmodel") Xe car) {
		boolean flag = true;

		if (flag == true) {
			if (!xedao.existsById(car.getBienSo())) {
				xedao.save(car);

				ClearForm(car);
				System.out.println("thêm thành công");
			} else {
				model.addAttribute("errorMessageBS", "Biển số đã tồn tại!");
			}

			return "addcar";
		} else {
			return "addcar";
		}

	}

	//
	@PostMapping("/car/update")
	public String updateCar(Model model, @ModelAttribute("carmodel") Xe car) {

		if (xedao.existsById(car.getBienSo())) {
			xedao.save(car);
			ClearForm(car);
			System.out.println("update thành công");
		} else {
			throw new RuntimeException("update không thành công");
		}

		return "addcar";
	}

	// @PostMapping("/car/delete")
	// public String deleteCar(Model model, @ModelAttribute("carmodel") Xe car ) {
	//
	// if(xedao.existsById(car.getBienSo())){
	// car.setNgayDangKiem(null);
	// xedao.deleteById(car.getBienSo());
	// //ClearForm(car);
	// System.out.println("xoá thành công");
	// }else {
	//// throw new RuntimeException("id này không tồn tại");
	// System.out.println("id này không tồn tại");
	// }
	//
	// return "addcar";
	// }

	// THEM NHAN VIEN

	@RequestMapping("car/staff")
	public String staff(Model model, @ModelAttribute("staffmodel") NhanVien staff) {

		return "staff/addstaff";
	}

	@RequestMapping("car/liststaff")
	public String liststaff(Model model) {
		// NhanVien nv = new NhanVien();
		List<NhanVien> nvs = nvdao.findAll();
		model.addAttribute("lsnv", nvs);

		return "staff/liststaff";
	}

	@PostMapping("/staff/create")
	public String addstaff(Model model, @ModelAttribute("staffmodel") NhanVien staff) {

		boolean flag = true;

		boolean ktSDT = staff.getSoDienThoai().matches(SDT_Vali);
		boolean ktEmail = staff.getEmail().matches(Email_Vali);
		boolean ktPass = staff.getMatKhau().matches(Pass_Vali);

		NhanVien findemailnv = nvdao.findByEmail(staff.getEmail());

		if (findemailnv == null || !findemailnv.getEmail().equalsIgnoreCase(staff.getEmail())) {
			flag = true;
		} else {
			model.addAttribute("errorMessageE", "Email đã tồn tại!");
			flag = false;
		}
		if (!ktEmail == true) {
			model.addAttribute("errorMessageE", "Nhập email hợp lệ!");
			flag = false;
		}
		// check mã
		// if(findmanv==null || !findmanv.getMaNV().equals(staff.getMaNV())) {
		// flag = true;
		// }else {
		// model.addAttribute("errorMessageM", "Mã nhân viên đã tồn tại!");
		// flag = false;
		// }
		if (staff.getMaNV() == null || staff.getMaNV().equals("")) {
			model.addAttribute("errorMessageM", "Nhập mã nhân viên!");
			flag = false;
		}
		// checkmk
		if (staff.getMatKhau() == null || staff.getMatKhau().equals("")) {
			model.addAttribute("errorMessageP", "Vui lòng nhập mật khẩu!");
			flag = false;
		}
		if (!ktPass == true) {
			model.addAttribute("errorMessageP",
					"Mật khẩu của bạn phải dài từ 8 đến 16 ký tự, phải chứa ít nhất 1 ký tự viết hoa, 1 ký tự viết thường, 1 ký tự số và 1 ký tự đặc biệt");
			flag = false;
		}
		// check ho
		if (staff.getHoNV() == null || staff.getHoNV().equals("")) {
			model.addAttribute("errorMessageH", "Vui lòng nhập họ!");
			flag = false;
		}
		// check tên
		if (staff.getTenNV() == null || staff.getTenNV().equals("")) {
			model.addAttribute("errorMessageT", "Vui lòng nhập tên!");
			flag = false;
		}
		// check giới tính
		if (staff.getGioiTinh() == null) {
			model.addAttribute("errorMessageG", "Vui lòng nhập chọn giới tính!");
			flag = false;
		}
		// check sdt
		if (staff.getSoDienThoai() == null || staff.getSoDienThoai().equals("")) {
			model.addAttribute("errorMessagePH", "Vui lòng nhập số điện thoại!");
			flag = false;
		}
		if (!ktSDT == true) {
			model.addAttribute("errorMessagePH", "Số điện thoại không hợp lệ!");
			flag = false;
		}
		// check ngày sinh
		if (staff.getNgaySinh() == null || staff.getNgaySinh().equals("")) {
			model.addAttribute("errorMessageB", "Vui lòng nhập ngày sinh!");
			flag = false;
		}
		// check thành phố
		if (staff.getTinh() == null || staff.getTinh().equals("")) {
			model.addAttribute("errorMessageTP", "Vui lòng nhập tỉnh!");
			flag = false;
		}
		if (staff.getHuyen() == null || staff.getHuyen().equals("")) {
			model.addAttribute("errorMessageQ", "Vui lòng nhập huyện!");
			flag = false;
		}
		if (staff.getXa() == null || staff.getXa().equals("")) {
			model.addAttribute("errorMessageX", "Vui lòng nhập xã!");
			flag = false;
		}
		if (staff.getDuong() == null || staff.getDuong().equals("")) {
			model.addAttribute("errorMessageS", "Vui lòng nhập đường!");
			flag = false;
		}
		// check chức vụ
		if (staff.getChucVu() == null) {
			model.addAttribute("errorMessageCV", "Vui lòng chọn chức vụ!");
			flag = false;
		}

		if (flag == true) {

			if (!nvdao.existsById(staff.getMaNV())) {
				String encodedPW = wconfig.passwordEncoder().encode(staff.getMatKhau());
				staff.setMatKhau(encodedPW);
				nvdao.save(staff);
				ClearFormStaff(staff);
				System.out.println("thêm thành công");
				return "staff/addstaff";
			} else {
				model.addAttribute("errorMessageM", "Mã nhân viên đã tồn tại!");

			}
			return "staff/addstaff";

		} else {
			return "staff/addstaff";
		}
	}

	@PostMapping("/staff/update")
	public String updateStaff(Model model, @ModelAttribute("staffmodel") NhanVien staff) {
		boolean flag = true;

		// boolean ktSDT = staff.getSoDienThoai().matches(SDT_Vali);
		// boolean ktEmail = staff.getEmail().matches(Email_Vali);
		boolean ktPass = staff.getMatKhau().matches(Pass_Vali);

		// checkmk
		if (staff.getMatKhau() == null || staff.getMatKhau().equals("")) {
			model.addAttribute("errorMessageP", "Vui lòng nhập mật khẩu!");
			flag = false;
		}
		if (!ktPass == true) {
			model.addAttribute("errorMessageP",
					"Mật khẩu của bạn phải dài từ 8 đến 16 ký tự, phải chứa ít nhất 1 ký tự viết hoa, 1 ký tự viết thường, 1 ký tự số và 1 ký tự đặc biệt");
			flag = false;
		}

		if (flag == true) {

			if (nvdao.existsById(staff.getMaNV())) {
				String encodedPW = wconfig.passwordEncoder().encode(staff.getMatKhau());
				staff.setMatKhau(encodedPW);
				nvdao.save(staff);
				ClearFormStaff(staff);
				System.out.println("Cập Nhật  thành công");
				return "staff/addstaff";
			} else {
				model.addAttribute("errorMessageM", "Cập Nhật Thất Bại");

			}
			return "staff/addstaff";

		} else {
			return "staff/addstaff";
		}

	}

	@GetMapping("/staff/edit/{maNV}")
	public String editCar(Model model, @PathVariable("maNV") String maNV,
			@ModelAttribute("staffmodel") NhanVien staff) {
		if (nvdao.findById(staff.getMaNV()).isEmpty()) {
			return "redirect: /liststaff";
		} else {
			NhanVien staffs = nvdao.findById(maNV).get();
			staff.setMaNV(staffs.getMaNV());
			staff.setMatKhau(staffs.getMatKhau());
			staff.setHoNV(staffs.getHoNV());
			staff.setTenNV(staffs.getTenNV());
			staff.setGioiTinh(staffs.getGioiTinh());
			staff.setSoDienThoai(staffs.getSoDienThoai());
			staff.setNgaySinh(staffs.getNgaySinh());
			staff.setEmail(staffs.getEmail());
			staff.setTinh(staffs.getTinh());
			staff.setHuyen(staffs.getHuyen());
			staff.setXa(staffs.getXa());
			staff.setDuong(staffs.getDuong());
			staff.setChucVu(staffs.getChucVu());
			staff.setTrangThai(staffs.getTrangThai());

		}
		return "staff/addstaff";
	}

	@GetMapping("/staff/sort")
	public String sortStaff(Model model, @RequestParam("field1") Optional<String> field) {
		Sort sort = Sort.by(Direction.ASC, field.orElse("maMV"));
		model.addAttribute("lsnv", nvdao.findAll(sort));

		return "staff/liststaff";
	}

	// report loại xe

	@RequestMapping("/car/report")
	public String report(Model model) {
		String key = req.getParameter("key");
		String format = "yyyy-MM-dd"; // Định dạng của chuỗi ngày tháng
		SimpleDateFormat sdf = new SimpleDateFormat(format);

		if (key == null || key.equals("")) {
			List<HopDong> rps = hddao.findAll();
			model.addAttribute("reports", rps);
		} else {
			try {
				Date date = sdf.parse(key);
				List<HopDong> rps = hddao.findkw(date);
				model.addAttribute("reports", rps);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return "staff/report";
	}

	// sort trạng thái hd
	@RequestMapping("lstthdnull")
	public String listPreContract(Model model) {
		List<HopDong> list = hddao.findByNhanVienIsNull();
		model.addAttribute("reports", list);
		return "staff/report";
	}

	@RequestMapping("lstthd")
	public String listPostContract(Model model) {
		List<HopDong> list = hddao.findByNhanVienIsNotNull();
		model.addAttribute("reports", list);
		return "staff/report";
	}

	@RequestMapping("car/admin/logout")
	public String logout(Model model) {
		session.removeAttribute("currentAccount");
		session.removeAttribute("nvAccount");
		session.removeAttribute("security-uri");
		return "redirect:/car/login";
	}

	// xuất ex
	@RequestMapping("/car/listHD/export")
	public String xuatExcel(Model model) throws IOException {
		List<HopDong> lsHD = hddao.findAll();
		excelHelper.exportlsDoanhThu(lsHD);
		model.addAttribute("reports", lsHD);
		return "staff/report";
	}
}
