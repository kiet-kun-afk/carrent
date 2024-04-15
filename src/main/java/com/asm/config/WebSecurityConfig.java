package com.asm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.asm.auth.MyUserDetailsService;
import com.asm.oauth2.CustomOAuth2UserService;
import com.asm.oauth2.CustomUserDetailService;
import com.asm.oauth2.OAuth2LoginSuccessHandler;


@EnableMethodSecurity
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	@Autowired
	MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Autowired
	private CustomOAuth2UserService customOAuth2;
	
	@Autowired
	private OAuth2LoginSuccessHandler successOAuth2;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(customUserDetailService);
        authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	public SecurityFilterChain securityFilter(HttpSecurity http) throws Exception {

		http.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(auth -> auth
						.anyRequest()
						.permitAll())
				.formLogin(login -> login
						.loginPage("/car/login")
						.loginProcessingUrl("/login")
						.usernameParameter("tendn")
						.passwordParameter("password")
						.permitAll()
						.defaultSuccessUrl("/car/index", true)
						)
				.oauth2Login(ou -> ou.authorizationEndpoint(e -> e
						.baseUri("/oauth2/authorization"))
						.successHandler(successOAuth2).redirectionEndpoint(e -> e.baseUri("/login/oauth2/code/*"))
						.userInfoEndpoint(e -> e
								.userService(customOAuth2)))
				.logout(l -> l.logoutSuccessUrl("/car/index"))
				.exceptionHandling(e -> e
						.accessDeniedPage("/accessDenied"));

		return http.build();
	}

	@Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailService);      
    }
}
