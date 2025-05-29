package antismo.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

@SpringBootTest(classes = BackendApplication.class)
// Thêm dòng này để chỉ rõ class cấu hình
class MainApplicationTests {

	@Test
	void contextLoads() {
	}

}
