package org.naehas.model.controller;

import org.naehas.model.model.Book;
import org.naehas.utils.Parser;
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

    @PostMapping("/bulk")
    @ResponseBody
    public ResponseEntity<String> addBooks(@RequestBody List<Book> books) {
        try {
            bookService.addBooks(books);
            return new ResponseEntity<>("book inserted!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("book not inserted!", HttpStatus.BAD_REQUEST);
        }
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

    @GetMapping
    @ResponseBody
    public ResponseEntity<String> getBooks() {
        try {

            return new ResponseEntity<>(new Parser<>().toJson(bookService.getBooks()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("something went wrong", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> getBookById(@PathVariable long id) {
        try {
            Book book = bookService.getBookById(id);
            return new ResponseEntity<>(new Parser<>().toJson(book), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("something went wrong", HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}/update-stock")
    @ResponseBody
    public ResponseEntity<String> updateStock(@PathVariable long id, @RequestParam int count) {
        try {
            bookService.updateStock(id, count);
            return new ResponseEntity<>("stock updated", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("stock not updated", HttpStatus.NOT_FOUND);
        }
    }


}
