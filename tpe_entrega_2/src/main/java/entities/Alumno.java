package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Alumno {
    @Id
    private int id_Alumno;
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
    @Column
    @ManyToMany(mappedBy ="inscriptos")
    private List<Carrera> carreras;

    public Alumno() {

    }

    public int getId_Alumno() {
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

    public List<Carrera> getCarreras() {
        return carreras;
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
