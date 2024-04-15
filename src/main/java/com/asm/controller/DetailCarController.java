package com.asm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.asm.entity.HopDong;
import com.asm.entity.Xe;
import com.asm.service.XeService;

@Controller
public class DetailCarController {

	@Autowired
	XeService xeService;

	// 51H-079.53

	@RequestMapping("detail")
	public String detail(Model model, @RequestParam("id") String id) {
		Xe xe = xeService.getInfoXe(id);
		int numberOfSeats = xe.getSoCho();

		String transmission = xe.getTruyenDong();
		String fuelType = xe.getNhienLieu();
		String fuelConsumption = xe.getNlTieuHao();

		String features = xe.getTienNghi();

		// Tách chuỗi thành mảng các chuỗi con
		String[] featureArray = features.split(", ");

		// Khai báo các biến cho các chuỗi cần tạo
		String map = null;
		String camera = null;
		String reverseCamera = null;
		String sensor = null;
		String speedWarning = null;
		String spareTire = null;
		String dvdScreen = null;
		String etc = null;
		String airbag = null;
		String collisionSensor = null;
		String fullCamera = null;
		String gps = null;
		String usb = null;
		String bluetooth = null;

		// Lấy các tính năng đã tách
		for (String feature : featureArray) {
			if (feature.equalsIgnoreCase("Bản đồ")) {
				map = feature;
			}
			if (feature.equalsIgnoreCase("Camera hành trình")) {
				camera = feature;
			}
			if (feature.equalsIgnoreCase("Camera 360")) {
				fullCamera = feature;
			}
			if (feature.equalsIgnoreCase("Camera lùi")) {
				reverseCamera = feature;
			}
			if (feature.equalsIgnoreCase("Cảm biến lốp")) {
				sensor = feature;
			}
			if (feature.equalsIgnoreCase("Cảm biến va chạm")) {
				collisionSensor = feature;
			}
			if (feature.equalsIgnoreCase("Cảnh báo tốc độ")) {
				speedWarning = feature;
			}
			if (feature.equalsIgnoreCase("Lốp dự phòng")) {
				spareTire = feature;
			}
			if (feature.equalsIgnoreCase("Màn hình DVD")) {
				dvdScreen = feature;
			}
			if (feature.equalsIgnoreCase("Định vị GPS")) {
				gps = feature;
			}
			if (feature.equalsIgnoreCase("Khe cắm USB")) {
				usb = feature;
			}
			if (feature.equalsIgnoreCase("Túi khí an toàn")) {
				airbag = feature;
			}
			if (feature.equalsIgnoreCase("ETC")) {
				etc = feature;
			}
			if (feature.equalsIgnoreCase("Bluetooth")) {
				bluetooth = feature;
			}
		}
		model.addAttribute("numberOfSeats", numberOfSeats);
		model.addAttribute("transmission", transmission);
		model.addAttribute("fuelType", fuelType);
		model.addAttribute("fuelConsumption", fuelConsumption);
		model.addAttribute("map", map);
		model.addAttribute("camera", camera);
		model.addAttribute("sensor", sensor);
		model.addAttribute("reverseCamera", reverseCamera);
		model.addAttribute("speedWarning", speedWarning);
		model.addAttribute("spareTire", spareTire);
		model.addAttribute("dvdScreen", dvdScreen);
		model.addAttribute("etc", etc);
		model.addAttribute("airbag", airbag);
		model.addAttribute("gps", gps);
		model.addAttribute("fullCamera", fullCamera);
		model.addAttribute("collisionSensor", collisionSensor);
		model.addAttribute("usb", usb);
		model.addAttribute("bluetooth", bluetooth);

		HopDong hopDong = new HopDong();

		List<Xe> randomXe = xeService.getRandomCars();

		model.addAttribute("contract", hopDong);
		model.addAttribute("car", xe);
		model.addAttribute("cars", randomXe);
		return "detail-car";
	}
}