package com.khoahd7621.youngblack.securities;

import com.khoahd7621.youngblack.constants.ERoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    @Autowired
    private ExceptionHandlerFilter exceptionHandlerFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.authorizeRequests()
                .antMatchers("/api/v1/login").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/user").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/user").hasAuthority(ERoles.ADMIN.toString())
                .antMatchers("/api/**").authenticated();
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.addFilterBefore(exceptionHandlerFilter, JwtRequestFilter.class);
        return httpSecurity.build();
    }
}
