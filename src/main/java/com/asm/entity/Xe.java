package com.asm.entity;

import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "xe")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Xe {

	// biển số xe
	@Id
	@Column(name = "bienso", nullable = false)
	private String bienSo;

	// tên xe
	@Column(name = "tenxe", nullable = false)
	private String tenXe;

	// số chỗ
	
	// giá thuê theo ngày
	@Column(name = "giathue")
	private Double giaThue;

	// các đặc điểm như Truyền động: Số tự động, nhiên liệu: Điện, nhiên liệu tiêu hao: 9 lít/100km
//	@Column(name = "dacdiem")
//	private String dacDiem;
	
	@Column(name = "socho")
	private Integer soCho;

	@Column(name = "truyendong")
	private String truyenDong;
	
	@Column(name = "nhienlieu")
	private String nhienLieu;
	
	@Column (name = "nttieuhao")
	private String nlTieuHao;
	

	// các tiện nghi như: bản đồ, bluetooth, camera hành trình, ETC, ...
	@Column(name = "tiennghi")
	private String tienNghi;

	// đã cho thuê hay ế
	@Column(name = "trangthai")
	private Boolean trangThai;

	// tên tệp hình đầu xe
	@Column(name = "imgdauxe")
	private String imgDauXe;

	// tên tệp hình đuôi xe
	@Column(name = "imgduoixe")
	private String imgDuoiXe;

	// tên tệp hình sườn phải xe
	@Column(name = "imgsuonpxe")
	private String imgSuonPXe;

	// tên tệp hình sườn trái xe
	@Column(name = "imgsuontxe")
	private String imgSuonTXe;

	// vài dòng mô tả tổng quát về xe
	@Column(name = "mota")
	private String moTa;

	// ngày đăng kiểm
	@Temporal(TemporalType.DATE)
	@Column(name = "ngaydangkiem")
	private Date ngayDangKiem;

	@ManyToOne
	@JoinColumn(name = "malx")
	private LoaiXe loaiXe;

	@ManyToOne
	@JoinColumn(name = "mahx")
	private HangXe hangXe;

	@ManyToOne
	@JoinColumn(name = "mats")
	private TruSo truSo;

	public String formatTien(double tien) {
		 Locale localeVN = new Locale("vi", "VN");
		 NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
		 String temp = currencyVN.format(tien);
		 return temp;
	}
	
}
