// package com.asm.interceptor;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.web.servlet.HandlerInterceptor;

// import com.asm.entity.KhachHang;
// import com.asm.entity.NhanVien;
// import com.asm.service.SessionService;

// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// @Service
// public class AuthInterceptor implements HandlerInterceptor {

// @Autowired
// SessionService session;

// @Override
// public boolean preHandle(HttpServletRequest request, HttpServletResponse
// response, Object handler)
// throws Exception {

// String uri = request.getRequestURI();

// KhachHang kh = (KhachHang) session.getAttribute("currentAccount");
// NhanVien nv = (NhanVien) session.getAttribute("nvAccount");

// String errorLogin = "VuiLongDangNhap";
// String errorKH = "TruyCapBiTuChoi";
// String errorAD = "";

// if (nv != null || kh != null) {
// errorLogin = "";
// }

// if (nv != null) {
// errorKH = "";
// }

// if (kh != null) {
// errorAD = "";
// }

// if (errorLogin.length() > 0) {
// session.setAttribute("security-uri", uri);
// response.sendRedirect("/car/login?error=" + errorLogin);
// return false;
// }
// if (errorKH.length() > 0) {
// session.setAttribute("security-uri", uri);
// response.sendRedirect("/car/index?error=" + errorKH);
// return false;
// }
// if (errorAD.length() > 0) {
// session.setAttribute("security-uri", uri);
// response.sendRedirect("/car/home?error=" + errorAD);
// return false;
// }

// return true;
// }

// }
