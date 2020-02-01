package com.tensorflow.model.server.DAO;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Nasa {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;
    private Date timestamp;
    private double setting1;
    private double setting2;
    private double s2;
    private double s3;
    private double s4;
    private double s6;
    private double s7;
    private double s8;
    private double s9;
    private double s11;
    private double s12;
    private double s13;
    private double s14;
    private double s15;
    private double s17;
    private double s20;
    private double s21;
}
