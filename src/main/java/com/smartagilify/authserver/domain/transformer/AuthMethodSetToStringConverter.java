package com.smartagilify.authserver.domain.transformer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

@Converter
public class AuthMethodSetToStringConverter implements AttributeConverter<Set<ClientAuthenticationMethod>, String> {
	private static final String DELIMITER = ",";

	@Override
	public String convertToDatabaseColumn(Set<ClientAuthenticationMethod> roles) {
		return roles != null ? String.join(DELIMITER, roles.stream().map(ClientAuthenticationMethod::getValue).toList()) : null;
	}

	@Override
	public Set<ClientAuthenticationMethod> convertToEntityAttribute(String dbData) {
		//TODO there should be a better way
		HashMap<String, ClientAuthenticationMethod> cm = new HashMap<>(5);
		cm.put(ClientAuthenticationMethod.CLIENT_SECRET_BASIC.getValue(), ClientAuthenticationMethod.CLIENT_SECRET_BASIC);
		cm.put(ClientAuthenticationMethod.CLIENT_SECRET_POST.getValue(), ClientAuthenticationMethod.CLIENT_SECRET_POST);
		cm.put(ClientAuthenticationMethod.CLIENT_SECRET_JWT.getValue(), ClientAuthenticationMethod.CLIENT_SECRET_JWT);
		cm.put(ClientAuthenticationMethod.PRIVATE_KEY_JWT.getValue(), ClientAuthenticationMethod.PRIVATE_KEY_JWT);
		cm.put(ClientAuthenticationMethod.NONE.getValue(), ClientAuthenticationMethod.NONE);
		return dbData != null ? new HashSet<>(Arrays.stream(dbData.split(DELIMITER)).map(cm::get).toList()) : null;
	}
}
