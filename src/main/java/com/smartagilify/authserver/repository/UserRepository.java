package com.smartagilify.authserver.repository;

import java.util.UUID;

import com.smartagilify.authserver.domain.entity.AppUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, UUID> {

	AppUser findByUsername(String username);
}
