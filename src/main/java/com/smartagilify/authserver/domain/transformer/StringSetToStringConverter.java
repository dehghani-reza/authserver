package com.smartagilify.authserver.domain.transformer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class StringSetToStringConverter implements AttributeConverter<Set<String>, String> {
	private static final String DELIMITER = ",";

	@Override
	public String convertToDatabaseColumn(Set<String> roles) {
		return roles != null ? String.join(DELIMITER, roles.stream().toList()) : null;
	}

	@Override
	public Set<String> convertToEntityAttribute(String dbData) {
		return dbData != null ? new HashSet<>(Arrays.stream(dbData.split(DELIMITER)).toList()) : null;
	}
}
