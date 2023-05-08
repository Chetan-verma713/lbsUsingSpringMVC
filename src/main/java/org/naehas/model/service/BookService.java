package org.naehas.model.service;

import org.naehas.model.dao.BookDao;
import org.naehas.model.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookDao bookDao;

    @Transactional
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Transactional
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Transactional
    public void deleteBook(long id) {
        bookDao.deleteBook(id);
    }

    @Transactional
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Transactional
    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    @Transactional
    public Book getBook(long id) {
        return bookDao.getBook(id);
    }

    @Transactional
    public List<Book> getAllBookByName(String title) {
        return bookDao.getAllBookByName(title);
    }

}
