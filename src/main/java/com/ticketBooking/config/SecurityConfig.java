package com.ticketBooking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)

public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService(){

        UserDetails admin  = User.builder().username("aadi").password(passwordEncoder().encode("1234")).roles("ADMIN").build();

        UserDetails user  = User.builder().username("shaurabh").password(passwordEncoder().encode("1234")).roles("USER").build();

        return new InMemoryUserDetailsManager (admin,user);

    }


    @Bean
    public SecurityFilterChain  securityFilterChain(HttpSecurity security) throws Exception {

        return security.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/api/user/registerUser").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/tickets/bookTicket").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/tickets/findTicketById").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/tickets/getAllTickets").hasRole("USER")

                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .build();

    }

}
///api/tickets/findTicketById/3