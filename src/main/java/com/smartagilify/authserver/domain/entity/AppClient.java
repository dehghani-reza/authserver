package com.smartagilify.authserver.domain.entity;

import java.util.Set;

import com.smartagilify.authserver.domain.enumerations.ClientAuthType;
import com.smartagilify.authserver.domain.enumerations.PermissionScope;
import com.smartagilify.authserver.domain.transformer.AuthGrantTypeSetToStringConverter;
import com.smartagilify.authserver.domain.transformer.AuthMethodSetToStringConverter;
import com.smartagilify.authserver.domain.transformer.PermissionScopeSetToStringConverter;
import com.smartagilify.authserver.domain.transformer.StringSetToStringConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "APP_Client")
public class AppClient extends BaseEntity {

	@Column(name = "CLIENT_ID", nullable = false, unique = true)
	private String clientId;

	@Column(name = "CLIENT_SECRET", nullable = false)
	private String clientSecret;

	@Column(name = "CLIENT_NAME")
	private String clientName;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "CLIENT_OWNER")
	private String clientOwner;// Information about the organization or individual responsible for developing and maintaining the client application.

	@Enumerated(EnumType.STRING)
	@Column(name = "CLIENT_AUTH_TYPE", nullable = false)
	private ClientAuthType clientAuthType;

	@Column(name = "AUTHORIZATION_GRANT_TYPES", nullable = false)
	@Convert(converter = AuthGrantTypeSetToStringConverter.class)
	private Set<AuthorizationGrantType> authorizationGrantTypes;

	@Column(name = "CLIENT_AUTHENTICATION_METHODS", nullable = false)
	@Convert(converter = AuthMethodSetToStringConverter.class)
	private Set<ClientAuthenticationMethod> clientAuthenticationMethods;

	@Column(name = "REDIRECT_URIS", nullable = false)
	@Convert(converter = StringSetToStringConverter.class)
	private Set<String> redirectUris;

	@Column(name = "POST_LOGOUT_REDIRECT_URI")
	private String postLogoutRedirectUri;

	@Column(name = "SCOPES", nullable = false)
	@Convert(converter = PermissionScopeSetToStringConverter.class)
	private Set<PermissionScope> scopes;
}
