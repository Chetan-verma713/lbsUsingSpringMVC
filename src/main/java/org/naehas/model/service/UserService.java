package org.naehas.model.service;

import org.naehas.model.dao.UserDao;
import org.naehas.model.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Transactional
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Transactional
    public void deleteUser(long id) {
        userDao.deleteUser(id);
    }

    @Transactional
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Transactional
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Transactional
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Transactional
    public void borrowBook(long userId, long bookId) {
        userDao.borrowBook(userId, bookId);
    }

    @Transactional
    public void returnBook(long userId, long bookId) {
        userDao.returnBook(userId, bookId);
    }


}
