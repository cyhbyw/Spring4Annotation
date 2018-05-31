package com.cyh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cyh.dao.BookDao;

@Service
public class BookService {

    @Qualifier("bookDao")
    @Autowired
    private BookDao bookDao;

    @Override
    public String toString() {
        return "BookService{" + "bookDao=" + bookDao + '}';
    }
}
