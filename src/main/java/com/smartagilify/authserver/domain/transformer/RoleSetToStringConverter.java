package com.smartagilify.authserver.domain.transformer;

import com.smartagilify.authserver.domain.enumerations.Role;
import jakarta.persistence.Converter;

@Converter
public class RoleSetToStringConverter extends EnumSetToStringConverter {
	@Override
	Class getEnumClass() {
		return Role.class;
	}

}
