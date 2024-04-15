package com.asm.oauth2;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.asm.dao.KhachHangDAO;
import com.asm.entity.KhachHang;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	KhachHangDAO khDAO;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		CustomOAuth2User oauth2User = (CustomOAuth2User) authentication.getPrincipal();

		String email = oauth2User.getEmail();
		String name = oauth2User.getName();

		System.out.println(name);
		System.out.println(email);

		if (khDAO.findByEmail(email) != null) {
			// Update user in database
			System.out.println("Khách hàng đã tồn tại");
		} else {
			// Create new user
			System.out.println("Khách hàng mới");

			// String password = "123";
			// String encodedPassword = passEncode.encode(password);

			KhachHang acc = new KhachHang();
			acc.setEmail(oauth2User.getEmail());
			acc.setHoKH(oauth2User.getName());
			acc.setTenKH(oauth2User.getName());
			acc.setAvatar(oauth2User.getPhoto());
			acc.setGioiTinh(true);
			acc.setActive(true);
			acc.setDuong("Quận 12");

			khDAO.save(acc);
		}

		response.sendRedirect("/car/index");
		super.onAuthenticationSuccess(request, response, authentication);
	}

}
