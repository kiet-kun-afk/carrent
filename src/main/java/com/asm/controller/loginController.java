package com.asm.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.asm.config.WebSecurityConfig;
import com.asm.dao.KhachHangDAO;
import com.asm.dao.NhanVienDAO;
import com.asm.dao.XeDAO;
import com.asm.entity.KhachHang;
import com.asm.entity.Xe;
import com.asm.helper.managerOTP;
import com.asm.service.MailService;
import com.asm.service.SessionService;
import com.asm.util.MailInfo;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class loginController {

	managerOTP mOTP = new managerOTP();
	String otp = "";

	private static final String SDT_Vali = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
	private static final String Email_Vali = "[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	private static final String Pass_Vali = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=]).{8,16}$";

	@Autowired
	HttpServletRequest req;

	@Autowired
	KhachHangDAO khDAO;

	@Autowired
	NhanVienDAO nvDAO;

	@Autowired
	XeDAO xeDAO;

	@Autowired
	WebSecurityConfig wconfig;

	@Autowired
	MailService mailService;

	@Autowired
	SessionService session;

	@RequestMapping("/car/login")
	public String login() {
		return "Login/login";
	}

	@RequestMapping("/oauth2/login/success")
	public String success(OAuth2AuthenticationToken oauth2) {
		return "redirect:/car/index";
	}

	@RequestMapping("car/register")
	public String register(Model model) {
		KhachHang kh = new KhachHang();
		model.addAttribute("khachhang", kh);
		return "Login/register";
	}

	@RequestMapping("car/register/submit")
	public String registerSubmit(Model model, @ModelAttribute("khachhang") KhachHang kh) {
		String confirmpass = req.getParameter("cfpassword");
		boolean flag = true;

		boolean ktSDT = kh.getSoDienThoai().matches(SDT_Vali);
		boolean ktEmail = kh.getEmail().matches(Email_Vali);
		boolean ktPass = kh.getMatKhau().matches(Pass_Vali);

		KhachHang findkh = khDAO.findByEmail(kh.getEmail());

		if (findkh == null || !findkh.getEmail().equals(kh.getEmail())) {
			flag = true;
		} else {
			model.addAttribute("errorMessageE", "Email đã tồn tại!");
			flag = false;
		}

		if (kh.getHoKH() == null || kh.getHoKH().equals("")) {
			model.addAttribute("errorMessageH", "Vui lòng nhập họ!");
			flag = false;
		}
		if (kh.getTenKH() == null || kh.getTenKH().equals("")) {
			model.addAttribute("errorMessageT", "Vui lòng nhập tên!");
			flag = false;
		}
		if (!ktSDT == true) {
			model.addAttribute("errorMessageS", "Số điện thoại không hợp lệ!");
			flag = false;
		}
		if (!ktEmail == true) {
			model.addAttribute("errorMessageE", "Nhập email hợp lệ!");
			flag = false;
		}
		if (!ktPass == true) {
			model.addAttribute("errorMessageP",
					"Mật khẩu của bạn phải dài từ 8 đến 16 ký tự, phải chứa ít nhất 1 ký tự viết hoa, 1 ký tự viết thường, 1 ký tự số và 1 ký tự đặc biệt");
			flag = false;
		}
		if (!kh.getMatKhau().equals(confirmpass)) {
			model.addAttribute("errorMessageP1", "Mật khẩu không đúng!");
			flag = false;
		}

		if (flag == true) {
			String encodedPW = wconfig.passwordEncoder().encode(kh.getMatKhau());
			kh.setMatKhau(encodedPW);
			kh.setHinhMatTruocGPLX("GPLXMT.jpg");
			khDAO.save(kh);
			return "redirect:/car/login";
		} else {
			return "Login/register";
		}
	}

	@RequestMapping("/car/forgotpassword")
	public String forgot() {
		return "Login/forgot";
	}

	@RequestMapping("/car/forgotpassword/submit")
	public String forgotsubmit(Model model, @RequestParam("email") String email)
			throws IOException, MessagingException {
		System.out.println(email);

		if (email.equals("") || email == null) {
			model.addAttribute("errorMessage", "Email Không Hợp Lệ!!");
			return "Login/forgot";
		} else {
			boolean ktEmail = email.matches(Email_Vali);
			if (ktEmail == true) {
				KhachHang kh = khDAO.findByEmail(email);
				if (kh == null) {
					model.addAttribute("errorMessage", "Email Không Hợp Lệ!!");
					return "Login/forgot";
				} else {
					mOTP.generateOTP(6);
					otp = mOTP.getmaOTP();

					long expirationTimeMillls = 30000;
					mOTP.startOTP(expirationTimeMillls);

					// MailHelper helper = new MailHelper();
					MailInfo mail = new MailInfo();
					mail.setFrom("CarR");
					mail.setTo(email);
					// mail.setTo(TO);
					// mail.setCc(emailCC);
					mail.setSubject("Mã OTP Của Bạn Là: " + otp);
					mail.setBody("Vui Lòng Nhập Mã OTP Để Xác Nhận Để Lấy Lại Mật Khẩu !");

					session.setAttribute("email", email);
					mailService.send(mail);
					return "Login/confirmOTP";
				}

			} else {
				model.addAttribute("errorMessage", "Định Dạng Email Chưa Đúng!");
				return "Login/forgot";
			}
		}
	}

	@RequestMapping("/car/forgotpassword/otp/submit")
	public String cofirmotp(Model model, @RequestParam("maotp") String maotp) {
		if (otp == mOTP.getmaOTP()) {
			if (maotp.equals("") || maotp == null) {
				model.addAttribute("errorMessage", "Nhập OTP !!");
				return "Login/confirmOTP";
			} else {
				if (maotp.equals(otp)) {
					return "Login/resetpass";
				} else {
					model.addAttribute("errorMessage", "Mã OTP Chưa Chính Xác !!");
					return "Login/confirmOTP";
				}
			}
		} else {
			if (maotp.equals("") || maotp == null) {
				model.addAttribute("errorMessage", "Nhập OTP !!");
				return "Login/confirmOTP";
			} else {
				Integer.parseInt(maotp);
				model.addAttribute("errorMessage", "Mã OTP Hết Hiệu Lực!");
				return "Login/confirmOTP";
			}
		}
	}

	@RequestMapping("/car/forgotpassword/resetpassword")
	public String resetPassword(Model model, @RequestParam("newpw") String newpw,
			@RequestParam("confirmpw") String confirmpw) {

		boolean flag = true;
		boolean ktPass = newpw.matches(Pass_Vali);

		if (!ktPass == true) {
			model.addAttribute("errorMessage",
					"Mật khẩu của bạn phải dài từ 8 đến 16 ký tự, phải chứa ít nhất 1 ký tự viết hoa, 1 ký tự viết thường, 1 ký tự số và 1 ký tự đặc biệt");
			flag = false;
		}

		if (newpw.equals("") || newpw == null) {
			model.addAttribute("errorMessage", "Vui Lòng Nhập Mật Khẩu Mới!");
			flag = false;
		}

		if (confirmpw.equals("") || confirmpw == null) {
			model.addAttribute("errorMessage1", "Vui Lòng Xác Nhận Mật Khẩu Mới !");
			flag = false;
		}

		if (!newpw.equals(confirmpw)) {
			model.addAttribute("errorMessage1", "Xác Nhận Mật Khẩu Chưa Chính Xác!");
			flag = false;
		}

		String email = session.getAttribute("email");
		KhachHang kh = khDAO.findByEmail(email);

		// if(kh == null) {
		// model.addAttribute("errorMessage2","Tài Khoản Không Tồn Tại!");
		// flag = false;
		// }

		if (flag == true) {
			String newpwencode = wconfig.passwordEncoder().encode(confirmpw);
			kh.setMatKhau(newpwencode);
			khDAO.save(kh);
			session.removeAttribute("email");
			model.addAttribute("errorMessage2", "Cập Nhật Thành Công");
			return "redirect:/car/login";
		} else {
			return "Login/resetpass";
		}
	}

	@RequestMapping("car/logout")
	public String logout(Model model) {
		session.removeAttribute("currentAccount");
		session.removeAttribute("nvAccount");
		session.removeAttribute("security-uri");
		return "redirect:/car/index";
	}

	@RequestMapping("/car/index")
	public String index(Model model, @RequestParam("page") Optional<Integer> page) {
		Pageable pageable = PageRequest.of(page.orElse(0), 8);
		Page<Xe> pages = xeDAO.findAll(pageable);
		model.addAttribute("page", pages);
		return "index";
	}
}
