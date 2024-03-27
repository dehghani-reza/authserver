package com.smartagilify.authserver.domain.transformer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.smartagilify.authserver.domain.enumerations.Role;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class SetToStringConverter implements AttributeConverter<Set<Role>, String> {
	private static final String DELIMITER = ",";

	@Override
	public String convertToDatabaseColumn(Set<Role> roles) {
		return roles != null ? String.join(DELIMITER, roles.stream().map(Enum::name).toList()) : null;
	}

	@Override
	public Set<Role> convertToEntityAttribute(String dbData) {
		return dbData != null ? new HashSet<>(Arrays.stream(dbData.split(DELIMITER)).map(Role::valueOf).toList()) : null;
	}
}
