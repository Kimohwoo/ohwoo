package com.ohwoo.domain;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.ohwoo.DTO.AuthDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;


//토큰 생성 및 검증 클래스
//해당 컴포넌트 필터클래스에서 사전 검증을 거칩니다.
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

	private String SECRETKEY = "ohwooServer";
    // 토큰 만료시간 단위 :  ms
    private long TOKEN_VALID_TIME = 30*60*1000L;
    private final UserDetailsService userDetailsService;
    
    //객체 초기화, Base64로 인코딩하기
    @PostConstruct
    protected void init() {
    	SECRETKEY = Base64.getEncoder().encodeToString(SECRETKEY.getBytes());
    }
	
    //토큰 생성
    //기본은 List<String> -> List<AuthDTO>로 변경함
    public String createToken(String userPk, List<AuthDTO> roles) {
    	Claims claims = Jwts.claims().setSubject(userPk); // JWT payload에 저장되는 정보단위, 보통 여기서 user를 식별하는 값을 넣는다.
    	claims.put("roles", roles);
    	Date now = new Date();
    	return Jwts.builder().setClaims(claims)//정보저장
    			.setIssuedAt(now)//토큰 발행 시간 정보
    			.setExpiration(new Date(now.getTime() + TOKEN_VALID_TIME)) // 토큰 만료시간 설정
    			.signWith(SignatureAlgorithm.HS256, SECRETKEY) // 사용할 암호화 알고리즘과 signature에 들어갈 시크릿 키 세팅
    			.compact();
    }
    
    //Jwt 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
    	UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
    	return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
    
    //토큰에서 회원 정보 추출
    public String getUserPk(String token) {
    	return Jwts.parser().setSigningKey(SECRETKEY).parseClaimsJws(token).getBody().getSubject();
    }
    
    //Request의 Header에서 token값을 가져옵니다. "Authorization" : "TOEKN 값"
    public String resolveToken(HttpServletRequest request) {
    	return request.getHeader("Authorization");
    }
    
    //토큰의 유효성 * 만료일자 확인
    public boolean validateToken(String jwtToken) {
    	try {
    		Jws<Claims> claims = Jwts.parser().setSigningKey(SECRETKEY).parseClaimsJws(jwtToken);
    		return !claims.getBody().getExpiration().before(new Date());
    	} catch(Exception e) {
    		return false;
    	}
    }
	
}





















