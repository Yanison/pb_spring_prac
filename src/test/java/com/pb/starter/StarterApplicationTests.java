package com.pb.starter;

import com.pb.starter.auth.AuthService;
import com.pb.starter.model.UserEntity;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.pb.starter.model.constant.Constant.GENDER_M;

@SpringBootTest
class StarterApplicationTests {

	@Autowired
	private AuthService authService;


	@Test
	void justest() {
		String txt = "jaron";
		char[] txtArr = txt.toCharArray();
		System.out.println("### txtArr : " + Arrays.toString(txtArr));
	}


	@Test
	void splitTest(){
		String txt = "ROLE_USER#ROLE_ADMIN";
		String txt2 = "ROLE_USER";
		String[] roles = txt.split("#");
		System.out.println("### roles : " + Arrays.toString(roles) + " size : " + roles.length);
		String[] roles2 = txt2.split("#");
		System.out.println("### roles2 : " + Arrays.toString(roles2) + " size : " + roles2.length);
		String txt3 = "";
		String[] roles3 = txt3.split("#");
		System.out.println("### roles3 : " + Arrays.toString(roles3) + " size : " + roles3.length);
	}

	@Test
	void encodeTest(){
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = "1234";
		String encoded = "$2a$10$AuCWg7kyxOF/oZrRbZRsbO2AlHztiTX3Hc5SHaEcxh5SVVsqJ.hHi";
		System.out.println("### encoded : " + encoded);
		System.out.println("### matches : " + passwordEncoder.matches(password, encoded));
	}
}
