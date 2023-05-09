package org.naehas.model.dao;

import org.naehas.model.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDao {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
    public void addBook(Book book) {
        if (book.getDescription().length() > 250) {
            book.setDescription(book.getDescription().substring(0, 245) + "...");
        }
        hibernateTemplate.save(book);
    }

    public void deleteBook(long id) {
        hibernateTemplate.delete(getBookById(id));
    }

    public void updateBook(Book updatedBook) {
        Book originalBook = getBookById(updatedBook.getId());

        if (updatedBook.getTitle() == null) {
            updatedBook.setTitle(originalBook.getTitle());
        }
        if (updatedBook.getSubtitle() == null) {
            updatedBook.setSubtitle(originalBook.getSubtitle());
        }
        if (updatedBook.getAuthor() == null) {
            updatedBook.setAuthor(originalBook.getAuthor());
        }
        if (updatedBook.getDescription() == null) {
            updatedBook.setDescription(originalBook.getDescription());
        }
        if (updatedBook.getIsbn() == null) {
            updatedBook.setIsbn(originalBook.getIsbn());
        }
        if (updatedBook.getPublisher() == null) {
            updatedBook.setPublisher(originalBook.getPublisher());
        }
        if (updatedBook.getPages() == 0) {
            updatedBook.setPages(originalBook.getPages());
        }
        if (updatedBook.getPublished() == null) {
            updatedBook.setPublished(originalBook.getPublished());
        }
        hibernateTemplate.merge(updatedBook);
    }

    public List<Book> getBooks() {
        return hibernateTemplate.loadAll(Book.class);
    }

    public Book getBookById(long id) {
        return hibernateTemplate.get(Book.class, id);
    }

}