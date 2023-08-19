package com.masai.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain springSecurityConfiguration(HttpSecurity http) throws Exception {

		http.sessionManagement(
				sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

				.cors(cors -> {

					cors.configurationSource(new CorsConfigurationSource() {

						@Override
						public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

							CorsConfiguration cfg = new CorsConfiguration();

							cfg.setAllowedOriginPatterns(Collections.singletonList("*"));
							cfg.setAllowedMethods(Collections.singletonList("*"));
							cfg.setAllowCredentials(true);
							cfg.setAllowedHeaders(Collections.singletonList("*"));
							cfg.setExposedHeaders(Arrays.asList("Authorization"));
							return cfg;

						}
					});

				}).authorizeHttpRequests(auth -> {
					auth.requestMatchers("/**").permitAll();
					auth.requestMatchers(HttpMethod.POST, "travel/customer/signup", "travel/addAdmin").permitAll()

							.requestMatchers(HttpMethod.POST, "travel/updateAdmin", "travel/adddestination",
									"travel/bus", "travel/bus/travels", "travel/destination",
									"travel/hotel")
							.hasRole("ADMIN")

							.requestMatchers("travel/Destination", "travel/Destination/travels",
									 "travel/reports","travel/route/update")
							.hasRole("ADMIN")

							.requestMatchers("travel/customers", "travel/bus", "travel/customers","travel/feedback",
									"travel/customer/delete", "travel/customer/update", "travel/customer",
									"travel/Destination", "travel/feedback/customer", "travel/Hotel/Destination",
									"travel/package", "travel/Payment","travel/Packages", "travel/report","travel/travels","travel/route")
							.hasAnyRole("USER","ADMIN")

							.requestMatchers("/swagger-ui*/**", "/v3/api-docs/**").permitAll()
							.requestMatchers("travel/customers/**", "travel/HotelBooking", "travel/Hotel", "packages")
							.hasAnyRole("ADMIN", "USER").anyRequest().authenticated();

				}).csrf(csrf -> csrf.disable())
				.addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
				.addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
				.formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());

		return http.build();
//

	}

	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();

	}
}
