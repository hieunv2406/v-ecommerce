package com.vm.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
public class AuthUtil {

    public static String getCurrentToken() {
        if (RequestContextHolder.getRequestAttributes() == null) {
            return null;
        }
        Object token = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");
        return token != null ? token.toString() : null;
    }

    public static String getUsernameFromJwtToken() {
        String token = getCurrentToken();
        if (token == null) {
            return null;
        }
        try {
            int idx = token.lastIndexOf('.');
            String withoutSignature;
            if (StringUtils.hasText(token) && token.startsWith("Bearer")) {
                withoutSignature = token.substring(7, idx + 1);
            } else {
                withoutSignature = token.substring(0, idx + 1);
            }
            Claims body = Jwts.parser().parseClaimsJwt(withoutSignature).getBody();
            Object username = body.get("username");
            return username != null ? username.toString() : null;
        } catch (ExpiredJwtException | UnsupportedJwtException ex) {
            log.error("ERROR - getUsernameFromJwtToken:" + ex.getMessage(), ex);
            return null;
        }
    }
}
