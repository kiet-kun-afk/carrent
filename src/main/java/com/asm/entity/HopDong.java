package com.asm.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hopdong")
@Data
@NoArgsConstructor
public class HopDong {

	// mã hợp đồng, tự tăng
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mahopdong")
	private Integer maHopDong;

	// ngày tạo hợp đồng
	@Temporal(TemporalType.DATE)
	@Column(name = "ngaytao", nullable = false)
	private Date ngayTao;

	// ngày bắt đầu hợp đồng
	@Temporal(TemporalType.DATE)
	@Column(name = "ngaybatdau", nullable = false)
	private Date ngayBatDau;

	// ngày kết thúc hợp đồng
	@Temporal(TemporalType.DATE)
	@Column(name = "ngayketthuc", nullable = false)
	private Date ngayKetThuc;

	// giá cho thuê xe / ngày
	@Column(name = "giathue", nullable = false)
	private Double giaThue;

	// tổng tiền cho thuê của hợp đồng = giá thuê x số ngày
	@Column(name = "tongtien", nullable = false)
	private Double tongTien;

	// tiền cọc
	@Column(name = "tiencoc", nullable = false)
	private Double tienCoc;

	// trạng thái thanh toán: đã thanh toán hoặc chưa
	@Column(name = "trangthaithanhtoan")
	private Boolean trangThaiThanhToan;

	// hình thức thanh toán
	@Column(name = "hinhthucthanhtoan")
	private String hinhThucThanhToan;

	@ManyToOne
	@JoinColumn(name = "bienso")
	private Xe xe;

	@ManyToOne
	@JoinColumn(name = "manv")
	private NhanVien nhanVien;

	@ManyToOne
	@JoinColumn(name = "makh")
	private KhachHang khachHang;
	
	@ManyToOne
	@JoinColumn(name = "manhanxe")
	private GiaoXe giaoXe;

}
