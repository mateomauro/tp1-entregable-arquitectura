package org.tp_entrega_3.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tp_entrega_3.Models.Alumno;
import org.tp_entrega_3.Models.Estudia;
import org.tp_entrega_3.Repositories.EstudiaRepository;

import java.util.List;

@Service("EstudiaService")
public class EstudiaService {
    @Autowired
    private EstudiaRepository repository;

    public List<Estudia> findAll() throws Exception {
        try {
            return repository.findAll();
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public Estudia matricularAlumnoACarrera(Estudia entity) throws Exception {
        try{
            return repository.save(entity);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
