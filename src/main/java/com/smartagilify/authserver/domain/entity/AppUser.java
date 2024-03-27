package com.smartagilify.authserver.domain.entity;

import java.util.Set;

import com.smartagilify.authserver.domain.enumerations.Role;
import com.smartagilify.authserver.domain.transformer.SetToStringConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "APP_USER")
public class AppUser extends BaseEntity {

	@Column(name = "USERNAME", nullable = false, unique = true)
	private String username;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Column(name = "IS_ACCOUNT_EXPIRED", nullable = false)
	private Boolean isAccountExpired;

	@Column(name = "IS_ACCOUNT_LOCKED", nullable = false)
	private Boolean isAccountLocked;

	@Column(name = "IS_CREDENTIAL_EXPIRED", nullable = false)
	private Boolean isCredentialExpired;

	@Column(name = "IS_ENABLE", nullable = false)
	private Boolean isEnable;

	@Column(name = "ROLE", nullable = false)
	@Convert(converter = SetToStringConverter.class)
	private Set<Role> roles;
}

