package thwjd.usedbook;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@MapperScan(value = {"thwjd.usedbook.mapper"})
@SpringBootApplication
public class UsedbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsedbookApplication.class, args);
	}

}
