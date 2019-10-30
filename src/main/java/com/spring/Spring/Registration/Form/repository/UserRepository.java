package com.spring.Spring.Registration.Form.repository;

import com.spring.Spring.Registration.Form.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
