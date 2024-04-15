package com.asm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hoadon")
@Data
@NoArgsConstructor
public class HoaDon {

	// mã hóa đơn,
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mahoadon")
	private Integer maHoaDon;

	// tiền khách phải trả cho hóa đơn
	@Column(name = "thanhtien", nullable = false)
	private Double thanhTien;

	// phí phụ thu
	@Column(name = "phuthu")
	private Double phuThu;

	// phí đền bù
	@Column(name = "denbu")
	private Double denBu;

	@ManyToOne
	@JoinColumn(name = "makh")
	private KhachHang khachHang;

	@ManyToOne
	@JoinColumn(name = "manv")
	private NhanVien nhanVien;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mahd")
	private HopDong hopDong;
}
