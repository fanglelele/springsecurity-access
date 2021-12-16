package com.yjxxt.security.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Service
public class UrlControlService {

    public boolean hasPermission(HttpServletRequest request, Authentication authentication){
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if(!CollectionUtils.isEmpty(authorities)){
            String uri=  request.getRequestURI();
            SimpleGrantedAuthority simpleGrantedAuthority =new SimpleGrantedAuthority(uri);
            if(authorities.contains(simpleGrantedAuthority)){
                return true;
            }
        }
        return false;
    }
}
