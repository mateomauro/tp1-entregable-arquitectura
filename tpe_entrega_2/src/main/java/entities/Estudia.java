package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "estudia")
public class Estudia implements Serializable {

    @EmbeddedId
    private EstudiaPK id;

    @ManyToOne
    private Alumno alumno;

    @ManyToOne
    private Carrera carrera;

    @Column(name = "se_graduo")
    private boolean seGraduo;

    private int antiguedad;

    public Estudia() {}

    public Estudia(long id_alumno, long id_carrera, Alumno alumno, Carrera carrera, boolean seGraduo, int antiguedad) {
        this.id = new EstudiaPK(id_alumno, id_carrera);
        this.alumno = alumno;
        this.carrera = carrera;
        this.seGraduo = seGraduo;
        this.antiguedad = antiguedad;
    }

    public Estudia(EstudiaPK id, Alumno alumno, Carrera carrera, boolean seGraduo, int antiguedad) {
        this.id = id;
        this.alumno = alumno;
        this.carrera = carrera;
        this.seGraduo = seGraduo;
        this.antiguedad = antiguedad;
    }
    public int getId() {
        return id.hashCode();
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
