package com.dadingcoding.web.service;

import com.dadingcoding.web.controller.dto.response.MemberLoginResponseDto;
import com.dadingcoding.web.controller.dto.response.SimpleMemberResponseDto;
import com.dadingcoding.web.controller.dto.response.ValidateEmailResponseDto;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberLoginService {
    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    @Transactional
    public MemberLoginResponseDto makeToken(String email, String password) {

        UsernamePasswordAuthenticationToken authenticationFilter
                = new UsernamePasswordAuthenticationToken(email, password);

        Authentication authentication = null;

        try {
            authentication = authenticationManagerBuilder.getObject().authenticate(authenticationFilter);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        JwtToken jwtToken = jwtTokenProvider.generateToken(authentication);

        SimpleMemberResponseDto simpleMemberResponseDto = null;

        // 이메일 존재하면 refresh
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent()) {
            Member findMember = member.get();
            findMember.setRefreshToken(jwtToken.getRefreshToken());
            simpleMemberResponseDto = SimpleMemberResponseDto.toDto(findMember);
        }

        return MemberLoginResponseDto.builder()
                .accessToken(jwtToken.getAccessToken())
                .refreshToken(jwtToken.getRefreshToken())
                .user(simpleMemberResponseDto)
                .build();
    }

    // username, refreshToken으로 검증 -> accessToken 토큰 생성
    @Transactional
    public String refresh(String email, String refreshToken) {

        String refreshDBToken = memberRepository.findByEmail(email)
                .map(user -> user.getRefreshToken())
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 회원을 찾을 수 없습니다."));

        if (!refreshDBToken.equals(refreshToken)) {
            throw new SecurityException("Refresh Token이 만료되었습니다. 다시 로그인 해주세요.");
        }

        return jwtTokenProvider.refreshAccessToken(email);
    }

    @Transactional
    public ValidateEmailResponseDto validateEmail(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);

        boolean isValid = matcher.matches();
        boolean isUnique = memberRepository.findByEmail(email).isEmpty();

        return new ValidateEmailResponseDto(isValid, isUnique);
    }

    @Transactional
    public void save(Member member) {
        String password = member.getPassword();
        member.setPassword(passwordEncoder.encode(password));
        memberRepository.save(member);
    }

    @Transactional
    public void logout(Member member) {
        member.setRefreshToken("");
    }
}
