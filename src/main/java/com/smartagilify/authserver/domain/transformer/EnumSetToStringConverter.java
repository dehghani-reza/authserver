package com.smartagilify.authserver.domain.transformer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.AttributeConverter;

public abstract class EnumSetToStringConverter implements AttributeConverter<Set<Enum>, String> {
	protected static final String DELIMITER = ",";

	abstract Class getEnumClass();

	@Override
	public String convertToDatabaseColumn(Set<Enum> roles) {
		return roles != null ? String.join(DELIMITER, roles.stream().map(Enum::name).toList()) : null;
	}

	@Override
	public Set<Enum> convertToEntityAttribute(String dbData) {
		List<Enum> list = Arrays.stream(dbData.split(DELIMITER)).map(name -> Enum.valueOf(getEnumClass(), name)).toList();
		return dbData != null ? new HashSet<>(list) : null;
	}
}
