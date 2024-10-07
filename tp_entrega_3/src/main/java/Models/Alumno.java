package Models;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Setter
@Getter
public class Alumno {
    @Id
    private long id_alumno;
    private String nombre;
    private String apellido;
    private String ciudad;
    private long dni;
    private int edad;
    private String genero;
    private long legajo;


    public Alumno(String nombre, String apellido, String ciudad, long dni, int edad, String genero, long legajo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.ciudad = ciudad;
        this.dni = dni;
        this.edad = edad;
        this.genero = genero;
        this.legajo = legajo;
    }

    public Alumno() {

    }

    @Override
    public String toString() {
        return "Alumno{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", dni=" + dni +
                ", edad=" + edad +
                ", genero='" + genero + '\'' +
                ", legajo=" + legajo +
                '}';
    }
}
