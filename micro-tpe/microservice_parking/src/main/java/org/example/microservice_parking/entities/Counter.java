package org.example.microservice_parking.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "counters")
public class Counter {

    @Id
    private String id;
    private Long seq;

    public Counter(String id, Long seq){
        this.id = id;
        this.seq = seq;
    }
}
