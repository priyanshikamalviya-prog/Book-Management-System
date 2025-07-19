package com.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bookstore.entity.Book;
import com.bookstore.entity.MyBookList;
import com.bookstore.service.BookService;
import com.bookstore.service.MyBookService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class BookController {
	
	@Autowired
	public BookService ser;
	@Autowired
	public MyBookService myser;
	@GetMapping("/")
	public String home() {
		return "index";
	}
	@GetMapping("/book_register")
	public String book_register() {
		return "bookRegister";
	}
	@GetMapping("/available_books")
	public ModelAndView getAllBooks() {
	    List<Book> list = ser.getAllBook();
	    ModelAndView m = new ModelAndView();
	    m.setViewName("bookList");
	    m.addObject("book",list);
	    return m;
	    //return new ModelAndView("bookList","book",list);
	}
	@PostMapping("/save")
	public String addBook(@ModelAttribute Book b) {
		//TODO: process POST request
		ser.save(b);
		return "redirect:/available_books";
	}
	@GetMapping("/mybooks")
	public String getMyBooks(Model model) {
		List<MyBookList> list = myser.getAllMyBooks();
		model.addAttribute("book",list);
		return "mybook";
	}
	@RequestMapping("/mylist/{id}")
	public String getMyList(@PathVariable("id") int id) {
		Book b = ser.getBookById(id);
		MyBookList mb = new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getPrice());
		System.out.println(b.getId());
		System.out.println(b.getName());
		System.out.println(b.getAuthor());
		
		myser.saveMyBooks(mb);
		return "redirect:/mybooks";
	}
	@RequestMapping("/deleteBook/{id}")
	public String deleteMyList(@PathVariable("id") int id) {
		ser.deleteById(id);
		return "redirect:/available_books";
	}
	
	
}
