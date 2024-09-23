package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Carrera{
    @Id
    private int id_Carrera;
    @Column
    private String nombre;
    @ManyToMany
    private List<Alumno> inscriptos;
    public Carrera() {}

}
