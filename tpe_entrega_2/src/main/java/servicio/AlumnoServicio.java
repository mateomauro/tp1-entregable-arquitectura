package servicio;

import entities.Alumno;
import utils.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class AlumnoServicio {
    private EntityManager em;
    
    public AlumnoServicio(){
        this.em = JPAUtil.getEntityManager();
    }
    
    public void darAltaEstudiante(Alumno alumno){
        em.getTransaction().begin();
        em.persist(alumno);
        em.getTransaction().commit();
    }

    public Alumno getAlumnoXid(long id){
        return em.find(Alumno.class, id);
    }

    //nos falta el criterio
    public List<Alumno> getEstudiantesXorden(){
        em.getTransaction().begin();
        String jpql = "SELECT new Alumno(a.id_alumno,a.nombre,a.apellido,a.edad,a.genero,a.dni, a.ciudad, a.legajo) FROM Alumno a ORDER BY edad";
        TypedQuery<Alumno> TypedQuery = em.createQuery(jpql, Alumno.class);
        List<Alumno> listaAlumnos = TypedQuery.getResultList();
        em.getTransaction().commit();
        return listaAlumnos;
    }

    public Alumno getEstudianteByLegajo(int legajo){
        em.getTransaction().begin();
        String jpql = "SELECT new Alumno(a.id_alumno,a.nombre,a.apellido,a.edad,a.genero,a.dni, a.ciudad, a.legajo) FROM Alumno a WHERE a.legajo = ?1";
        TypedQuery<Alumno> TypedQuery = em.createQuery(jpql, Alumno.class);
        TypedQuery.setParameter(1, legajo);
        Alumno alumno = TypedQuery.getSingleResult();
        em.getTransaction().commit();
        return alumno;
    }

    public List<Alumno> getAlumnosBygenero(String genero){
        em.getTransaction().begin();
        String jpql = "SELECT new Alumno(a.id_alumno,a.nombre,a.apellido,a.edad,a.genero,a.dni, a.ciudad, a.legajo) FROM Alumno a WHERE a.genero = ?1";
        TypedQuery<Alumno> TypedQuery = em.createQuery(jpql, Alumno.class);
        TypedQuery.setParameter(1, genero);
        List<Alumno> listaAlumnos = TypedQuery.getResultList();
        em.getTransaction().commit();
        return listaAlumnos;
    }
}
