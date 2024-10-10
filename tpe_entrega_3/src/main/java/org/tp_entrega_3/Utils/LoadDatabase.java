package org.tp_entrega_3.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tp_entrega_3.Models.Alumno;
import org.tp_entrega_3.Models.Carrera;
import org.tp_entrega_3.Models.Estudia;
import org.tp_entrega_3.Repositories.AlumnoRepository;
import org.tp_entrega_3.Repositories.CarreraRepository;
import org.tp_entrega_3.Repositories.EstudiaRepository;

@Component
public class LoadDatabase {
    @Autowired
    private AlumnoRepository alumnoRepository;
    @Autowired
    private CarreraRepository carreraRepository;
    @Autowired
    private EstudiaRepository estudiaRepository;


     //CREAMOS LOS ALUMNOS
     Alumno mateoMauro = new Alumno("mateo","mauro",22,"Masculino",43867965,"Tandil",25254);
     Alumno tomasDonati = new Alumno("tomas", "donati", 23, "Masculino",345345345,"Tandil", 25253);
     Alumno lorenzoIpsu = new Alumno("lorenzo", "ipsu", 25, "Masculino",2121231,"Tandil", 25251);
     Alumno delfinaFerreyra = new Alumno("delfina", "ferreyra", 24, "Femenino",6786786,"Tandil", 25259);
     Alumno enzoDelSole = new Alumno("enzo", "del sole", 23, "Masculino",9999999,"Tandil", 25258);
     Alumno emilioColombo = new Alumno("emilio", "colombo", 22, "Masculino",11111111,"Tandil", 1111);
     Alumno lucianoMauro = new Alumno("luciano", "mauro", 30, "Masculino",5555555,"Tandil", 25257);

    // a) dar de alta un estudiante
    public void loadAlumnos() throws Exception {
        estudiaRepository.deleteAll();
        alumnoRepository.deleteAll();
        try {
            alumnoRepository.save(mateoMauro);
            alumnoRepository.save(tomasDonati);
            alumnoRepository.save(lorenzoIpsu);
            alumnoRepository.save(delfinaFerreyra);
            alumnoRepository.save(enzoDelSole);
            alumnoRepository.save(emilioColombo);
            alumnoRepository.save(lucianoMauro);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    //CREAMOS CARRERA
    Carrera tudai = new Carrera("TUDAI");
    Carrera ing = new Carrera("ING");
    Carrera abogacia = new Carrera("ABOGACIA");
    public void loadCarreras() throws Exception {
        carreraRepository.deleteAll();
        try {
            carreraRepository.save(tudai);
            carreraRepository.save(ing);
            carreraRepository.save(abogacia);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public void loadEstudia() throws Exception {
        /*Alumno mateoMauro = alumnoRepository.getAlumnoByDNI(43867965);
        Alumno tomasDonati = alumnoRepository.getAlumnoByDNI(345345345);
        Alumno lorenzoIpsu = alumnoRepository.getAlumnoByDNI(2121231);
        Alumno delfinaFerreyra =  alumnoRepository.getAlumnoByDNI(6786786);
        Alumno enzoDelSole = alumnoRepository.getAlumnoByDNI(9999999);

        Carrera tudai = carreraRepository.getCarreraByName("TUDAI");
        Carrera INGENIERIA = carreraRepository.getCarreraByName("ING");
        Carrera abogacia = carreraRepository.getCarreraByName("ABOGACIA");*/

        Estudia mateoMauro_Estudia = new Estudia(tudai,mateoMauro,2022,2024);
        Estudia tomasDonati_Estudia = new Estudia(tudai,tomasDonati,2022,2024);
        Estudia lorenzoIpsu_Estudia = new Estudia(tudai,lorenzoIpsu,2020,2022);
        Estudia delfinaFerreyra_Estudia = new Estudia(abogacia,delfinaFerreyra,2020, 2024);
        Estudia enzoDelSole_Estudia = new Estudia(abogacia,enzoDelSole,2018,2020);
        Estudia emilioColombo_Estudia = new Estudia(ing,emilioColombo,2020, 0);
        Estudia lucianoMauro_Estudia = new Estudia(ing,lucianoMauro,2020,0);
        try {
            estudiaRepository.save(mateoMauro_Estudia);
            estudiaRepository.save(tomasDonati_Estudia);
            estudiaRepository.save(lorenzoIpsu_Estudia);
            estudiaRepository.save(delfinaFerreyra_Estudia);
            estudiaRepository.save(enzoDelSole_Estudia);
            estudiaRepository.save(emilioColombo_Estudia);
            estudiaRepository.save(lucianoMauro_Estudia);

        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
