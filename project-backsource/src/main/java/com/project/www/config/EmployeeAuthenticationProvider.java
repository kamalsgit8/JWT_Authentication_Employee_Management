package com.project.www.config;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.project.www.dtos.EmployeeDto;
import com.project.www.service.EmpService;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class EmployeeAuthenticationProvider 
{

	@Value("${security.jwt.token.secret-key:secret-key}")
	private String secretKey;
	
	private final EmpService empService;
	
	@PostConstruct
	// this is to avoid having the raw secret key available in the JVM
	protected void init()
	{
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}
	
	public String createToken(String login)
	{
		Date now = new Date();
		Date validity = new Date(now.getTime() + 3600000);
		
		Algorithm algorithm = Algorithm.HMAC256(secretKey);
		return JWT.create()
				.withSubject(login)
				.withIssuedAt(now)
				.withExpiresAt(validity)
				.sign(algorithm);
	}
	
	public Authentication validateToken(String token)
	{
		Algorithm algorithm = Algorithm.HMAC256(secretKey);
		
		JWTVerifier verifier = JWT.require(algorithm).build();
		
		DecodedJWT decoded = verifier.verify(token);
		
		EmployeeDto emp = empService.findByLogin(decoded.getSubject());
		
		return new UsernamePasswordAuthenticationToken(emp,null,Collections.emptyList());

		
	}
	
	
}
