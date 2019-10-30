package com.spring.Spring.Registration.Form.service;

import com.spring.Spring.Registration.Form.entity.User;
import com.spring.Spring.Registration.Form.validation.UserRegistratrionDto;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByEmail(String mail);

    User save(UserRegistratrionDto userRegistratrionDto);
}
