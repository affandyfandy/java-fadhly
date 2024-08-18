package com.week10.authentication.repository;

import com.week10.authentication.entity.Authentication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepository extends JpaRepository<Authentication, String> {

}
