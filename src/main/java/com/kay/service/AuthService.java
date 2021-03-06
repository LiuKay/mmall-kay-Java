package com.kay.service;

import com.kay.vo.UserIdentityDTO;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {

    UserIdentityDTO getUser(HttpServletRequest request);

    Integer getUserId(HttpServletRequest request);

    void sendVerificationCode(String mobile);

}
