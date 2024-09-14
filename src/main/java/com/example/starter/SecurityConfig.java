package com.example.starter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.example.starter.services.CustAuthService;

import  static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
//	@Bena
//	permit all endpoints
//	public SecurityFilterChain securityfilterChain(HttpSecurity http) throws Exception{
//		return http.csrf(csrf->csrf.disable())
//		.authorizeHttpRequests(authorize->{
//			authorize.anyRequest().permitAll();
//		})
//		.httpBasic(withDefaults())
//		.build();
//		
//	}
	@Autowired
	CustAuthService custAuthService;
	@Bean
	public  PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(custAuthService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
		
	}
	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		
//		UserDetails normalUser=User
//				.withUsername("User")
//				.password(passwordEncoder().encode("abc"))
//				.roles("USER")
//				.build();
//		
//		UserDetails normalUser2=User
//				.withUsername("User2")
//				.password(passwordEncoder().encode("ab"))
//				.roles("USER2")
//				.build();
//		
//		UserDetails adminUser=User
//				.withUsername("admin")
//				.password(passwordEncoder().encode("abcd"))
//				.roles("USER","ADMIN")
//				.build();
//		UserDetails adminUser2=User
//				.withUsername("admin2")
//				.password(passwordEncoder().encode("abcde"))
//				.roles("USER","ADMIN")
//				.build();
//		
//		InMemoryUserDetailsManager detailsManager=new InMemoryUserDetailsManager(
//				normalUser,
//				normalUser2,
//				adminUser,
//				adminUser2
//				);
//		return detailsManager;
//	}
	@Bean
	public SecurityFilterChain securityfilterChain(HttpSecurity http) throws Exception{
		return http.csrf(csrf->csrf.disable())
		.authorizeHttpRequests(authorize->{
			authorize.requestMatchers(HttpMethod.POST,"/blogs/**").hasRole("ADMIN")
			.requestMatchers(HttpMethod.PUT,"/blogs/**").hasRole("ADMIN")
			.requestMatchers(HttpMethod.DELETE,"/blogs/**").hasRole("ADMIN")
			.requestMatchers(HttpMethod.GET,"/blogs").permitAll()
			.requestMatchers(HttpMethod.GET,"/blogs/**").permitAll()
			.requestMatchers(HttpMethod.POST,"/auth/register").permitAll()
			.anyRequest().authenticated();
		})
		.httpBasic(withDefaults())
		.build();
		
	}
	
	

}
