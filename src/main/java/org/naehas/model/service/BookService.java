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
    public void addBooks(List<Book> books) {
        bookDao.addBooks(books);
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
    public List<Book> getBooks() {
        return bookDao.getBooks();
    }

    @Transactional
    public Book getBookById(long id) {
        return bookDao.getBookById(id);
    }

    @Transactional
    public void updateStock(long id, int count) {
        bookDao.updateStock(id, count);
    }

}
