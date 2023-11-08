package ru.otus.example.grooming.gsmaster.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.otus.example.grooming.gsmaster.entities.UserEntity;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ApiUserDetails implements UserDetails {
    private UserEntity apiUser;

    public ApiUserDetails(UserEntity apiUser) {
        this.apiUser = apiUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final List<GrantedAuthority> authorities
                = Collections.singletonList(new SimpleGrantedAuthority("USER"));
        return authorities;
    }

    @Override
    public String getPassword() {
        return apiUser.getPassword();
    }

    @Override
    public String getUsername() {
        return apiUser.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return apiUser.getEnabled();
    }
}