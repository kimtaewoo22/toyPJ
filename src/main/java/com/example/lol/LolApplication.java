package com.example.lol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//DataSourceAutoConfiguration (DB를 사용안한다는 의미)
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class LolApplication {

	public static void main(String[] args) {
		SpringApplication.run(LolApplication.class, args);
	}

}
