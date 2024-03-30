package com.smartagilify.authserver.domain.enumerations;

import java.util.Arrays;

import com.smartagilify.authserver.exception.BusinessException;
import lombok.Getter;

@Getter
public enum PermissionScope {
	USER_READ("user.read"), USER_WRITE("user.write"), OPEN_ID("openid");

	private final String scopeValue;

	PermissionScope(String scopeValue) {
		this.scopeValue = scopeValue;
	}

	public static PermissionScope findByScopeValue(String iScopeValue) {
		return Arrays.stream(PermissionScope.values()).filter(permissionScope -> permissionScope.scopeValue.equalsIgnoreCase(iScopeValue))
				.findFirst().orElseThrow(BusinessException::new);
	}
}
