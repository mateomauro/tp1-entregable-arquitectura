package org.example.microservice_trip.feignClient.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
