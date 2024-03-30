package com.smartagilify.authserver.domain.transformer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import org.springframework.security.oauth2.core.AuthorizationGrantType;

@Converter
public class AuthGrantTypeSetToStringConverter implements AttributeConverter<Set<AuthorizationGrantType>, String> {
	private static final String DELIMITER = ",";

	@Override
	public String convertToDatabaseColumn(Set<AuthorizationGrantType> roles) {
		return roles != null ? String.join(DELIMITER, roles.stream().map(AuthorizationGrantType::getValue).toList()) : null;
	}

	@Override
	public Set<AuthorizationGrantType> convertToEntityAttribute(String dbData) {
		//TODO there should be a better way
		HashMap<String, AuthorizationGrantType> cm = new HashMap<>(6);
		cm.put(AuthorizationGrantType.AUTHORIZATION_CODE.getValue(), AuthorizationGrantType.AUTHORIZATION_CODE);
		cm.put(AuthorizationGrantType.REFRESH_TOKEN.getValue(), AuthorizationGrantType.REFRESH_TOKEN);
		cm.put(AuthorizationGrantType.CLIENT_CREDENTIALS.getValue(), AuthorizationGrantType.CLIENT_CREDENTIALS);
		cm.put(AuthorizationGrantType.PASSWORD.getValue(), AuthorizationGrantType.PASSWORD);
		cm.put(AuthorizationGrantType.JWT_BEARER.getValue(), AuthorizationGrantType.JWT_BEARER);
		cm.put(AuthorizationGrantType.DEVICE_CODE.getValue(), AuthorizationGrantType.DEVICE_CODE);
		return dbData != null ? new HashSet<>(Arrays.stream(dbData.split(DELIMITER)).map(cm::get).toList()) : null;
	}
}
