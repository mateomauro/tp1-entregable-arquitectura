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
    private Long id_alumno;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private int edad;

    @Column
    private String genero;

    @Column
    private int dni;

    @Column
    private String ciudad;

    @Column
    private int legajo;

    @OneToMany(mappedBy = "alumno")
    private List<Estudia> carreras;

    public Alumno() {}

    public Alumno(String nombre, String apellido, int edad, String genero, int dni, String ciudad, int legajo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.dni = dni;
        this.ciudad = ciudad;
        this.legajo = legajo;
        this.carreras = new ArrayList<Estudia>();
    }

    public Alumno(Long id_alumno, String nombre, String apellido, int edad, String genero, int dni, String ciudad, int legajo) {
        this.id_alumno = id_alumno;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.dni = dni;
        this.ciudad = ciudad;
        this.legajo = legajo;
    }

    public long getId_Alumno() {
        return id_alumno;
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

    @Override
    public String toString() {
        return "Alumno{" +
                "id_alumno=" + id_alumno +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", genero='" + genero + '\'' +
                ", dni=" + dni +
                ", ciudad='" + ciudad + '\'' +
                ", legajo=" + legajo +
                '}';
    }
}
