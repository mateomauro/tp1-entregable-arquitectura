package org.microservice_user.Dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequestDTO {

    @NotEmpty(message = "el campo dateHigh del user no puede estar vacío")
    @NotNull( message = "La fecha de alta es un campo obligatorio.")
    private LocalDate dateHigh;
    @NotEmpty(message = "el campo name del user no puede estar vacío")
    @NotNull( message = "El name es un campo obligatorio.")
    private Double balance;

}
