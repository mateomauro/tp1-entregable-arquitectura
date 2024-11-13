package org.microservice_user.Dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.microservice_user.Entities.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponseDTO {

    private Long id_account;
    @NotEmpty(message = "el campo dateHigh del user no puede estar vacío")
    @NotNull( message = "La fecha de alta es un campo obligatorio.")
    private LocalDate dateHigh;
    @NotEmpty(message = "el campo name del user no puede estar vacío")
    @NotNull( message = "El name es un campo obligatorio.")
    private Double balance;
    @NotEmpty(message = "el campo annulled del user no puede estar vacío")
    @NotNull( message = "El annulled es un campo obligatorio.")
    private Boolean annulled;

    private List<User> users;

    public AccountResponseDTO(Long id_account, LocalDate dateHigh, Double balance, Boolean annulled) {
        this.id_account = id_account;
        this.dateHigh = dateHigh;
        this.balance = balance;
        this.annulled = annulled;
        this.users = new ArrayList<>();
    }

    public void addUser(User user) {
        this.users.add(user);
    }
}
