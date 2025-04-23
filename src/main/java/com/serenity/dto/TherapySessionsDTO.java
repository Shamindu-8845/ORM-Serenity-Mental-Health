package com.serenity.dto;
import com.serenity.entity.Patients;
import com.serenity.entity.Therapists;
import com.serenity.entity.TherapyPrograms;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class TherapySessionsDTO {
    private int id;
    private int therapy;
    private int patient;
    private int program;
    /*private double cost;*/
    private String description;
    private Date date;


    /*public TherapySessionsDTO(int therapy, int patient, int program,*//*double cost,*//* String description,Date date) {
        this.therapy=therapy;
        this.patient=patient;
        this.program=program;
       *//* this.cost=cost;*//*
        this.description=description;
        this.date =date;
    }*/
   /* public TherapySessions(int id, Date date, Patients patient, Therapists therapist, TherapyPrograms program, String description) {
        this.id = id;
        this.date = date;
        this.patient = patient;
        this.therapist = therapist;
        this.program = program;
        this.description = description;
    }*/


}
