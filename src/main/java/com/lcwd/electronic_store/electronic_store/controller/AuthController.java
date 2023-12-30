package com.lcwd.electronic_store.electronic_store.controller;

import com.lcwd.electronic_store.electronic_store.config.ProjectConfig;
import com.lcwd.electronic_store.electronic_store.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private ProjectConfig projectConfig;
    @RequestMapping("/curr")
    public ResponseEntity<UserDto> getCurrentUser(Principal principal){
        UserDto userDto= projectConfig.modelMapper().map(userDetailsService.loadUserByUsername(principal.getName()), UserDto.class);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
