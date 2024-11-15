package org.microservice_user.Dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
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
}
