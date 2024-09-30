import dtos.AlumnoDTO;
import dtos.CarreraDTO;
import entities.Alumno;
import entities.Carrera;
import entities.Estudia;
import repositories.AlumnoServicio;
import repositories.CarreraServicio;
import repositories.EstudiaServicio;

import java.sql.SQLException;
import java.util.List;

public class Executable {

    public static void main(String[] args) throws SQLException {

        AlumnoServicio alumnoServicio = new AlumnoServicio();
        CarreraServicio carreraServicio = new CarreraServicio();
        EstudiaServicio estudiaServicio = new EstudiaServicio();

        //a)Ear de alta un alumno
        /* id 1 -> */ alumnoServicio.darAltaAlumno(new Alumno("Martin","Perez",20,"Masculino",423445398,"Tandil",2334435));
        /* id 2 -> */ alumnoServicio.darAltaAlumno(new Alumno("Juan","Rodriguez",21,"Masculino",18225368,"Loberia",13546513));
        /* id 3 -> */ alumnoServicio.darAltaAlumno(new Alumno("Maria","Martinez",29,"Femenino",19874326,"Gardey",216798213));
        /* id 4 -> */ alumnoServicio.darAltaAlumno(new Alumno("Marcelo","Morazo",25,"Masculino",42997354,"Gardey",1597526));
        /* id 5 -> */ alumnoServicio.darAltaAlumno(new Alumno("Enzo","Perez",23,"Masculino",38159762,"La Plata",12547835));
        /* id 6 -> */ alumnoServicio.darAltaAlumno(new Alumno("Delfina","Garcia",31,"Femenino",40157842,"Mar del Plata",49832164));
        /* id 7 -> */ alumnoServicio.darAltaAlumno(new Alumno("Tomas","Donati",53,"Masculino",34556129,"Pehuajo",364985222));
        /* id 8 -> */ alumnoServicio.darAltaAlumno(new Alumno("Martina","Perez",20,"Femenino",423445398,"Tandil",2334436));

        /* id 1 -> */ carreraServicio.darAltaCarrera(new Carrera("TUDAI"));
        /* id 2 -> */ carreraServicio.darAltaCarrera(new Carrera("TUARI"));
        /* id 3 -> */ carreraServicio.darAltaCarrera(new Carrera("SISTEMAS"));

        //b) matricular un alumno en una carrera

        Alumno a1 = alumnoServicio.getAlumnoById(1);
        Alumno a2 = alumnoServicio.getAlumnoById(2);
        Alumno a3 = alumnoServicio.getAlumnoById(3);
        Alumno a4 = alumnoServicio.getAlumnoById(4);
        Alumno a5 = alumnoServicio.getAlumnoById(5);
        Alumno a6 = alumnoServicio.getAlumnoById(6);
        Alumno a7 = alumnoServicio.getAlumnoById(7);
        Alumno a8 = alumnoServicio.getAlumnoById(8);

        Carrera c1 = carreraServicio.getCarreraById(1);
        Carrera c2 = carreraServicio.getCarreraById(2);
        Carrera c3 = carreraServicio.getCarreraById(3);

        estudiaServicio.matricularAlumnoAcarrera(new Estudia(a1,c1,false,2));
        estudiaServicio.matricularAlumnoAcarrera(new Estudia(a2,c2,true,5));
        estudiaServicio.matricularAlumnoAcarrera(new Estudia(a3,c3,false,10));
        estudiaServicio.matricularAlumnoAcarrera(new Estudia(a4,c1,false,1));
        estudiaServicio.matricularAlumnoAcarrera(new Estudia(a5,c2,true,4));
        estudiaServicio.matricularAlumnoAcarrera(new Estudia(a6,c1,true,7));
        estudiaServicio.matricularAlumnoAcarrera(new Estudia(a7,c3,false,0));
        estudiaServicio.matricularAlumnoAcarrera(new Estudia(a1,c3,true,1));
        estudiaServicio.matricularAlumnoAcarrera(new Estudia(a8,c1,true,6));

        //c) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
        List<Alumno> listaDeAlumnosOrdenadosPorEdad = alumnoServicio.getAlumnosByOrder();
        System.out.println(listaDeAlumnosOrdenadosPorEdad);

        //d) recuperar un estudiante, en base a su número de libreta universitaria
        Alumno alumnoBylegajo = alumnoServicio.getAlumnoByLegajo(2334435);
        System.out.println(alumnoBylegajo);

        //e) recuperar todos los estudiantes, en base a su género.
        List<Alumno> listaDeAlumnosBygenero = alumnoServicio.getAlumnosByGenero("Masculino");
        System.out.println(listaDeAlumnosBygenero);

        //f) recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos
        List<CarreraDTO> carrerasConAlumnosInscriptos = carreraServicio.getCarrerasConAlumnosInscriptos();
        System.out.println(carrerasConAlumnosInscriptos);

        //g) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
        List<AlumnoDTO> alumnosPorCarrera = alumnoServicio.getAlumnosByCarreraAndCity("TUDAI","Tandil");
        System.out.println(alumnosPorCarrera);

        /**
         * 3) Generar un reporte de las carreras, que para cada carrera incluya información de los inscriptos y egresados
         *    por año. Se deben ordenar las carreras alfabéticamente, y presentar los años de manera cronológica.
         *    NO ANDA LOREN, es el unico punto que nos falta, y despues detalles.
         */
        //List<CarreraDTO> reporteCarreras = carreraServicio.getReporteCarreras();
        //System.out.println(reporteCarreras);

        
        //System.out.println("Ya termino");
    }
}
