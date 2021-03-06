package com.example.demo.config;

import com.example.demo.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MyUsersDetails implements UserDetails {

    private String userName;
    private String password;
    private List<GrantedAuthority> authorities = new ArrayList<>();


    public MyUsersDetails(User user) {
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.authorities = Arrays.stream(user.getRoles().split(" "))
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());

    }

    public MyUsersDetails() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
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
        return true;
    }
}
