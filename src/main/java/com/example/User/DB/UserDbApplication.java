package com.example.User.DB;

import com.example.User.DB.Twilio.TwilioConfig;
import com.twilio.Twilio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;


@SpringBootApplication
@EnableConfigurationProperties

public class UserDbApplication {
	@Autowired
	private TwilioConfig twilioConfig;
@PostConstruct
public  void setUp(){
	Twilio.init(twilioConfig.getAccountSid(),twilioConfig.getAuthToken());
}

	public static void main(String[] args) {
		SpringApplication.run(UserDbApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return  new ModelMapper();
	}
}
