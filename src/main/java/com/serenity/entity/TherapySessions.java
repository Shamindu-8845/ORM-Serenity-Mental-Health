package com.serenity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class TherapySessions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
   /* private double cost;
    private String description;*/
    private Date date;
    @ManyToOne(cascade = CascadeType.ALL)
    private Patients patients;
    @ManyToOne(cascade = CascadeType.ALL)
    private Therapists therapists;
    @ManyToOne(cascade = CascadeType.ALL)
    private TherapyPrograms therapyPrograms;

    public TherapySessions(/*double cost, String description,*/Date date, Patients patient, Therapists therapy, TherapyPrograms program) {
       /* this.cost=cost;
        this.description=description;*/
        this.date = date;
        this.patients=patient;
        this.therapists=therapy;
        this.therapyPrograms=program;
    }
}
