package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "alumno")
public class Alumno implements Serializable {

    @Id
    @Column(name = "id_alumno")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Alumno;

    private String nombre;

    private String apellido;

    private int edad;

    private String genero;

    private int dni;

    private String ciudad;

    private int legajo;

    @OneToMany(mappedBy = "alumno")
    private List<Estudia> carreras;

    public Alumno() {}

    public Alumno(Long id_Alumno, String nombre, String apellido, int edad, String genero, int dni, String ciudad, int legajo) {
        this.id_Alumno = id_Alumno;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.dni = dni;
        this.ciudad = ciudad;
        this.legajo = legajo;
        this.carreras = new ArrayList<Estudia>();
    }

    public Long getId_Alumno() {
        return id_Alumno;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }

    public String getGenero() {
        return genero;
    }

    public List<Estudia> getCarreras() {
        return new ArrayList<>(carreras);
    }

    public int getLegajo() {
        return legajo;
    }

    public int getDni() {
        return dni;
    }

    public String getCiudad() {
        return ciudad;
    }
}
