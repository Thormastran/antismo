package com.demo.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeApplication.class, args);
	}

	// API

	// Method
	// URL

	// GET: lấy dữ liệu
	// POST: create new
	// PUT: update
	// DELETE: xoá

	// Quản lý sinh viên
	// 1. lấy danh sách sinh viên
	// => GET /api/student

	// 2. tạo 1 sinh viên mới
	// => POST /api/student

	// 3. update thông tin sinh viên
	// => PUT /api/student/id

	// 4. Delete 1 thằng sinh viên nào đó
	// => DELETE /api/student/id

}
