package com.serenity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Getter
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double payment;
    private String status;
    private Date date;
    private String method;
    @ManyToOne(cascade = CascadeType.ALL)
    private Patients patients;
    @ManyToOne(cascade = CascadeType.ALL)
    private Therapists therapists;

    public Payments(double payment, String status,Date date,String method, Patients patients, Therapists therapists) {
        this.payment=payment;
        this.status=status;
        this.patients=patients;
        this.therapists=therapists;
        this.method= method;
        this.date = date;
    }
}
