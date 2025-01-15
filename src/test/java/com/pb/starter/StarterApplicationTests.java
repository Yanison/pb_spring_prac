package com.pb.starter;

import com.pb.starter.auth.AuthService;
import com.pb.starter.model.SubjectEntity;
import com.pb.starter.subject.SubjectMapper;
import com.pb.starter.subject.SubjectServiceImpl1;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
class StarterApplicationTests {

	@Autowired
	private AuthService authService;
	@Autowired
	private SubjectMapper subjectMapper;
	@Autowired
	private SubjectServiceImpl1 subjectServiceImpl1;


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
	void subjectDummy(){
		String[] catecory = {"important", "social", "business"};
		Date date = new Date();
		String formattedYYYYMMDD = String.format("%tF", date);
		String title = "dummy title" + formattedYYYYMMDD + "_";
		String content = "dummy content" + formattedYYYYMMDD + "_";

		int totalCnt = subjectServiceImpl1.findAll().size();
		for(int i=0; i<100; i++){
			SubjectEntity subject = new SubjectEntity();
			subject.setTitle(title + i);
			subject.setUserId(1L);
			subject.setContent(content + i);
			subject.setCategory(catecory[i%3]);
			subject.setFavorite(rareTrue());
			subjectServiceImpl1.insert(subject);
		}
		List<SubjectEntity> list = subjectServiceImpl1.findAll();
		System.out.println("### totalCnt : " + totalCnt + " list.size() : " + list.size());
	}

	boolean rareTrue(){
		//8% 확률로 true, 92% 확률로 false
		return Math.random() < 0.08;
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
