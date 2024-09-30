package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

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

    @Column(name="graduacion", nullable = true)
    private LocalDate anio_graduacion;

    // Constructor vacío
    public Estudia() {}

    // Constructor con parámetros
    public Estudia(Alumno alumno, Carrera carrera, boolean seGraduo) {
        this.alumno = alumno;
        this.carrera = carrera;
        this.seGraduo = seGraduo;
    }

    public Estudia(Alumno alumno, Carrera carrera, boolean seGraduo, LocalDate anio) {
        this.alumno = alumno;
        this.carrera = carrera;
        this.seGraduo = seGraduo;
        this.anio_graduacion = anio;
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

    public LocalDate getAntiguedad() {
        return anio_graduacion;
    }

    public void SetGraduacion(LocalDate anio_graduacion) {
        this.anio_graduacion = anio_graduacion;
    }
}
