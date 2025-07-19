package com.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.entity.MyBookList;
import com.bookstore.repository.MyBookRepository;

@Service
public class MyBookService {
	@Autowired
	public MyBookRepository mybookRepo;
	public void saveMyBooks(MyBookList mybook) {
		mybookRepo.save(mybook);
	}
	public List<MyBookList> getAllMyBooks(){
		return mybookRepo.findAll();
	}
	public void deleteById(int id) {
		mybookRepo.deleteById(id);
	}
	
}
