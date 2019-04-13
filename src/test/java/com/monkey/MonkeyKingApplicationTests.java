package com.monkey;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.monkey.taf.common.Tools;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MonkeyKingApplicationTests {
	@Test
	public void contextLoads() {
	}

	public static void main(String[] args) {
		Date date1 = new Date();
		date1.setHours(9);
		date1.setMinutes(0);
		date1.setSeconds(0);
		try {
			System.out.println(Tools.converToString(date1, Tools.FULLFORMAT));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
