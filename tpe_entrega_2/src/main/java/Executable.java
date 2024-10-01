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

        //CREAMOS LOS ALUMNOS
        Alumno mateoMauro = new Alumno("mateo","mauro",22,"Masculino",43867965,"Tandil",25254);
        Alumno tomasDonati = new Alumno("tomas", "donati", 23, "Masculino",345345345,"Tandil", 25253);
        Alumno lorenzoIpsu = new Alumno("lorenzo", "ipsu", 25, "Masculino",2121231,"Tandil", 25251);
        Alumno delfinaFerreyra = new Alumno("delfina", "ferreyra", 24, "Femenino",6786786,"Tandil", 25259);
        Alumno enzoDelSole = new Alumno("enzo", "del sole", 23, "Masculino",9999999,"Tandil", 25258);
        Alumno emilioColombo = new Alumno("emilio", "colombo", 22, "Masculino",11111111,"Tandil", 1111);
        Alumno lucianoMauro = new Alumno("luciano", "mauro", 30, "Masculino",5555555,"Tandil", 25257);

        //a) dar de alta un estudiante
        alumnoServicio.darAltaAlumno(mateoMauro);
        alumnoServicio.darAltaAlumno(tomasDonati);
        alumnoServicio.darAltaAlumno(lorenzoIpsu);
        alumnoServicio.darAltaAlumno(delfinaFerreyra);
        alumnoServicio.darAltaAlumno(enzoDelSole);
        alumnoServicio.darAltaAlumno(emilioColombo);
        alumnoServicio.darAltaAlumno(lucianoMauro);

        //CREAMOS CARRERA
        Carrera tudai = new Carrera("TUDAI");
        Carrera ing = new Carrera("ING");
        Carrera abogacia = new Carrera("ABOGACIA");

        //INSERTAMOS LAS CARRERAS
        carreraServicio.darAltaCarrera(tudai);
        carreraServicio.darAltaCarrera(ing);
        carreraServicio.darAltaCarrera(abogacia);

        //b) matricular un estudiante en una carrera
        estudiaServicio.matricularAlumnoAcarrera(new Estudia(mateoMauro,tudai,2022,2024));
        estudiaServicio.matricularAlumnoAcarrera(new Estudia(tomasDonati,tudai,2022,2024));
        estudiaServicio.matricularAlumnoAcarrera(new Estudia(lorenzoIpsu,tudai,2020,2022));
        estudiaServicio.matricularAlumnoAcarrera(new Estudia(delfinaFerreyra,abogacia,2020,2024));
        estudiaServicio.matricularAlumnoAcarrera(new Estudia(enzoDelSole,abogacia,2018,2020));
        estudiaServicio.matricularAlumnoAcarrera(new Estudia(emilioColombo,ing,2020));
        estudiaServicio.matricularAlumnoAcarrera(new Estudia(lucianoMauro,ing,2020));


        //c) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
        System.out.println("Recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.(ordenado por edad de menor a mayor)" );
        List<Alumno> listaDeAlumnosOrdenadosPorEdad = alumnoServicio.getAlumnosByOrder();
        for (Alumno alumno:listaDeAlumnosOrdenadosPorEdad){
            System.out.println(alumno);
        }

        System.out.println("--------------------------------------------------------------------");

        //d) recuperar un estudiante, en base a su número de libreta universitaria.
        System.out.println("Recuperar un estudiante, en base a su número de libreta universitaria.  (legajo: 25254)");
        Alumno alumnoBylegajo = alumnoServicio.getAlumnoByLegajo(25254);
        System.out.println(alumnoBylegajo);

        System.out.println("--------------------------------------------------------------------");

        //e) recuperar todos los estudiantes, en base a su género.
        System.out.println("Recuperar todos los estudiantes, en base a su género. (genero: Masculino)");
        List<Alumno> listaDeAlumnosBygenero = alumnoServicio.getAlumnosByGenero("Masculino");
        for (Alumno alumno:listaDeAlumnosBygenero){
            System.out.println(alumno);
        }

        System.out.println("--------------------------------------------------------------------");

        //f) recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.
        System.out.println("Recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos. (ordenado de mayor a menor)");
        List<CarreraDTO> carrerasConAlumnosInscriptos = carreraServicio.getCarrerasConAlumnosInscriptos();
        for (CarreraDTO carreraDTO:carrerasConAlumnosInscriptos){
            System.out.println(carreraDTO);
        }

        System.out.println("--------------------------------------------------------------------");

        //g) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
        System.out.println("Recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia. (carrera: TUDAI, ciudad: Tandil");
        List<AlumnoDTO> alumnosPorCarrera = alumnoServicio.getAlumnosByCarreraAndCity("TUDAI","Tandil");
        for (AlumnoDTO alumnoDTO:alumnosPorCarrera){
            System.out.println(alumnoDTO);
        }

        System.out.println("--------------------------------------------------------------------");

        /*3) Generar un reporte de las carreras, que para cada carrera incluya información de los
        inscriptos y egresados por año. Se deben ordenar las carreras alfabéticamente, y presentar
        los años de manera cronológica*/
        System.out.println("Generar un reporte de las carreras, que para cada carrera incluya información de los inscriptos y egresados por año. Se deben ordenar las carreras alfabéticamente, y presentar los años de manera cronológica.");
        List<CarreraDTO> reporteCarreras = carreraServicio.getReporteCarreras();
        for (CarreraDTO carreraDTO:reporteCarreras){
            System.out.println(carreraDTO);
        }
    }
}
