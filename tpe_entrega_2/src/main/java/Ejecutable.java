import dtos.CarreraDTO;
import servicios.AlumnoServicio;
import servicios.CarreraServicio;
import servicios.EstudiaServicio;

import java.sql.SQLException;
import java.util.List;

public class Ejecutable {

    public static void main(String[] args) throws SQLException {

        AlumnoServicio alumnoServicio = new AlumnoServicio();
        CarreraServicio carreraServicio = new CarreraServicio();
        EstudiaServicio estudiaServicio = new EstudiaServicio();

        //a) dar de alta un estudiante
        // id 1 -> alumnoServicio.darAltaEstudiante(new Alumno("Martin","Perez",20,"Masculino",423445398,"Tandil",2334435));
        // id 2 -> alumnoServicio.darAltaEstudiante(new Alumno("Juan","Rodriguez",21,"Masculino",18225368,"Loberia",13546513));
        // id 3 -> alumnoServicio.darAltaEstudiante(new Alumno("Maria","Martinez",29,"Femenino",19874326,"Gardey",216798213));
        // id 4 -> alumnoServicio.darAltaEstudiante(new Alumno("Marcelo","Morazo",25,"Masculino",42997354,"Gardey",1597526));
        // id 5 -> alumnoServicio.darAltaEstudiante(new Alumno("Enzo","Perez",23,"Masculino",38159762,"La Plata",12547835));
        // id 6 -> alumnoServicio.darAltaEstudiante(new Alumno("Delfina","Garcia",31,"Femenino",40157842,"Mar del Plata",49832164));
        // id 7 -> alumnoServicio.darAltaEstudiante(new Alumno("Tomas","Donati",53,"Masculino",34556129,"Pehuajo",364985222));
        // id 8 -> alumnoServicio.darAltaEstudiante(new Alumno("Martina","Perez",20,"Femenino",423445398,"Tandil",2334435));

        // id 1 -> carreraServicio.darAltaCarrera(new Carrera("TUDAI"));
        // id 2 -> carreraServicio.darAltaCarrera(new Carrera("TUARI"));
        // id 3 -> carreraServicio.darAltaCarrera(new Carrera("SISTEMAS"));


        //b) matricular un estudiante en una carrera

        //Alumno a1 = alumnoServicio.getAlumnoXid(1);
        //Alumno a2 = alumnoServicio.getAlumnoXid(2);
        //Alumno a3 = alumnoServicio.getAlumnoXid(3);
        //Alumno a4 = alumnoServicio.getAlumnoXid(4);
        //Alumno a5 = alumnoServicio.getAlumnoXid(5);
        //Alumno a6 = alumnoServicio.getAlumnoXid(6);
        //Alumno a7 = alumnoServicio.getAlumnoXid(7);
        //Alumno a8 = alumnoServicio.getAlumnoXid(8);

        //Carrera c1 = carreraServicio.getCarreraXid(1);
        //Carrera c2 = carreraServicio.getCarreraXid(2);
        //Carrera c3 = carreraServicio.getCarreraXid(3);

        //estudiaServicio.matricularEstudianteAcarrera(new Estudia(a1,c1,false,2));
        //estudiaServicio.matricularEstudianteAcarrera(new Estudia(a2,c2,true,5));
        //estudiaServicio.matricularEstudianteAcarrera(new Estudia(a3,c3,false,10));
        //estudiaServicio.matricularEstudianteAcarrera(new Estudia(a4,c1,false,1));
        //estudiaServicio.matricularEstudianteAcarrera(new Estudia(a5,c2,true,4));
        //estudiaServicio.matricularEstudianteAcarrera(new Estudia(a6,c1,true,7));
        //estudiaServicio.matricularEstudianteAcarrera(new Estudia(a7,c3,false,0));
        //estudiaServicio.matricularEstudianteAcarrera(new Estudia(a1,c3,true,1));
        //estudiaServicio.matricularEstudianteAcarrera(new Estudia(a8,c1,true,6));

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

        System.out.println("Ya termino");
    }
}
