package com.project.market.controller;

import com.project.market.exception.AuthenticationFailedException;
import com.project.market.jwt.JwtProvider;
import com.project.market.security.UserPrincipal;
import com.project.market.dto.AuthorizationDTO;
import com.project.market.service.UserService;
import com.project.market.util.CookieUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/authorize")
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    // 로그인
    @PostMapping
    public ResponseEntity authenticationUsernamePassword(@Valid @RequestBody AuthorizationDTO authorizationRequest, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response){

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authorizationRequest.getUsername(), authorizationRequest.getPassword()));
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            String status = generateTokenCookie(userPrincipal, request, response);
            return ResponseEntity.status(HttpStatus.OK).body(status);
        } catch (AuthenticationException e) {
            throw new AuthenticationFailedException("아이디 또는 패스워드가 틀렸습니다.");
        }
    }
    private String generateTokenCookie(UserPrincipal userPrincipal, HttpServletRequest request, HttpServletResponse response) {
        final int cookieMaxAge = jwtProvider.getTokenExpirationDate().intValue();
        //https 프로토콜인 경우 secure 옵션사용
        boolean secure = request.isSecure();
        CookieUtils.addCookie(response, "access_token", jwtProvider.generateToken(userPrincipal.getUsername()), true, secure, cookieMaxAge);
        return "SUCCESS";
    }

    // 로그아웃
    /* 토큰 쿠키를 삭제하는 컨트롤러 (로그아웃) */
    @PostMapping("/logout")
    public ResponseEntity<?> expiredToken(HttpServletRequest request, HttpServletResponse response) {
        CookieUtils.deleteCookie(request, response, "access_token");
        //CookieUtils.deleteCookie(request, response, StatelessCSRFFilter.CSRF_TOKEN);
        return ResponseEntity.ok("SUCCESS");
    }
}
