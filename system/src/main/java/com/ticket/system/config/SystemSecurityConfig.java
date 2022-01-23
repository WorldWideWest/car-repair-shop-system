package com.ticket.system.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration @EnableWebSecurity
public class SystemSecurityConfig extends WebSecurityConfigurerAdapter{

    // Adding reference to security data source
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // UserBuilder users = User.withDefaultPasswordEncoder();

        // auth.inMemoryAuthentication()
        //     .withUser(users.username("dzenan.dzafic").password("eminem662").roles("MANAGER"));

        // JPA -authentication

        auth.jdbcAuthentication().dataSource(dataSource);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/status/**").hasRole("EMPLOYEE")
                .antMatchers("/ticket/**").hasRole("EMPLOYEE")
            .and()
                .formLogin()
                .permitAll()
            .and()
                .logout()
                .permitAll()
            .and()
                .exceptionHandling().accessDeniedPage("/access-denied");

    }

    
    
}
