package com.Disney.DisneyApp.security.filter;

import com.Disney.DisneyApp.models.UsuarioEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.Disney.DisneyApp.security.jwt.JwtUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final  JwtUtils jwtUtils;

    public JwtAuthenticationFilter(JwtUtils token) {
        this.jwtUtils = token;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        UsuarioEntity usuarioEntity;
        String username="";
        String password="";
        try{
            usuarioEntity=new ObjectMapper().readValue(request.getInputStream(),UsuarioEntity.class);
            username= usuarioEntity.getEmail();
            password=usuarioEntity.getPassword();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UsernamePasswordAuthenticationToken authenticationToken=
                new UsernamePasswordAuthenticationToken(username,password);
        return getAuthenticationManager().authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request
                                            ,HttpServletResponse response
                                            ,FilterChain chain
                                            ,Authentication authResult)
                                            throws IOException, ServletException {

        User userDetails = (User) authResult.getPrincipal();
        String token = jwtUtils.generarToken(userDetails.getUsername());

        response.addHeader("Authorization", token);
        Map<String, Object> httpResponse = new HashMap<>();
        httpResponse.put("token", token);
        httpResponse.put("message", "Autenticacion Correcta");
        httpResponse.put("username", userDetails.getUsername());

        response.getWriter().write(new ObjectMapper().writer().writeValueAsString(httpResponse));
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().flush();

        super.successfulAuthentication(request, response, chain, authResult);
    }
}
