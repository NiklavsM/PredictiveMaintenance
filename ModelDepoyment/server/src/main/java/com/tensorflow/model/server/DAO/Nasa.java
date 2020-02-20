package com.tensorflow.model.server.DAO;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@AllArgsConstructor
public class Nasa {

    @Id
    private Integer id;
//    private Date timestamp;
    private Double setting1;
    private Double setting2;
    private Double s2;
    private Double s3;
    private Double s4;
    private Double s6;
    private Double s7;
    private Double s8;
    private Double s9;
    private Double s11;
    private Double s12;
    private Double s13;
    private Double s14;
    private Double s15;
    private Double s17;
    private Double s20;
    private Double s21;

    public Integer getId() {
        return id;
    }
}
