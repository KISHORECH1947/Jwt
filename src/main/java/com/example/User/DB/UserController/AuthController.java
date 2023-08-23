package com.example.User.DB.UserController;

import com.example.User.DB.DTO.AuthenticationResponse;
import com.example.User.DB.JwtUtil.JwtUtility;
import com.example.User.DB.LoginRequest;
import com.example.User.DB.UserServiceImpl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.DataInputStream;
import java.io.IOException;

@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtUtility jwtUtility;
@PostMapping("/authentication")
    public AuthenticationResponse createAuthenticationToken
            (@RequestBody LoginRequest loginRequest, HttpServletResponse response) throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect Username or Password");

        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        final String  jwt= jwtUtility.generateToken(userDetails.getUsername());
        return new AuthenticationResponse(jwt);
    }
}













