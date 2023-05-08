package org.naehas.model.controller;

import org.naehas.model.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.naehas.model.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        try {
            bookService.addBook(book);
            return new ResponseEntity<>("book inserted!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("book not inserted!", HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteBook(@PathVariable long id) {
        try {
            bookService.deleteBook(id);
            return new ResponseEntity<>("book deleted!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("book not deleted!", HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping
    @ResponseBody
    public ResponseEntity<String> updateBook(@RequestBody Book book) {
        try {
            System.out.println(book);
            bookService.updateBook(book);
            return new ResponseEntity<>("book updated!", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("book not updated!", HttpStatus.BAD_REQUEST);
        }
    }

    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    public Book getBook(long id) {
        return bookService.getBook(id);
    }

    public List<Book> getAllBookByName(String title) {
        return bookService.getAllBookByName(title);
    }


}
