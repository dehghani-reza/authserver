package com.smartagilify.authserver.domain.transformer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.smartagilify.authserver.domain.enumerations.PermissionScope;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PermissionScopeSetToStringConverter implements AttributeConverter<Set<PermissionScope>, String> {

	protected static final String DELIMITER = ",";

	@Override
	public String convertToDatabaseColumn(Set<PermissionScope> roles) {
		return roles != null ? String.join(DELIMITER, roles.stream().map(PermissionScope::getScopeValue).toList()) : null;
	}

	@Override
	public Set<PermissionScope> convertToEntityAttribute(String dbData) {
		return dbData != null ? new HashSet<>(Arrays.stream(dbData.split(DELIMITER)).map(PermissionScope::findByScopeValue).toList()) : null;
	}
}
