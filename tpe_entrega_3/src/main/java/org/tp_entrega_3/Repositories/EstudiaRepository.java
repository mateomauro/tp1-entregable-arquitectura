package org.tp_entrega_3.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tp_entrega_3.Models.Estudia;
import org.tp_entrega_3.Models.EstudiaID;

@Repository("EstudiaRepository")
public interface EstudiaRepository extends JpaRepository<Estudia, EstudiaID> {
}
