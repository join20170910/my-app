package com.mycompany.app;

import org.junit.Assert;
import org.junit.Test;

public class TestHelloWorld {

	@Test()
	public void testEmailGenerator() {

		RandomEmailGenerator obj = new RandomEmailGenerator();
		String email = obj.generate();

		Assert.assertNotNull(email);
		Assert.assertEquals(email, "feedback@yoursite.com");
	}

}
