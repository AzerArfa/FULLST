package com.auth.config;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration {


	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    return http
	            .csrf().disable()
	            .authorizeHttpRequests(auth -> auth
	            	    .requestMatchers("/auth/signup").permitAll()
	            	    .requestMatchers("/auth/users").permitAll()
	            	    .requestMatchers("/auth/delete/**").permitAll()
	            	    .requestMatchers("/auth/login").permitAll()
	            	    .requestMatchers("/auth/user/**").permitAll()
	            	    .requestMatchers("/auth/updateuser/**").permitAll()
	            	    .requestMatchers("/auth/roles").permitAll()
	            	    .requestMatchers("/auth/entreprises").permitAll()
	            	    .requestMatchers("/auth/user/{userId}/add-entreprise").permitAll()
	            	    .requestMatchers("/auth/user/{userId}/entreprises").permitAll()
	            	    .requestMatchers("/auth/users/**").permitAll()
	            	    .requestMatchers("/auth/updatepassword").permitAll()
	            	    .requestMatchers("/auth/make-user/**").hasAnyRole("SUPERADMIN")
	            	    .requestMatchers("/auth/make-admin/**").hasAnyRole("SUPERADMIN")
	            	    .anyRequest().authenticated()
	            	)
	            .sessionManagement()
	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	            .and()
	            .build();
	}


	 
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**")
	            .allowedOrigins("*")
	            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
	            .allowedHeaders("*")
	            .exposedHeaders("Authorization")
	            .allowCredentials(true)
	            .maxAge(3600);
	    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
