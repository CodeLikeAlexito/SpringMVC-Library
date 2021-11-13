package com.example.library.controller;

import java.awt.SystemColor;
import java.security.Principal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.library.dto.BookResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.library.dto.UserRegistrationDto;
import com.example.library.model.Book;
import com.example.library.model.User;
import com.example.library.service.BookService;

@Slf4j
@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

	private final BookService bookService;
	
	@GetMapping("/view-all-books")
	public ResponseEntity<List<Book>> viewAllBooks() {
		List<Book> listOfBooks = bookService.getAllBooks();
		return ResponseEntity.ok(listOfBooks);
	}
	
//	@GetMapping("/new")
//	public String showForm(Model model) {
//		Book book = new Book();
//		model.addAttribute("book", book);
//		return "new_book";
//	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> getBook(@PathVariable long id) {
		return ResponseEntity.ok(bookService.getBookById(id));
	}
	
	@PostMapping
	public String saveBook(@ModelAttribute("book") Book bookObj, Authentication auth) {
		bookService.saveBook(bookObj, auth);
		
		return "redirect:/";
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView showEditForm(@PathVariable(name = "id") long id) {
		ModelAndView mav = new ModelAndView("edit_book");
		Book book = bookService.getBookById(id);
		mav.addObject("book", book);
		
		return mav;
	}
	
	@PutMapping("/{id}")
	public void updateBook(@RequestBody Book bookObj, @PathVariable long id) {
		bookService.updateBook(id, bookObj);
	}
	
	@GetMapping("/delete/{id}")
	public String deleteBook(@PathVariable(name = "id") long id) {
		bookService.deleteBookById(id);
		return "redirect:/";
	}
	/*
	public void deleteBook(@PathVariable long id) {
		bookService.deleteBookById(id);
	}*/
	
	/*
	public BookController(BookService bookService) {
		super();
		this.bookService = bookService;
	}*/
	
	/*
	public void createNewBook(@ModelAttribute("book") Book bookObj) {
		bookService.saveBook(bookObj);
	}*/
	
	/*
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String showForm() {
		return "new";
	}*/
	/*
	public List<Book>getAllBooks(){
		
		return bookService.getAllBooks();
	}*/
}
