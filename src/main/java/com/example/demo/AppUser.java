package com.example.demo;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "users")

public class AppUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (unique = true, nullable = false)
    private String username;
    @Column (nullable = false)
    private String password;

    public AppUser() {
    }

    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //Getter und Setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
