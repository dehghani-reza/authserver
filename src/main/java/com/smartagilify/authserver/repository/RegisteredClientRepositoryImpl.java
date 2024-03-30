package com.smartagilify.authserver.repository;

import java.time.Duration;
import java.time.ZoneId;
import java.util.UUID;
import java.util.stream.Collectors;

import com.smartagilify.authserver.domain.entity.AppClient;
import com.smartagilify.authserver.domain.enumerations.PermissionScope;
import com.smartagilify.authserver.exception.BusinessException;
import lombok.RequiredArgsConstructor;

import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class RegisteredClientRepositoryImpl implements RegisteredClientRepository {

	private final AppClientRepository appClientRepository;

	@Override
	public void save(RegisteredClient registeredClient) {
		appClientRepository.save(appClientMapper(registeredClient));
	}

	@Override
	public RegisteredClient findById(String id) {
		return clientMapper(appClientRepository.findById(UUID.fromString(id)).orElseThrow(BusinessException::new));
	}

	@Override
	public RegisteredClient findByClientId(String clientId) {
		return clientMapper(appClientRepository.findByClientId(clientId).orElseThrow(BusinessException::new));
	}

	private static RegisteredClient clientMapper(AppClient appClient) {
		//TODO add expired to client
		//TODO find out clientSettings
		//TODO find out tokenSettings
		return RegisteredClient
				.withId(appClient.getId().toString())
				.clientId(appClient.getClientId())
				.clientIdIssuedAt(appClient.getCreateDate().atZone(ZoneId.systemDefault()).toInstant())
				.clientSecret(appClient.getClientSecret())
//				.clientSecretExpiresAt()
				.clientName(appClient.getClientName())
				.clientAuthenticationMethods(c -> c.addAll(appClient.getClientAuthenticationMethods()))
				.authorizationGrantTypes(a -> a.addAll(appClient.getAuthorizationGrantTypes()))
				.redirectUris(r -> r.addAll(appClient.getRedirectUris()))
				.postLogoutRedirectUri(appClient.getPostLogoutRedirectUri())
				.scopes(s -> appClient.getScopes().forEach(scope -> s.add(scope.getScopeValue())))
				.clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
				.tokenSettings(TokenSettings.builder().accessTokenTimeToLive(Duration.ofMinutes(5L)).build())
				.build();
	}

	private static AppClient appClientMapper(RegisteredClient registeredClient) {
		return AppClient.builder()
				.clientId(registeredClient.getClientId())
				.clientSecret(registeredClient.getClientSecret())
				.clientName(registeredClient.getClientName())
				.clientAuthenticationMethods(registeredClient.getClientAuthenticationMethods())
				.clientAuthenticationMethods(registeredClient.getClientAuthenticationMethods())
				.redirectUris(registeredClient.getRedirectUris())
				.postLogoutRedirectUri(registeredClient.getPostLogoutRedirectUris().stream().findFirst().orElseThrow(BusinessException::new))
				.scopes(registeredClient.getScopes().stream().map(PermissionScope::valueOf).collect(Collectors.toSet()))
				.build();
	}
}
