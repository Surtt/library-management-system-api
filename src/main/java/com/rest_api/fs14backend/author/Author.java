package com.rest_api.fs14backend.author;

import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "authors")
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(nullable = false, columnDefinition = "varchar(50)", name="authorname")
    private String authorname;
    @Column(nullable = false, columnDefinition = "varchar(50)", name="lastname")
    private String lastname;
    private Date dateofbirth;

    public Author(){};

    public Author(String authorname, String lastname, Date dateofbirth) {
        this.authorname = authorname;
        this.lastname = lastname;
        this.dateofbirth = dateofbirth;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }
}
