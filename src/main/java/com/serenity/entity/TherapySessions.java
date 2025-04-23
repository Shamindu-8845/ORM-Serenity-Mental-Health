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

    @ManyToOne(cascade = CascadeType.ALL)
    private Patients patients;

    @ManyToOne(cascade = CascadeType.ALL)
    private Therapists therapists;

    @ManyToOne(cascade = CascadeType.ALL)
    private TherapyPrograms therapyPrograms;

    private String description;
    private Date date;

    public TherapySessions(Date date, Patients patient, Therapists therapist, TherapyPrograms program, String description) {
        this.date = date;
        this.patients = patient;
        this.therapists = therapist;
        this.therapyPrograms = program;
        this.description = description;
    }
}
