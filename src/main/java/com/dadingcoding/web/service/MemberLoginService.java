package com.dadingcoding.web.service;

import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.repository.MemberRepository;
import com.dadingcoding.web.security.JwtToken;
import com.dadingcoding.web.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberLoginService {
    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;


    @Transactional
    public JwtToken signIn(String email, String password) {

        UsernamePasswordAuthenticationToken authenticationFilter
                = new UsernamePasswordAuthenticationToken(email, password);

        Authentication authentication = null;

        try {
            authentication = authenticationManagerBuilder.getObject().authenticate(authenticationFilter);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JwtToken jwtToken = jwtTokenProvider.generateToken(authentication);

        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent()) {
            member.get().setRefreshToken(jwtToken.getRefreshToken());
        }

        return jwtToken;
    }

    // username, refreshToken으로 검증 -> accessToken 토큰 생성
    @Transactional
    public String refresh(String email, String refreshToken) {

        String refreshDBToken = memberRepository.findByEmail(email)
                .map(user -> user.getRefreshToken())
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 회원을 찾을 수 없습니다."));

        if (!refreshDBToken.equals(refreshToken)) {
            throw new SecurityException("Invalid JWT Token");
        }

        return jwtTokenProvider.refreshAccessToken(email);
    }

}
