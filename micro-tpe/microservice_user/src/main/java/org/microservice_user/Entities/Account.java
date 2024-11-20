package org.microservice_user.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_account;
    private LocalDate dateHigh;
    private Double balance;
    private Boolean annulled;

    @ManyToMany(mappedBy = "accounts")
    @JsonIgnore
    private List<User> users;

    public Account() {
        this.annulled = false;
        this.users = new ArrayList<>();
    }

    public Account(LocalDate dateHigh, Double balance) {
        this.dateHigh = dateHigh;
        this.balance = balance;
        this.annulled = false;
        users = new ArrayList<>();
    }

    public Account(double balance) {
        this.dateHigh = LocalDate.now();
        this.balance = balance;
        this.annulled = false;
        users = new ArrayList<>();
    }

    public void addUser(User user) {
        this.users.add(user);
    }

}
