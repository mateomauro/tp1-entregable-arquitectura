package org.example.microservicemaintenance.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class Maintenance implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_maintenance;
    private long id_skateboard;
    @Column(columnDefinition = "TINYINT(1)")
    private boolean repair;


    public Maintenance(boolean repair, long id_skateboard) {
        this.repair = repair;
        this.id_skateboard = id_skateboard;
    }

    public Maintenance() {

    }

}
