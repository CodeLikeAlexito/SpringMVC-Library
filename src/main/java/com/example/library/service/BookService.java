package com.example.library.service;

import java.util.List;

import com.example.library.dto.BookResponseDto;
import org.springframework.security.core.Authentication;

import com.example.library.dto.UserRegistrationDto;
import com.example.library.model.Book;
import com.example.library.model.User;

public interface BookService {
	List<Book> getAllBooks();
	void saveBook(Book book, Authentication auth);
	Book getBookById(long id);
	void deleteBookById(long id);
	void updateBook(long id, Book bookObj);
}
