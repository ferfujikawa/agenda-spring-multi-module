package com.agendaspring.thymeleaf.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class AutenticacaoConfig {

    private AuthenticationProvider authenticationProvider;
	
	public AutenticacaoConfig(ApiAuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
	public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationSuccessHandler customAuthenticationSuccessHandler) throws Exception {
		
		http
            .authenticationProvider(authenticationProvider)
            .authorizeHttpRequests(configurer ->
                configurer
					.antMatchers("/login").permitAll()
					.antMatchers("/webjars/**").permitAll()
                    .anyRequest().authenticated()
			)
			.formLogin(form ->
				form
					.loginPage("/login")
					.loginProcessingUrl("/authenticate")
					.successHandler(customAuthenticationSuccessHandler)
			)
			.logout(logout -> logout.logoutSuccessUrl("/login?logout"));
		
		return http.build();
	}
}
