package com.practice.library.controoller;

import com.practice.library.model.Book;
import com.practice.library.dto.BookRequestDTO;
import com.practice.library.service.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
   private BookService bookService;

    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody BookRequestDTO bookRequestDTO) {
        return ResponseEntity.ok().body(bookService.addBook(bookRequestDTO));
    }
    @GetMapping("/books/{name}")
    public ResponseEntity <List<Book >> getBooksByName(@PathVariable String name) {
        return ResponseEntity.ok().body(bookService.getBookListByName(name));
    }
}
