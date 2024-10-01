package repositories;

import entities.Alumno;
import entities.Carrera;
import entities.Estudia;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import utils.JPAUtil;

import javax.persistence.EntityManager;
import java.io.FileReader;
import java.io.IOException;

public class EstudiaRepository {
    private EntityManager em;

    public EstudiaRepository(){
        this.em = JPAUtil.getEntityManager();
    }

    public void cargarCSV() throws IOException {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/resources/estudia.csv"));
        em.getTransaction().begin();
        for(CSVRecord row: parser) {
            long id_alumno = Long.parseLong(row.get("id_alumno"));
            long id_carrera = Long.parseLong(row.get("id_carrera"));
            Alumno alumno = em.find(Alumno.class, id_alumno);
            Carrera carrera = em.find(Carrera.class,id_carrera);
            int f_inicio = Integer.parseInt(row.get("anio_ingreso"));
            int f_fin = Integer.parseInt(row.get("anio_egreso"));
            Estudia estudia = null;
            if(f_fin != 0){
                estudia = new Estudia(alumno,carrera,f_inicio,f_fin);
            }else{
                estudia = new Estudia(alumno,carrera,f_inicio);
            }
            em.persist(estudia);
        }
        em.getTransaction().commit();
    }

    public void matricularAlumnoAcarrera(Estudia estudia){
        em.getTransaction().begin();
        em.persist(estudia);
        em.getTransaction().commit();
    }
}
