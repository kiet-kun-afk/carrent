package com.asm.entity;

import java.io.Serializable;
import java.util.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "giaoxe")
@Data
@NoArgsConstructor
public class GiaoXe implements Serializable{
	// mã nhận xe, tự tăng
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "magiaoxe")
	private Integer maGiaoXe;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "ngaygiaoxe", nullable = false)
	private Date ngayGiaoXe;
	
	@Column(name = "ngoaithat", nullable = false)
	private String ngoaiThat;
	
	@Column(name = "noithat", nullable = false)
	private String noiThat;
	
	@Column(name = "dongco", nullable = false)
	private String dongCo;
	
	@Column(name = "giaytoxe", nullable = false)
	private String giayToXe;
	
	@Column(name = "tinhtrang", nullable = false)
	private String tinhTrang;
	
	@Column(name = "noidung", nullable = false)
	private String noiDung;
	
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
	
	@OneToOne 
	@JoinColumn(name = "mahd")
	private HopDong hopDong;

}
