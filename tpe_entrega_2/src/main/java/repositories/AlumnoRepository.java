package repositories;

import dtos.AlumnoDTO;
import entities.Alumno;
import utils.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class AlumnoRepository {
    private EntityManager em;
    
    public AlumnoRepository(){
        this.em = JPAUtil.getEntityManager();
    }
    
    public void darAltaAlumno(Alumno alumno){
        em.getTransaction().begin();
        em.persist(alumno);
        em.getTransaction().commit();
    }

    public Alumno getAlumnoById(long id){
        return em.find(Alumno.class, id);
    }

    //nos falta el criterio
    public List<Alumno> getAlumnosByOrder(){
        em.getTransaction().begin();
        String jpql = "SELECT new Alumno(a.id_alumno,a.nombre,a.apellido,a.edad,a.genero,a.dni, a.ciudad, a.legajo) FROM Alumno a ORDER BY edad";
        TypedQuery<Alumno> TypedQuery = em.createQuery(jpql, Alumno.class);
        List<Alumno> listAlumnos = TypedQuery.getResultList();
        em.getTransaction().commit();
        return listAlumnos;
    }

    public Alumno getAlumnoByLegajo(int legajo){
        em.getTransaction().begin();
        String jpql = "SELECT new Alumno(a.id_alumno,a.nombre,a.apellido,a.edad,a.genero,a.dni, a.ciudad, a.legajo) FROM Alumno a WHERE a.legajo = ?1";
        TypedQuery<Alumno> TypedQuery = em.createQuery(jpql, Alumno.class);
        TypedQuery.setParameter(1, legajo);
        Alumno alumno = TypedQuery.getSingleResult();
        em.getTransaction().commit();
        return alumno;
    }

    public List<Alumno> getAlumnosByGenero(String genero){
        em.getTransaction().begin();
        String jpql = "SELECT new Alumno(a.id_alumno,a.nombre,a.apellido,a.edad,a.genero,a.dni, a.ciudad, a.legajo) FROM Alumno a WHERE a.genero = ?1";
        TypedQuery<Alumno> TypedQuery = em.createQuery(jpql, Alumno.class);
        TypedQuery.setParameter(1, genero);
        List<Alumno> listAlumnos = TypedQuery.getResultList();
        em.getTransaction().commit();
        return listAlumnos;
    }

    public List<AlumnoDTO> getAlumnosByCarreraAndCity(String carrera, String ciudad) {
        em.getTransaction().begin();
        String jpql = "SELECT new dtos.AlumnoDTO(a.nombre,a.apellido,a.edad,a.genero,a.dni,a.ciudad,a.legajo,e.carrera.nombre) FROM Alumno a JOIN a.carreras e WHERE e.carrera.nombre = ?1 AND a.ciudad = ?2";
        TypedQuery<AlumnoDTO> TypedQuery = em.createQuery(jpql, AlumnoDTO.class);
        TypedQuery.setParameter(1, carrera);
        TypedQuery.setParameter(2, ciudad);
        List<AlumnoDTO> listAlumnos = TypedQuery.getResultList();
        em.getTransaction().commit();
        return listAlumnos;
    }
}
