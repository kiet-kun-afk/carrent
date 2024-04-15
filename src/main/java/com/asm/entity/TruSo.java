package com.asm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "truso")
@Data
@NoArgsConstructor
public class TruSo {

	// mã trụ sở, tự tăng
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mats")
	private Integer maTS;

	// số điện thoại
	@Column(name = "sodienthoai", nullable = false)
	private Integer soDienThoai;

	// địa chỉ email
	@Column(name = "email")
	private String email;

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

//	@OneToMany(mappedBy = "truso")
//	List<Xe> danhSachXeTheoTruSo;

}
