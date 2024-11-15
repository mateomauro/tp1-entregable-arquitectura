package org.microservice_user.Dtos;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.microservice_user.Entities.Account;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private Long id_user;
    @NotEmpty(message = "el campo name del user no puede estar vacío")
    @NotNull( message = "El name es un campo obligatorio.")
    private String name;
    @NotEmpty(message = "el campo lastName del user no puede estar vacío")
    @NotNull( message = "El lastName es un campo obligatorio.")
    private String lastName;
    @NotEmpty(message = "el campo email del user no puede estar vacío")
    @NotNull( message = "El email es un campo obligatorio.")
    private String email;
    @NotEmpty(message = "el campo phone_number del user no puede estar vacío")
    @NotNull( message = "El phone_number es un campo obligatorio.")
    private String phone_number;
    @NotEmpty(message = "el campo role del user no puede estar vacío")
    @NotNull( message = "El role es un campo obligatorio.")
    private String role;
    @NotEmpty(message = "el campo latitude del user no puede estar vacío")
    @NotNull( message = "La latitude es un campo obligatorio.")
    private Double latitude;
    @NotEmpty(message = "el campo longitude del user no puede estar vacío")
    @NotNull( message = "La longitude es un campo obligatorio.")
    private Double longitude;
    private List<Account> accounts;


    // RECIBE TODO MENOS EL ID_USER Y LA LISTA DE CUENTAS
    public UserResponseDTO(String name, String lastName, String email, String phone_number, String role, Double latitude, Double longitude) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone_number = phone_number;
        this.role = role;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    // RECIBE TODO MENOS LA LISTA DE CUENTAS
    public UserResponseDTO(Long id_user, String name, String lastName, String email, String phone_number, String role, Double latitude, Double longitude) {
        this.id_user = id_user;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone_number = phone_number;
        this.role = role;
        this.latitude = latitude;
        this.longitude = longitude;
        // Cuidao con esto, ya que me lo inicializa vacío, tendría que tener un findAll() de cuentas del User.
        this.accounts = new ArrayList<>();
    }
    // RECIBE TODOS PERO UNA SOLA CUENTADouble
    public UserResponseDTO(Long id_user, String name, String lastName, String email, String phone_number, String role, Double latitude, Double longitude, Account account) {
        this.id_user = id_user;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone_number = phone_number;
        this.role = role;
        this.latitude = latitude;
        this.longitude = longitude;
        // Cuidao con esto, ya que me lo inicializa vacío, tendría que tener un findAll() de cuentas del User.
        this.accounts = new ArrayList<>();
        this.accounts.add(account);
    }
}
