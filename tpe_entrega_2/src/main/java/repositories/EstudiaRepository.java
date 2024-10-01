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

    public void matricularAlumnoAcarrera(Estudia estudia){
        em.getTransaction().begin();
        em.persist(estudia);
        em.getTransaction().commit();
    }
}
