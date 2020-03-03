package com.tensorflow.model.server.DAO;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
public class NasaPCA {

    @Id
    private Integer id;
    private Integer timestamp;
    private Double s1;
    private Double s2;
    private Double s3;
    private Double s4;
    private Double s5;
    private Double s6;
    private Double s7;
    private Double s8;
    private Double s9;
    private Double s10;
    private Double s11;

    public NasaPCA(){

    }
    public Integer getId() {
        return id;
    }
}
