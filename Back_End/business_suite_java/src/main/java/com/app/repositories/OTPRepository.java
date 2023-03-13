package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.OTP;

public interface OTPRepository extends JpaRepository<OTP, String> {

}
