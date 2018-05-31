package com.cyh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.cyh.service.BookService;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;
}
