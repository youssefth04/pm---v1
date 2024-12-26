package com.ysf.pm.interceptors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Component
public class FeignInterceptor implements RequestInterceptor {

	@Override
	public void apply(RequestTemplate template) {
	SecurityContext context= SecurityContextHolder.getContext();
	Authentication authentication = context.getAuthentication();
	JwtAuthenticationToken authenticationToken= (JwtAuthenticationToken) authentication;
	String accessToken = authenticationToken.getToken().getTokenValue();
	template.header("Authorization","Bearer"+accessToken);
	}
	
	
}
