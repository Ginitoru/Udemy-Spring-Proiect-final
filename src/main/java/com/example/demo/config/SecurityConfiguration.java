package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    private final UserDetailsService userDetailService;

    @Autowired
    public SecurityConfiguration(@Qualifier("myUserDetailService") UserDetailsService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailService);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
              //  .antMatchers("/users/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/welcome/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/").authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                .and()
                .logout().permitAll()
                    .and()
                    .exceptionHandling().accessDeniedPage("/access-denied");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }



}
