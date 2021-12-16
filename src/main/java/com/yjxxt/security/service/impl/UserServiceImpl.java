package com.yjxxt.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;





    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(StringUtils.isEmpty(username) || !(username.equals("admin"))){
            System.out.println("用户不存在!");
            throw  new UsernameNotFoundException("用户记录不存在!");
        }

       // 到数据库查询用户记录

        String password = passwordEncoder.encode("123");
        return new User(username,password, AuthorityUtils.
                commaSeparatedStringToAuthorityList("101010,/insert,/select,/update,/delete" +
                        ",ROLE_a,ROLE_d,ROLE_c"));
    }
}
