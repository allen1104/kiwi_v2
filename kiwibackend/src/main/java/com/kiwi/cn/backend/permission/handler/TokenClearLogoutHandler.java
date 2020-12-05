package com.kiwi.cn.backend.permission.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kiwi.cn.backend.permission.service.JwtUserServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutHandler;


public class TokenClearLogoutHandler implements LogoutHandler {

    private JwtUserServiceImpl jwtUserService;

    public TokenClearLogoutHandler(JwtUserServiceImpl jwtUserService) {
        this.jwtUserService = jwtUserService;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        clearToken(authentication);
    }

    protected void clearToken(Authentication authentication) {
        if(authentication == null)
            return;
        UserDetails user = (UserDetails)authentication.getPrincipal();
        if(user!=null && user.getUsername()!=null)
            jwtUserService.deleteUserLoginInfo(user.getUsername());
    }

}
