package com.winter.app.configs.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtTokenManager jwtTokenManager;
	
	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;
	
	@Autowired
	private AddLogoutSucessHandler addLogoutSucessHandler;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
		httpSecurity
			.cors(cors-> cors.configurationSource(this.corsConfiguration()))
			.csrf(csrf-> csrf.disable())
		//1. 권한 설정
			.authorizeHttpRequests(
				auth -> {
					auth
						.requestMatchers("/api/notice/add").hasRole("ADMIN")
						.requestMatchers("/api/notice").authenticated()
						.anyRequest().permitAll();
					
					
			})
		
		//2. Form Login
			.formLogin(formLogin-> formLogin.disable())
		
		//3. Logout	
			.logout(logout -> {
				logout
				.logoutUrl("/api/member/logout")
				.invalidateHttpSession(true)
				.deleteCookies("access_token", "refresh_token")
				.logoutSuccessHandler(addLogoutSucessHandler);
			})
			
		//4. Session
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		
		//5. HttpBasic
			.httpBasic(http -> http.disable())
		
		//6. Token에 관련된 필터를 등록	
			.addFilter(new JwtAuthenticationFilter(this.authenticationConfiguration.getAuthenticationManager(), jwtTokenManager))
			.addFilter(new JwtLoginFilter(this.authenticationConfiguration.getAuthenticationManager(), jwtTokenManager))
			;
		
		return httpSecurity.build();
		
	}
	
	CorsConfigurationSource corsConfiguration() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(List.of("*"));
		
		//configuration.setAllowCredentials(true);를 사용하면
		//setAllowedOrigins를 사용 이 안됨
		//setAllowedOriginPatterns를 사용
		//* 를 사용 못함
		
//		configuration.setAllowedOriginPatterns(List.of("http://localhost:5173"));
//		configuration.setAllowCredentials(true);
		
		//Method에서 *은 사용 X
		configuration.setAllowedMethods(List.of("GET", "POST", "PATCH", "DELETE", "PUT", "OPTIONS"));
		configuration.setAllowedHeaders(List.of("*"));
		
		configuration.setExposedHeaders(List.of("accesstoken", "Accesstoken"));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		
		return source;
	}

}
