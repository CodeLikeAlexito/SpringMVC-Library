package com.example.library.service;

import com.example.library.model.User;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.library.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
	public List<User> listAllUser();
}