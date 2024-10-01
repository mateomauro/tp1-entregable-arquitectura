import dtos.AlumnoDTO;
import dtos.CarreraDTO;
import entities.Alumno;
import entities.Carrera;
import entities.Estudia;
import repositories.AlumnoRepository;
import repositories.CarreraRepository;
import repositories.EstudiaRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Executable {

    public static void main(String[] args) throws SQLException, IOException {

        AlumnoRepository alumnoServicio = new AlumnoRepository();
        CarreraRepository carreraServicio = new CarreraRepository();
        EstudiaRepository estudiaServicio = new EstudiaRepository();

        //alumnoServicio.cargarCSV();
        //carreraServicio.cargarCSV();
        //estudiaServicio.cargarCSV();

        //System.out.println("Dar de alta un alumno");
        //alumnoServicio.darAltaAlumno(new Alumno("Martin","Perez",20,"Masculino",423445398,"Tandil",2334435));

        //System.out.println("Matricular un alumno en una carrera");

        //Alumno a1 = alumnoServicio.getAlumnoById(1);
        //Alumno a2 = alumnoServicio.getAlumnoById(2);
        //Alumno a3 = alumnoServicio.getAlumnoById(3);
        //Alumno a4 = alumnoServicio.getAlumnoById(4);
        //Alumno a5 = alumnoServicio.getAlumnoById(5);
        //Alumno a6 = alumnoServicio.getAlumnoById(6);
        //Alumno a7 = alumnoServicio.getAlumnoById(7);
        //Alumno a8 = alumnoServicio.getAlumnoById(8);
        //
        //Carrera c1 = carreraServicio.getCarreraById(1);
        //Carrera c2 = carreraServicio.getCarreraById(2);
        //Carrera c3 = carreraServicio.getCarreraById(3);
        //
        //estudiaServicio.matricularAlumnoAcarrera(new Estudia(a1,c1,2009));
        //estudiaServicio.matricularAlumnoAcarrera(new Estudia(a2,c2,2009, 2017));
        //estudiaServicio.matricularAlumnoAcarrera(new Estudia(a3,c3,2010));
        //estudiaServicio.matricularAlumnoAcarrera(new Estudia(a4,c1,2019));
        //estudiaServicio.matricularAlumnoAcarrera(new Estudia(a5,c2,2005,2011));
        //estudiaServicio.matricularAlumnoAcarrera(new Estudia(a6,c1,2015,2017));
        //estudiaServicio.matricularAlumnoAcarrera(new Estudia(a7,c3,2022));
        //estudiaServicio.matricularAlumnoAcarrera(new Estudia(a1,c3,2014,2019));
        //estudiaServicio.matricularAlumnoAcarrera(new Estudia(a8,c1,1985,2018));

        System.out.println("Recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple");
        List<Alumno> listaDeAlumnosOrdenadosPorEdad = alumnoServicio.getAlumnosByOrder();
        System.out.println(listaDeAlumnosOrdenadosPorEdad);

        //Hay que ver este, por que cuando no existe el alumno con ese numero de legajo, se rompe
        System.out.println("Recuperar un estudiante, en base a su número de libreta universitaria");
        Alumno alumnoBylegajo = alumnoServicio.getAlumnoByLegajo(2334435);
        System.out.println(alumnoBylegajo);

        System.out.println("Recuperar todos los estudiantes, en base a su género.");
        List<Alumno> listaDeAlumnosBygenero = alumnoServicio.getAlumnosByGenero("Masculino");
        System.out.println(listaDeAlumnosBygenero);

        System.out.println("Recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.");
        List<CarreraDTO> carrerasConAlumnosInscriptos = carreraServicio.getCarrerasConAlumnosInscriptos();
        System.out.println(carrerasConAlumnosInscriptos);

        System.out.println("Recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.");
        List<AlumnoDTO> alumnosPorCarrera = alumnoServicio.getAlumnosByCarreraAndCity("TUDAI","Tandil");
        System.out.println(alumnosPorCarrera);

        System.out.println("Generar un reporte de las carreras, que para cada carrera incluya información de los inscriptos y egresados por año. Se deben ordenar las carreras alfabéticamente, y presentar los años de manera cronológica.");
        List<CarreraDTO> reporteCarreras = carreraServicio.getReporteCarreras();
        System.out.println(reporteCarreras);
        
        System.out.println("Ya termino");
    }
}
