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
@Table(name = "hangxe")
@Data
@NoArgsConstructor
public class HangXe {

	// mã hãng xe
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mahx")
	private Integer maHX;

	// tên hãng xe
	@Column(name = "tenhang", nullable = false)
	private String tenHang;

	// xuất xứ quốc gia
	@Column(name = "quocgia")
	private String quocGia;

	// @OneToMany(mappedBy = "hangxe")
	// List<Xe> danhSachXeTheoHang;
}
