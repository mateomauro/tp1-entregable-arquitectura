package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.io.Serializable;

@Entity
public class Estudia implements Serializable {
    @Id
    private int id_Alumno;
    @Id
    private int id_Carrera;
    @Column
    private boolean seGraduo;
    @Column
    private int antiguedad;

    public Estudia() {}

    public int getId_Alumno() {
        return id_Alumno;
    }

    public int getId_Carrera() {
        return id_Carrera;
    }

    public boolean isSeGraduo() {
        return seGraduo;
    }

    public int getAntiguedad() {
        return antiguedad;
    }
}
