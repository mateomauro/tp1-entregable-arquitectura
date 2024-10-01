package repositories;

import dtos.CarreraDTO;
import entities.Alumno;
import entities.Carrera;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import utils.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CarreraRepository {
    private EntityManager em;

    public CarreraRepository(){
        this.em = JPAUtil.getEntityManager();
    }

    public void cargarCSV() throws IOException {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/resources/carreras.csv"));
        em.getTransaction().begin();
        for(CSVRecord row: parser) {
            Carrera carrera = new Carrera(row.get("nombre"));
            em.persist(carrera);
        }
        em.getTransaction().commit();
    }

    public void darAltaCarrera(Carrera carrera){
        em.getTransaction().begin();
        em.persist(carrera);
        em.getTransaction().commit();
    }

    public Carrera getCarreraById(long id){
        return em.find(Carrera.class, id);
    }

    public List<CarreraDTO> getCarrerasConAlumnosInscriptos(){
        em.getTransaction().begin();
        String jpql = "SELECT new dtos.CarreraDTO(c.id, c.nombre, COUNT(e.alumno.id)) FROM Carrera c JOIN c.alumnos e GROUP BY c.id, c.nombre ORDER BY COUNT(e.alumno.id) DESC";
        TypedQuery<CarreraDTO> TypedQuery = em.createQuery(jpql, CarreraDTO.class);
        List<CarreraDTO> carreras = TypedQuery.getResultList();
        em.getTransaction().commit();
        return carreras;
    }


     //   3) Generar un reporte de las carreras, que para cada carrera incluya información de los inscriptos y egresados
     //      por año. Se deben ordenar las carreras alfabéticamente, y presentar los años de manera cronológica.

//    public List<CarreraDTO> getReporteCarreras(){
//        em.getTransaction().begin();
//        Query TypedQuery = em.createNativeQuery("SELECT c.nombre AS carrera, e.anio_ingreso AS anio, COUNT(DISTINCT e.id_alumno) AS cantidad_inscriptos, (SELECT COUNT(DISTINCT e2.id_alumno) FROM estudia e2 WHERE e2.id_carrera = c.id_carrera AND e2.anio_graduacion > 0 AND e2.anio_ingreso = e.anio_ingreso) AS cantidad_recibidos FROM carrera c  INNER JOIN estudia e ON c.id_carrera = e.id_carrera GROUP BY c.nombre, e.anio_ingreso, cantidad_recibidos ORDER BY c.nombre, e.anio_ingreso");
//        List<dtos.CarreraDTO> carreras = TypedQuery.getResultList();
//        em.getTransaction().commit();
//        return carreras;
//    }

    public List<CarreraDTO> getReporteCarreras() {
        em.getTransaction().begin();

        Query query = em.createNativeQuery(
                "SELECT\n" +
                        "    carrera,\n" +
                        "    SUM(cant_inscriptos) AS cant_inscriptos,\n" +
                        "    SUM(cant_graduados) AS cant_graduados,\n" +
                        "    anio\n" +
                        "FROM (\n" +
                        "         SELECT\n" +
                        "             c.nombre AS carrera,\n" +
                        "             COUNT(DISTINCT e.id_alumno) AS cant_inscriptos,\n" +
                        "             0 AS cant_graduados,\n" +
                        "             e.anio_ingreso AS anio\n" +
                        "         FROM\n" +
                        "             estudia e\n" +
                        "                 JOIN\n" +
                        "             carrera c ON e.id_carrera = c.id_carrera\n" +
                        "         GROUP BY\n" +
                        "             c.nombre, e.anio_ingreso\n" +
                        "\n" +
                        "         UNION ALL\n" +
                        "\n" +
                        "         SELECT\n" +
                        "             c.nombre AS carrera,\n" +
                        "             0 AS cant_inscriptos,\n" +
                        "             COUNT(DISTINCT e.id_alumno) AS cant_graduados,\n" +
                        "             e.anio_graduacion AS anio\n" +
                        "         FROM\n" +
                        "             estudia e\n" +
                        "                 JOIN\n" +
                        "             carrera c ON e.id_carrera = c.id_carrera\n" +
                        "         WHERE\n" +
                        "             e.anio_graduacion > 0\n" +
                        "         GROUP BY\n" +
                        "             c.nombre, e.anio_graduacion\n" +
                        "     ) AS subQuery\n" +
                        "WHERE\n" +
                        "    anio IS NOT NULL\n" +
                        "GROUP BY\n" +
                        "    carrera, anio\n" +
                        "ORDER BY\n" +
                        "    carrera ASC, anio ASC;\n"
        );

        List<Object[]> results = query.getResultList();
        List<CarreraDTO> carreras = new ArrayList<>();

        for (Object[] result : results) {
            String nombreCarrera = (String) result[0];  // Índice 0: carrera
            long cantidadInscriptos = ((Number) result[1]).longValue();  // Índice 1: cant_inscriptos
            long cantidadEgresados = ((Number) result[2]).longValue();  // Índice 2: cant_graduados
            int anio = ((Number) result[3]).intValue();  // Índice 3: anio

            CarreraDTO carreraDTO = new CarreraDTO(nombreCarrera, cantidadInscriptos, cantidadEgresados, anio);

            carreras.add(carreraDTO);
        }

        em.getTransaction().commit();
        return carreras;
    }


    //c.alumnos

    // TUDAI | 2010 | 10 inscriptos actualmente | egresados : (juan, pepito, carlitos, maria) |

}
