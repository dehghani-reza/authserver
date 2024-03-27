package com.smartagilify.authserver.domain.transformer;

import java.util.Collection;
import java.util.HashSet;

import com.smartagilify.authserver.domain.entity.AppUser;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@RequiredArgsConstructor
public class MyUserPrincipal implements UserDetails {

	private final AppUser user;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		HashSet<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.name())));
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return !user.getIsAccountExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return !user.getIsAccountLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !user.getIsCredentialExpired();
	}

	@Override
	public boolean isEnabled() {
		return user.getIsEnable();
	}
}
