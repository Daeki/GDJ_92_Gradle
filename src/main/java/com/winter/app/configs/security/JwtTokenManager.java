package com.winter.app.configs.security;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.winter.app.member.MemberRepostory;
import com.winter.app.member.MemberVO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtTokenManager {
	
	//1. access 토큰 유효 시간
	@Value("${jwt.accessValidTime}")
	private Long accessValidTime;
	
	//2. 리프레시 토큰 유효시간
	@Value("${jwt.refreshValidTime}")
	private Long refreshValidTime;	
	//3. 발급자
	@Value("${jwt.issuer}")
	private String issuer;	
	//4. Security Key
	@Value("${jwt.secretKey}")
	private String secretKey;		
	
	//5. Key
	private SecretKey key;
	
	@Autowired
	private MemberRepostory memberRepostory;
	
	//A. Key 생성하는 메서드
	@PostConstruct 
	public void init() {
		String k = Base64.getEncoder().encodeToString(this.secretKey.getBytes());
		key = Keys.hmacShaKeyFor(k.getBytes());
	}
	
	//B. Token 생성하는 메서드
	private String createToken(Authentication authentication, Long validTime){
		return Jwts
				.builder()
				//사용자의 ID
				.subject(authentication.getName())
				//claim -> 추가 정보
				.claim("roles", authentication.getAuthorities())
				//토큰 생성 시간
				.issuedAt(new Date(System.currentTimeMillis()))
				//토큰 유효 시간
				.expiration(new Date(System.currentTimeMillis()+validTime))
				//발급자
				.issuer(issuer)
				//key
				.signWith(key)
				.compact();
	}
	
	//C. Access Token 생성
	public String createAccessToken(Authentication authentication)  {
		return this.createToken(authentication, accessValidTime);
	}
	
	//D. Refresh Token 생성
	public String createRefreshToken(Authentication authentication){
		return this.createToken(authentication, refreshValidTime);
	}
	
	//E. Token 검증
	public Authentication verifyToken(String token) throws Exception {
		//검증에 실패하면 Exception 발생
		Claims claims = Jwts
						.parser()
						.verifyWith(key)
						.build()
						.parseSignedClaims(token)
						.getPayload()
						;
		
		 Optional<MemberVO> result = memberRepostory.findById(claims.getSubject());
		 
		 MemberVO memberVO = result.get();
		 
		 return new UsernamePasswordAuthenticationToken(memberVO, null, memberVO.getAuthorities());
	}
	

}
