package com.asm.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "nhanvien")
@Data
@NoArgsConstructor
public class NhanVien {

	// mã nhân viên, dùng để đăng nhập
	@Id
	@Column(name = "manv", nullable = false)
	private String maNV;

	// mật khẩu cũng dùng để đăng nhập
	@Column(name = "matkhau", nullable = false)
	private String matKhau;

	// họ của nhân viên, trong tiếng anh là first name
	@Column(name = "honv", nullable = false)
	private String hoNV;

	// tên của nhân viên, trong tiếng anh là last name
	@Column(name = "tennv", nullable = false)
	private String tenNV;

	// giới tính, nam hoặc nữ
	@Column(name = "gioitinh")
	private Boolean gioiTinh;

	// số điện thoại
	@Column(name = "sodienthoai", nullable = false)
	private String soDienThoai;

	// ngày sinh
	@Temporal(TemporalType.DATE)
	@Column(name = "ngaysinh")
	private Date ngaySinh;

	// địa chỉ email
	@Column(name = "email")
	private String email;

	// chức vụ, sếp hoặc đệ
	@Column(name = "chucvu", nullable = false)
	private Boolean chucVu;

	// trang thái, còn làm hoặc nghỉ làm
	@Column(name = "trangthai")
	private Boolean trangThai = true;

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

	// tên tệp ảnh
	@Column(name = "avatar")
	private String avatar;

//	@OneToMany(mappedBy = "nhanvien")
//	private List<HoaDon> danhSachHoaDon;

//	@OneToMany(mappedBy = "nhanvien")
//	private List<HopDong> danhSachHopDong;

}
