package com.smartagilify.authserver.domain.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	@Serial
	private static final long serialVersionUID = 6763148085164464446L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Version
	private Long version;
}