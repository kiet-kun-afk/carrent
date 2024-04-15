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
@Table(name = "loaixe")
@Data
@NoArgsConstructor
public class LoaiXe {

	// mã loại xe, tự tăng
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "malx")
	private Integer maLX;

	// tên loại xe
	@Column(name = "tenloai", nullable = false)
	private String tenLoai;

//	@OneToMany(mappedBy = "loaixe")
//	private List<Xe> danhSachXeTheoLoai;

}
