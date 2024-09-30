package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "estudia")
public class Estudia implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_alumno", referencedColumnName = "id_alumno")
    private Alumno alumno;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_carrera", referencedColumnName = "id_carrera")
    private Carrera carrera;

    @Column(name = "anio_ingreso")
    private int anio_ingreso;

    @Column(name="anio_graduacion", nullable = true,columnDefinition = "int default '0'")
    private int anio_graduacion;

    // Constructor vacío
    public Estudia() {}

    // Constructor con parámetros
    public Estudia(Alumno alumno, Carrera carrera, int inicio) {
        this.alumno = alumno;
        this.carrera = carrera;
        this.anio_ingreso = inicio;
    }

    public Estudia(Alumno alumno, Carrera carrera, int inicio, int anio) {
        this.alumno = alumno;
        this.carrera = carrera;
        this.anio_ingreso = anio;
        this.anio_graduacion = anio;
    }

    // Getters y setters
    public Alumno getAlumno() {
        return alumno;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public int getAnio_ingreso() {
        return this.anio_ingreso;
    }

    public int getAnio_graduacion() {
        return anio_graduacion;
    }

    public void SetGraduacion(int anio_graduacion) {
        this.anio_graduacion = anio_graduacion;
    }
}
