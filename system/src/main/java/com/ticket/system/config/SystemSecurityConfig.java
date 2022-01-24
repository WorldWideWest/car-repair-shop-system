package com.ticket.system.config;

import javax.sql.DataSource;

// import com.ticket.system.auth.CustomUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration @EnableWebSecurity
public class SystemSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private DataSource dataSource;

    // @Bean
    // public UserDetailsService userDetailsService(){
    //     return new CustomUserDetailsService();
    // }

    // @Bean
    // public BCryptPasswordEncoder passwordEncoder(){
    //     return new BCryptPasswordEncoder();
    // }

    // @Bean
    // public DaoAuthenticationProvider authenticationProvider(){
    //     DaoAuthenticationProvider authenticationProvider = 
    //         new DaoAuthenticationProvider();

    //     authenticationProvider.setUserDetailsService(userDetailsService());
    //     authenticationProvider.setPasswordEncoder(passwordEncoder());
    //     return authenticationProvider;
    // }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // auth.authenticationProvider(authenticationProvider());
        // auth.jdbcAuthentication().dataSource(dataSource);

        UserBuilder users = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication()
            .withUser(users.username("dzenan.dzafic").password("eminem662").roles("MANAGER", "EMPLOYEE"));

        // JPA -authentication

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
