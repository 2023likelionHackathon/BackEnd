package com.project.market.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
@Slf4j
public class CookieUtils {
    public static Optional<Cookie> getCookie(HttpServletRequest request, String name){
        Cookie[] cookies = request.getCookies();
        //log.info("cookies len = {}",cookies.length);
        if(cookies!= null && cookies.length>0){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(name)){
                    return Optional.of(cookie);
                }
            }
        }
        return Optional.empty();
    }
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge){
        addCookie(response, name, value, false, false, maxAge);
    }
    public static void addCookie(HttpServletResponse response, String name, String value, boolean httpOnly, boolean secure, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setHttpOnly(httpOnly);
        //cookie.setSecure(secure);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
        // Set-Cookie 헤더를 추가하여 쿠키를 클라이언트에게 전송
        //response.setHeader("Set-Cookie", String.format("%s=%s; Max-Age=%d; Path=/; HttpOnly; Secure", name, value, maxAge));
        response.setHeader("Set-Cookie", String.format("%s=%s; Max-Age=%d; Domain=domarketdodo.shop; Path=/;", name, value, maxAge));
    }

    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    cookie.setValue("");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
    }
}