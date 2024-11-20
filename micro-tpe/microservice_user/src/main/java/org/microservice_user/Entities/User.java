package org.microservice_user.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "user")
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;
    private String name;
    private String lastName;
    private String email;
    private String phone_number;
    private String role;
    private Double latitude;
    private Double longitude;

    @ManyToMany
    @JsonIgnore
    private List<Account> accounts;

    public User() {
        this.accounts = new ArrayList<>();
    }
    public User(String name, String lastName, String email, String phone_number, String role, Double latitude, Double longitude) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone_number = phone_number;
        this.role = role;
        this.latitude = latitude;
        this.longitude = longitude;
        this.accounts = new ArrayList<>();
    }

    public User(String name, String lastName, String email, String phone_number, String role) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone_number = phone_number;
        this.role = role;
        this.accounts = new ArrayList<>();
    }
    public User(Long id, String name, String lastName, String email, String phone_number, String role, Double latitude, Double longitude) {
        this.id_user = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone_number = phone_number;
        this.role = role;
        this.latitude = latitude;
        this.longitude = longitude;
        this.accounts = new ArrayList<>();
    }


    public void addAccount(Account account) {
        this.accounts.add(account);
    }
}
