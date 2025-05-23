package com.dadingcoding.web.security;

import com.dadingcoding.web.service.CustomUserDetailsService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {
    private final Key key;

    private final CustomUserDetailsService customUserDetailsService;

    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey, CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        key = Keys.hmacShaKeyFor(keyBytes);
    }

    // 유저 정보 -> Token 생성
    public JwtToken generateToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Calendar accessTokenCal = Calendar.getInstance();
        accessTokenCal.setTime(new Date());
        accessTokenCal.add(Calendar.DATE, 1);

        Calendar refreshTokenCal = Calendar.getInstance();
        refreshTokenCal.setTime(new Date());
        refreshTokenCal.add(Calendar.MONTH, 1);

        // AccessToken : 1일 후 만료
        Date accessTokenExpiresIn = accessTokenCal.getTime();
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("auth", authorities)
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        // RefreshToken : 1달 후 만료
        Date refreshTokenExpiresIn = refreshTokenCal.getTime();
        String refreshToken = Jwts.builder()
                .setExpiration(refreshTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return JwtToken.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public String refreshAccessToken(String email) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

        Calendar accessTokenCal = Calendar.getInstance();
        accessTokenCal.setTime(new Date());
        accessTokenCal.add(Calendar.DATE, 1);

        // AccessToken : 1일 후 만료
        Date accessTokenExpiresIn = accessTokenCal.getTime();

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("auth", userDetails.getAuthorities())
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    //Jwt Token -> 권한 확인
    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);

        // 권한 정보 없을 시 시행
        if (claims.get("auth") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        UserDetails principal = customUserDetailsService.loadUserByUsername(claims.getSubject());
        log.info("principalThis={}",principal);
        return new UsernamePasswordAuthenticationToken(principal, "", principal.getAuthorities());
    }

    // token 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
            throw new JwtException("올바르지 않은 토큰입니다.");
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
            // 만료 토큰 재발급 필요
            throw new JwtException("Access Token이 만료되었습니다. 토큰을 갱신해 주세요.");
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
            throw new JwtException("올바르지 않은 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
            throw new JwtException("정상적이지 않은 접근입니다.");
        }
//        return false;
    }

    // 넘어온 accessToken의 정보 조회
    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

}
