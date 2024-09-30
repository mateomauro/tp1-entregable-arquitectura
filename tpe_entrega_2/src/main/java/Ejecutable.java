import dtos.AlumnoDTO;
import dtos.CarreraDTO;
import entities.Alumno;
import entities.Carrera;
import entities.Estudia;
import servicios.AlumnoServicio;
import servicios.CarreraServicio;
import servicios.EstudiaServicio;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Ejecutable {

    public static void main(String[] args) throws SQLException {

        AlumnoServicio alumnoServicio = new AlumnoServicio();
        CarreraServicio carreraServicio = new CarreraServicio();
        EstudiaServicio estudiaServicio = new EstudiaServicio();

        //2a) dar de alta un estudiante
        //alumnoServicio.darAltaEstudiante(new Alumno("Martin","Perez",20,"Masculino",423445398,"Tandil",2334435));
        //alumnoServicio.darAltaEstudiante(new Alumno("Juan","Rodriguez",21,"Masculino",18225368,"Loberia",13546513));
        //alumnoServicio.darAltaEstudiante(new Alumno("Maria","Martinez",29,"Femenino",19874326,"Gardey",216798213));
        //alumnoServicio.darAltaEstudiante(new Alumno("Marcelo","Morazo",25,"Masculino",42997354,"Gardey",1597526));
        //alumnoServicio.darAltaEstudiante(new Alumno("Enzo","Perez",23,"Masculino",38159762,"La Plata",12547835));
        //alumnoServicio.darAltaEstudiante(new Alumno("Delfina","Garcia",31,"Femenino",40157842,"Mar del Plata",49832164));
        //alumnoServicio.darAltaEstudiante(new Alumno("Tomas","Donati",53,"Masculino",34556129,"Pehuajo",364985222));
        //alumnoServicio.darAltaEstudiante(new Alumno("Martina","Perez",20,"Femenino",423445398,"Tandil",2334435));
        //
        //carreraServicio.darAltaCarrera(new Carrera("TUDAI"));
        //carreraServicio.darAltaCarrera(new Carrera("TUARI"));
        //carreraServicio.darAltaCarrera(new Carrera("SISTEMAS"));


        //b) matricular un estudiante en una carrera

        Alumno a1 = alumnoServicio.getAlumnoXid(1);
        Alumno a2 = alumnoServicio.getAlumnoXid(2);
        Alumno a3 = alumnoServicio.getAlumnoXid(3);
        Alumno a4 = alumnoServicio.getAlumnoXid(4);
        Alumno a5 = alumnoServicio.getAlumnoXid(5);
        Alumno a6 = alumnoServicio.getAlumnoXid(6);
        Alumno a7 = alumnoServicio.getAlumnoXid(7);
        Alumno a8 = alumnoServicio.getAlumnoXid(8);

        Carrera c1 = carreraServicio.getCarreraXid(1);
        Carrera c2 = carreraServicio.getCarreraXid(2);
        Carrera c3 = carreraServicio.getCarreraXid(3);

        estudiaServicio.matricularEstudianteAcarrera(new Estudia(a1,c1,false));
        estudiaServicio.matricularEstudianteAcarrera(new Estudia(a2,c2,true, LocalDate.of(2010,05,10)));
        estudiaServicio.matricularEstudianteAcarrera(new Estudia(a3,c3,false));
        estudiaServicio.matricularEstudianteAcarrera(new Estudia(a4,c1,false));
        estudiaServicio.matricularEstudianteAcarrera(new Estudia(a5,c2,true, LocalDate.of(2017,04,14)));
        estudiaServicio.matricularEstudianteAcarrera(new Estudia(a6,c1,true, LocalDate.of(2009,10,27)));
        estudiaServicio.matricularEstudianteAcarrera(new Estudia(a7,c3,false));
        estudiaServicio.matricularEstudianteAcarrera(new Estudia(a1,c3,false));
        estudiaServicio.matricularEstudianteAcarrera(new Estudia(a8,c1,false));

        //c) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
        //List<Alumno> listaDeAlumnos = alumnoServicio.getEstudiantesXorden();
        //System.out.println(listaDeAlumnos);


        //d) recuperar un estudiante, en base a su número de libreta universitaria
        //Alumno alumnoXlegajo = alumnoServicio.getEstudianteByLegajo(2334435);
        //System.out.println(alumnoXlegajo);

        //e) recuperar todos los estudiantes, en base a su género.
        //List<Alumno> listaDeAlumnosBygenero = alumnoServicio.getAlumnosBygenero("macho");
        //System.out.println(listaDeAlumnosBygenero);

        //f) recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos
        //List<CarreraDTO> res = carreraServicio.getCarrerasConCantidad();
        //System.out.println(res);

        //g) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
        //List<AlumnoDTO> res = alumnoServicio.getEstudianteByCarreraAndResidencia("TUDAI","Tandil");
        //System.out.println(res);


        //3) Generar un reporte de las carreras, que para cada carrera incluya información de los inscriptos y egresados
        //   por año. Se deben ordenar las carreras alfabéticamente, y presentar los años de manera cronológica.
        //   NO ANDA LOREN, es el unico punto que nos falta, y despues detalles.

        //List<CarreraDTO> res = carreraServicio.getReporteCarreras();
        //System.out.println(res);

        
        System.out.println("Ya termino");
    }
}
