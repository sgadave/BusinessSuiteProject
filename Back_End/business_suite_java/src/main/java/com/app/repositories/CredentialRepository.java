package com.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.UserCredential;

public interface CredentialRepository extends JpaRepository<UserCredential, String>  {
	Optional<UserCredential> findByUserNameAndPassword(String userName, String password);
	Optional<UserCredential> findByUserName(String userName );
}
