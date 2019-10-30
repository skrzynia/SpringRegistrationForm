package com.spring.Spring.Registration.Form.service;

import com.spring.Spring.Registration.Form.entity.Role;
import com.spring.Spring.Registration.Form.entity.User;
import com.spring.Spring.Registration.Form.repository.UserRepository;
import com.spring.Spring.Registration.Form.validation.UserRegistratrionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findByEmail(String mail) {
        return userRepository.findByEmail(mail);
    }

    @Override
    public User save(UserRegistratrionDto userRegistratrionDto) {
        User user = new User();
        user.setFirstName(userRegistratrionDto.getFirstName());
        user.setLastName(userRegistratrionDto.getLastName());
        user.setPassword(bCryptPasswordEncoder.encode(userRegistratrionDto.getPassword()));
        user.setEmail(userRegistratrionDto.getEmail());
        user.setRoles(Arrays.asList(new Role("ROLE_USER ")));

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(), mapRolesToAuthorities(user.getRoles())
                );
    }

    private Collection<?extends GrantedAuthority> mapRolesToAuthorities(Collection< Role > roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
