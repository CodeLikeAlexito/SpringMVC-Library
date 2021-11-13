package com.example.library.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.service.UserService;
import com.example.library.dto.UserRegistrationDto;
import com.example.library.model.Book;
import com.example.library.model.User;

import javax.xml.ws.Response;

@Slf4j
@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class UserRegistrationController {

	private UserService userService;

	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }
	
	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}
	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
		userService.save(registrationDto);
		return "redirect:/registration?success";
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<User>> viewAllUsers() {
		List<User> listUser = userService.listAllUser();
//		model.addAttribute("listUser", listUser);
		return ResponseEntity.ok(listUser);
	}
	
	/*
	@GetMapping
	public List<User> list() {
        return userService.listAllUser();
    }*/
	
	/*
	@PostMapping("/")
	public String registerUserAccount (@RequestBody UserRegistrationDto userObj) {
		userService.save(userObj);
		return "success";
	}*/
}
