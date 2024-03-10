package com.practice.library.service.interfaces;

import com.practice.library.dto.BookRequestDTO;
import com.practice.library.model.Book;

import java.util.List;

public interface BookService {
    Book addBook(BookRequestDTO bookRequestDTO);
    List<Book> getBookListByName(String bookName);
}
