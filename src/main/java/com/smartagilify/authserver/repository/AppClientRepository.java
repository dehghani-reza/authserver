package com.smartagilify.authserver.repository;

import java.util.Optional;
import java.util.UUID;

import com.smartagilify.authserver.domain.entity.AppClient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppClientRepository extends JpaRepository<AppClient, UUID> {

	Optional<AppClient> findByClientId(String clientId);
}
