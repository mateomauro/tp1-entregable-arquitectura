package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carrera")
public class Carrera implements Serializable {
    @Id
    @Column(name = "id_carrera")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_carrera;

    @Column
    private String nombre;

    @OneToMany(mappedBy = "carrera")
    private List<Estudia> alumnos;

    public Carrera() {}

    public Carrera(String nombre) {
        this.nombre = nombre;
        this.alumnos = new ArrayList<Estudia>();
    }

    public long getId_Carrera() {
        return id_carrera;
    }

    public void setId_Carrera(long id_Carrera) {
        this.id_carrera = id_Carrera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Estudia> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Estudia> alumnos) {
        this.alumnos = alumnos;
    }

    @Override
    public String toString() {
        return "Carrera{" +
                "id_carrera=" + id_carrera +
                ", nombre='" + nombre + '\'' +
                ", alumnos=" + alumnos +
                '}';
    }
}
