package com.ohwoo.domain;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohwoo.DTO.LoginRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@RequiredArgsConstructor
@Log4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        log.info("JwtAuthenticationFilter: 진입");

        ObjectMapper om = new ObjectMapper();
        LoginRequestDTO loginRequestDto = null;

        try {
            //Convert "JSON" to "Java Object"
            loginRequestDto = om.readValue(request.getInputStream(), LoginRequestDTO.class);
        } catch(Exception e) {
            e.printStackTrace();
        }

        log.info("JwtAuthenticationFilter:  loginRequestDto toString():  "+loginRequestDto);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getUsername(),
                        loginRequestDto.getPassword());

        log.info("JwtAuthenticationFilter  =--> "
                + " UsernamePasswordAuthenticationToken :   authenticationToken: 객체생성완료" + authenticationToken);

        // 생성된 토큰
        //전달된 인증 개체를 인증하려고 시도하고 성공하면 완전히 채워진 인증 개체(허가된 권한 포함)를 반환합니다.
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        //getPrincipal();
        //인증 중인 보안 주체의 ID입니다. 사용자 이름과 암호가 포함된 인증 요청의 경우 사용자 이름이 됩니다.
        //호출자는 인증 요청을 위해 주체를 채울 것으로 예상됩니다.
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        log.info("Authentication 의 CustomUserDetails.getUser().getUsername()의 내용: "+ customUserDetails.getUser().getUsername());

        return authentication;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info("successfulAuthentication 진입");
        CustomUserDetails customUserDetails = (CustomUserDetails) authResult.getPrincipal();

        log.info("successfulAuthentication principalDetails.getUsername() : 내용" + customUserDetails.getUser().getUsername());

        String jwtToken = JWT.create()
                .withSubject(customUserDetails.getUser().getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+JwtProperties.EXPIRATION_TIME))
                .withClaim("username", customUserDetails.getUser().getUsername())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));

        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX+jwtToken);
        log.info("response 확인해보기 : " +JwtProperties.HEADER_STRING + " : " + JwtProperties.TOKEN_PREFIX+jwtToken);

//	    HMAC 알고리즘을 사용할 경우 서명은 다음과 같이 생성한다.
//        HMACSHA256(
//          base64UrlEncode(header) + "." +
//          base64UrlEncode(payload),
//          your-256-bit-secret
//        )

        log.info("successfulAuthentication jwtToken : 내용 : " + jwtToken);

        ObjectMapper om = new ObjectMapper();

        LoginRequestDTO cmRequestDto = new LoginRequestDTO();
        cmRequestDto.setUsername(customUserDetails.getUser().getUsername());
        cmRequestDto.setPassword(customUserDetails.getUser().getPassword());

        String cmRequestDtoJson = om.writeValueAsString(cmRequestDto);
        log.info("om.writeValueAsString(cmRequestDto); 내용 : " + cmRequestDtoJson);
        PrintWriter out = response.getWriter();
        out.print(cmRequestDtoJson);
        out.flush();

    }

}