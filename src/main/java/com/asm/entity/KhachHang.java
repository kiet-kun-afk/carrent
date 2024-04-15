package com.asm.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "khachhang")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KhachHang {

	// mã khách hàng, tự tăng
	@Id
	@Column(name = "makh")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer maKH;

	// số điện thoại
	@Column(name = "sodienthoai", nullable = true)
	private String soDienThoai;
	
	// Email, dùng để đăng ký tài khoản 
	@Column(name = "email", nullable = false)
	private String email;

	// họ của khách hàng, trong tiếng anh là first name
	@Column(name = "hokh", nullable = false)
	private String hoKH;

	// tên của khách hàng, trong tiếng anh là last name
	@Column(name = "tenkh", nullable = false)
	private String tenKH;
	
	// mật khẩu 
	@Column(name = "matkhau", nullable = true)
	private String matKhau;

	// số căn cước công dân
	@Column(name = "socccd")
	private Integer soCCCD;

	// tên tệp hình mặt trước của căn cước
	@Column(name = "hinhmattruoccccd")
	private String hinhMatTruocCCCD;

	// tên tệp hình mặt sau của căn cước
	@Column(name = "hinhmatsaucccd")
	private String hinhMatSauCCCD;

	// giới tính, nam hoặc nữ
	@Column(name = "gioitinh")
	private Boolean gioiTinh;
	
	// ngày sinh, dd/MM/yyy
	@Column(name = "ngaysinh")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ngaySinh;
	
	// ảnh đại diện
	@Column(name = "anhdaidien")
	private String avatar;

	// số giấy phép lái xe
	@Column(name = "sogplx")
	private Integer soGPLX;

	// tên tệp hình mặt trước của gplx
	@Column(name = "hinhmattruocgplx")
	private String hinhMatTruocGPLX;

	// tên tệp hình mặt sau của gplx
	@Column(name = "hinhmatsaugplx")
	private String hinhMatSauGPLX;

	// Tỉnh / Thành phố trực thuộc trung ương trong địa chỉ
	@Column(name = "tinh")
	private String tinh;

	// Quận / Huyện / Thị xã / Thành phố thuộc tỉnh / Thành phố thuộc thành phố
	// trực thuộc trung ương trong địa chỉ
	@Column(name = "huyen")
	private String huyen;

	// Xã / Phường / Thị trấn trong địa chỉ
	@Column(name = "xa")
	private String xa;

	// Đường trong địa chỉ
	@Column(name = "duong")
	private String duong;
	
	// trạng thái tài khoản
	@Column(name = "trangthai", nullable = false)
	private boolean active;

//	@OneToMany(mappedBy = "khachhang")
//	private List<HoaDon> danhSachHoaDon;

//	@OneToMany(mappedBy = "khachhang")
//	private List<HopDong> danhSachHopDong;

}
