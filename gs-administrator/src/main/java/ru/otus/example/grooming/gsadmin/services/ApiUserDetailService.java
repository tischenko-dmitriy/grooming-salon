package ru.otus.example.grooming.gsadmin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.otus.example.grooming.gsadmin.entities.UserEntity;
import ru.otus.example.grooming.gsadmin.exceptions.UserNotFoundException;
import ru.otus.example.grooming.gsadmin.model.ApiUserDetails;
import ru.otus.example.grooming.gsadmin.repositories.UserRepository;

@Service
public class ApiUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public ApiUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByLogin(s).orElse(null);
        if (userEntity == null) {
            throw new UserNotFoundException(s);
        }

        return new ApiUserDetails(userEntity);
    }

}