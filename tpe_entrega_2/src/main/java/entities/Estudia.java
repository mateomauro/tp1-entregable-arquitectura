package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "estudia")
public class Estudia implements Serializable {
    @Id
    @ManyToOne
    private Alumno alumno;
    @Id
    @ManyToOne
    private Carrera carrera;
    @Column(name = "se_graduo")
    private boolean seGraduo;

    private int antiguedad;

    public Estudia() {}

    public Estudia(Alumno alumno, Carrera carrera, boolean seGraduo, int antiguedad) {
        this.alumno = alumno;
        this.carrera = carrera;
        this.seGraduo = seGraduo;
        this.antiguedad = antiguedad;
    }

    public Alumno get_alumno() {
        return this.alumno;
    }

    public Carrera get_carrera() {
        return carrera;
    }

    public boolean isSeGraduo() {
        return seGraduo;
    }

    public int getAntiguedad() {
        return antiguedad;
    }
}
