package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.example.demo.upload.StorageProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class AdGrupo1Application {

	public static void main(String[] args) {
		SpringApplication.run(AdGrupo1Application.class, args);
	}

}
