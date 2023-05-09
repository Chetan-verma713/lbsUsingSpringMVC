package org.naehas.model.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"name", "gender", "role"}
        )
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String name;
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Temporal(TemporalType.DATE)
    @Column(name = "entry_date")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    final private Date date = new Date();

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Book> books;

    public User() {
    }

    public User(String name, Gender gender, Role role) {
        setName(name.toUpperCase());
        setGender(Gender.valueOf(gender.toString().toUpperCase()));
        setRole(Role.valueOf(role.toString().toUpperCase()));
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Date getDate() {
        return date;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "{" +
                "id=''" + id + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", role=" + role +
                ", date=" + date +
                '}';
    }
}


enum Role {
    STUDENT,
    STAFF,
    LIBRARIAN
}

enum Gender {
    MALE,
    FEMALE,
    OTHER
}
