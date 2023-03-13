package com.app;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BusinessSuiteJavaApplication {

	public static void main(String[] args) {
//		System.setProperty("java.io.tmpdir", "documents/temporary");
		SpringApplication.run(BusinessSuiteJavaApplication.class, args);
	}
	@Bean
	public PasswordEncoder encoder()
	{
		System.out.println("in Password Encoder");
		return new BCryptPasswordEncoder();
	}
	
	//add a method to configure ModelMapper as a spring bean
		@Bean // equivalent to <bean id ..../> in xml file
		public ModelMapper configureMapper() {
			System.out.println("in config mapper....");
			ModelMapper modelMapper = new ModelMapper();
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			return modelMapper;//method rets bean instance to SC
		}



}
