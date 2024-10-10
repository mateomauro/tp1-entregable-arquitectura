package org.tpe_entrega_3.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tpe_entrega_3.Models.Estudia;
import org.tpe_entrega_3.Models.EstudiaID;

@Repository("EstudiaRepository")
public interface EstudiaRepository extends JpaRepository<Estudia, EstudiaID> {
}
