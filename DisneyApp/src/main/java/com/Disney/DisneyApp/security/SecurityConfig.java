package com.Disney.DisneyApp.security;

import com.Disney.DisneyApp.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.Disney.DisneyApp.security.filter.JwtAuthenticationFilter;
import com.Disney.DisneyApp.security.filter.JwtAuthorizationFilter;
import com.Disney.DisneyApp.security.jwt.JwtUtils;

@Configuration
@EnableMethodSecurity(prePostEnabled = true) // Habilita @PreAuthorize
public class SecurityConfig {

    @Autowired
    JwtAuthorizationFilter authorizationFilter ;
    @Autowired
    JwtUtils jwtUtils;



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config
                                                        ) throws Exception {
        return config.getAuthenticationManager();
    }


    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
    //    JwtAuthenticationFilter jwtAuthenticationFilter=new JwtAuthenticationFilter(jwtUtils);
      //  jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
        //jwtAuthenticationFilter.setFilterProcessesUrl("/api/auth/login");


        return  http
                .csrf(csrf -> csrf.disable())
                //desactivar cabeceras para ver H2 en navegador
                .headers(headers -> headers
                        .defaultsDisabled()   // desactiva todas las cabeceras por defecto
                        .frameOptions(frame -> frame.sameOrigin()) // permite iframe solo del mismo origen (H2)
                )
                .sessionManagement((session)->{
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
          //      .addFilter(jwtAuthenticationFilter)
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }



}
