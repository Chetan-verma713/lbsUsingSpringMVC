package org.naehas.model.dao;

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

    public void addUser(User user) {
        hibernateTemplate.save(new User(user.getName(), user.getGender(), user.getRole()));
    }

    public void deleteUser(long id) {
        hibernateTemplate.delete(getUser(id));
    }

    public void updateUser(User updatedUser) {
        hibernateTemplate.merge(updatedUser);
    }

    public List<User> getUsers() {
        return hibernateTemplate.loadAll(User.class);
    }

    public User getUser(Long id) {
        return hibernateTemplate.load(User.class, id);
    }

/*
    public List<User> getUsersByName(String name) {
        return getUsers()
                .stream()
                .filter(user -> user.getName().contains(name))
                .collect(Collectors.toList());
    }
*/
}
