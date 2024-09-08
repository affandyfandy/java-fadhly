package com.lecture25.assignment1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lecture25.assignment1.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
