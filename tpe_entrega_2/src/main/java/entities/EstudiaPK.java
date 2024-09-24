package entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EstudiaPK implements Serializable {

    @Column(name = "id_alumno_pk")
    private long id_alumno;

    @Column(name = "id_carrera_pk")
    private long id_carrera;

    public EstudiaPK() {}

    public EstudiaPK(long id_alumno, long id_carrera) {
        this.id_alumno = id_alumno;
        this.id_carrera = id_carrera;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstudiaPK estudiaPK = (EstudiaPK) o;
        return Objects.equals(id_alumno, estudiaPK.id_alumno) && Objects.equals(id_carrera, estudiaPK.id_carrera);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_alumno, id_carrera);
    }

    public long getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(Long id_alumno) {
        this.id_alumno = id_alumno;
    }

    public long getId_carrera() {
        return id_carrera;
    }

    public void setId_carrera(Long id_carrera) {
        this.id_carrera = id_carrera;
    }
}
