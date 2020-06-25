package com.vs.app;


import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderTest {

    @Test
    public void testBCryptPasswordEncoder(){
        System.out.println(new BCryptPasswordEncoder().encode("123456"));

    }
}
