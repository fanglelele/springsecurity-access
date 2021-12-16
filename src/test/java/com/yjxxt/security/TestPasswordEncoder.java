package com.yjxxt.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class TestPasswordEncoder {

    @Test
    public void test01(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("123456"));
    }

    @Test
    public void test02(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.matches("123456", "$2a$10$FaUp8Rl9nabyoaG0mcuyj.XXUDAtCnUaU7xIY25dpnvOgiyTAC31m"));
    }

}
