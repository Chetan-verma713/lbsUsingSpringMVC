package org.naehas.model.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
    @NotNull
    @Check(constraints = "email like %@%.%")
    private String email;
    @NotNull
//    @JsonIgnore
    private String password;
    @Temporal(TemporalType.DATE)
    @Column(name = "entry_date")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    final private Date entryDate = new Date();
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Book> books;

    public User() {
    }

    public User(String name, Gender gender, Role role, String email, String password) {
        setName(name.toUpperCase());
        setGender(Gender.valueOf(gender.toString().toUpperCase()));
        setRole(Role.valueOf(role.toString().toUpperCase()));
        setEmail(email);
        setPassword(password);
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

    public List<Book> getBooks() {
        List<Book> newBooks = new ArrayList<>();
        for (Book book: books) {
            newBooks.add(new Book(book.getTitle(), book.getSubtitle(), book.getAuthor(), book.getPublisher(), book.getDescription(), book.getIsbn(), book.getPages(), book.getPublished()));
        }
        return newBooks;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (validEmail(email)) {
            this.email = email;
        } else {
            throw new RuntimeException("Invalid email");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", role=" + role +
                ", email='" + email + '\'' +
                ", entryDate=" + entryDate +
                ", books=" + books +
                '}';
    }

    private boolean validEmail(String email) {
        return (email.contains(".com") || email.contains(".in")) && email.contains("@");
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
