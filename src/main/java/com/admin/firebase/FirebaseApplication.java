package com.admin.firebase;

//import com.github.tobato.fastdfs.FdfsClientConfig;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.admin")
//@Import(FdfsClientConfig.class)
@MapperScan("com.admin.firebase.*.mapper")
public class FirebaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirebaseApplication.class, args);
	}

}