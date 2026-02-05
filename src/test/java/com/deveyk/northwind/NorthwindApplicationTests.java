package com.deveyk.northwind;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.yaml")
class NorthwindApplicationTests {

	@Test
	void contextLoads() {

	}

}
