package com.smartagilify.authserver.service;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailService {

	@Bean
	InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		return new InMemoryUserDetailsManager(
				User.withDefaultPasswordEncoder()
						.roles("admin")
						.username("yunus")
						.password("pw")
						.build(),
				User.withDefaultPasswordEncoder()
						.roles("user")
						.username("mohammad")
						.password("pw")
						.build());
	}
}
