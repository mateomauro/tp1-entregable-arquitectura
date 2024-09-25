import entities.Alumno;
import entities.Carrera;
import entities.Estudia;
import servicio.AlumnoServicio;
import servicio.CarreraServicio;
import servicio.EstudiaServicio;
import utils.JPAUtil;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

public class Ejecutable {

    public static void main(String[] args) throws SQLException {

        AlumnoServicio alumnoServicio = new AlumnoServicio();
        CarreraServicio carreraServicio = new CarreraServicio();
        EstudiaServicio estudiaServicio = new EstudiaServicio();

        //a) dar de alta un estudiante
        alumnoServicio.darAltaEstudiante(new Alumno("delfi","ipsu",20,"macho",423445398,"tandil",2334435));
        //carreraServicio.darAltaCarrera(new Carrera("tudai"));


        //b) matricular un estudiante en una carrera
        //Alumno a1 = alumnoServicio.getAlumnoXid(1);
        //Carrera c1 = carreraServicio.getCarreraXid(1);
        //estudiaServicio.matricularEstudianteAcarrera(new Estudia(a1,c1,false,2));

        //c) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
        //List<Alumno> listaDeAlumnos = alumnoServicio.getEstudiantesXorden();
        //System.out.println(listaDeAlumnos);


        //d) recuperar un estudiante, en base a su número de libreta universitaria
        //Alumno alumnoXlegajo = alumnoServicio.getEstudianteByLegajo(2334435);
        //System.out.println(alumnoXlegajo);

        //e) recuperar todos los estudiantes, en base a su género.
        //List<Alumno> listaDeAlumnosBygenero = alumnoServicio.getAlumnosBygenero("macho");
        //System.out.println(listaDeAlumnosBygenero);

        //f) recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.



        System.out.println("Ya termino");
    }
}
