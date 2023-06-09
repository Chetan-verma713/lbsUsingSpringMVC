package org.naehas.model.dao;

import org.jetbrains.annotations.NotNull;
import org.naehas.model.model.Book;
import org.naehas.model.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public void addUser(@NotNull User user) {
        hibernateTemplate.save(new User(user.getName(), user.getGender(), user.getRole()));
    }

    public void deleteUser(long id) {
        hibernateTemplate.delete(getUserById(id));
    }

    public void updateUser(User updatedUser) {
        hibernateTemplate.merge(updatedUser);
    }

    public List<User> getUsers() {
        return hibernateTemplate.loadAll(User.class);
    }

    public User getUserById(long id) {
        return hibernateTemplate.get(User.class, id);
    }

    public void borrowBook(long userId, long bookId) {
        User user = getUserById(userId);
        System.out.println(user);
        BookDao bookDao = new BookDao();
        Book book = bookDao.getBookById(bookId);
        System.out.println(book);
        hibernateTemplate.save(user.getBooks().add(book));
    }

}
