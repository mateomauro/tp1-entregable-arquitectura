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

    @Column(name = "se_graduo")
    private boolean seGraduo;

    @Column
    private int antiguedad;

    // Constructor vacío
    public Estudia() {}

    // Constructor con parámetros
    public Estudia(Alumno alumno, Carrera carrera, boolean seGraduo, int antiguedad) {
        this.alumno = alumno;
        this.carrera = carrera;
        this.seGraduo = seGraduo;
        this.antiguedad = antiguedad;
    }

    // Getters y setters
    public Alumno getAlumno() {
        return alumno;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public boolean isSeGraduo() {
        return seGraduo;
    }

    public int getAntiguedad() {
        return antiguedad;
    }
}
