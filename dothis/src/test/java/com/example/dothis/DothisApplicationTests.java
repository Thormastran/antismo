package com.example.dothis;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.dothis")
@SpringBootTest(classes = DothisApplication.class)
// Thêm dòng này để chỉ rõ class cấu hình
class DothisApplicationTests {

	@Test
	void contextLoads() {
	}

}
