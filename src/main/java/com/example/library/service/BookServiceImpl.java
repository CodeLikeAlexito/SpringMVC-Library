package com.example.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.library.dto.UserRegistrationDto;
import com.example.library.model.Book;
import com.example.library.model.User;
import com.example.library.repository.BookRepository;
import com.example.library.repository.UserRepository;

@Service
public class BookServiceImpl implements BookService{
	
	
	private BookRepository bookRepository;
	private UserRepository userRepository;

	@Autowired
	public BookServiceImpl(BookRepository bookRepository, UserRepository userRepository)
	{
		this.bookRepository = bookRepository;
		this.userRepository = userRepository;
	}
	
	@Override
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public void saveBook(Book book, Authentication auth) {
		String getEmail = auth.getName();
		User user = this.userRepository.findByEmail(getEmail);
		book.setUser(user);
		this.bookRepository.save(book);
	}

	@Override
	public Book getBookById(long id) {
		Optional<Book> optional = bookRepository.findById(id);
		Book book = null;
		if (optional.isPresent()) {
			book = optional.get();
		} else {
			throw new RuntimeException(" Book not found for id :: " + id);
		}
		return book;
	}

	@Override
	public void deleteBookById(long id) {
		this.bookRepository.deleteById(id);
	}

	@Override
	public void updateBook(long id, Book bookObj) {
		// TODO Auto-generated method stub
	}

}
