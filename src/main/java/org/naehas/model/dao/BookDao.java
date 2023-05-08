package org.naehas.model.dao;

import org.naehas.model.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        hibernateTemplate.delete(getBook(id));
    }

    public void updateBook(Book updatedBook) {
        Book originalBook = getBook(updatedBook.getId());

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
        if (updatedBook.getWebsite() == null) {
            updatedBook.setWebsite(originalBook.getWebsite());
        }
        if (updatedBook.getPages() == 0) {
            updatedBook.setPages(originalBook.getPages());
        }
        if (updatedBook.getPublished() == null) {
            updatedBook.setPublished(originalBook.getPublished());
        }
        System.out.println(updatedBook);
        hibernateTemplate.merge(updatedBook);
    }

    public List<Book> getAllBooks() {
        return hibernateTemplate.loadAll(Book.class);
    }

    public Book getBook(long id) {
        return hibernateTemplate.load(Book.class, id);
    }

    public List<Book> getAllBookByName(String title) {
        return getAllBooks()
                .stream()
                .filter(book -> Objects.equals(book.getTitle(), title))
                .collect(Collectors.toList());
    }

}