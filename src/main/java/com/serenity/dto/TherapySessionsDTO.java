package com.serenity.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class TherapySessionsDTO {
    private int id;
    private int therapy;
    private int patient;
    private int program;
    /*private double cost;
    private String description;*/
    private Date date;


    public TherapySessionsDTO(int therapy, int patient, int program/*double cost, String description*/,Date date) {
        this.therapy=therapy;
        this.patient=patient;
        this.program=program;
        /*this.cost=cost;
        this.description=description;*/
        this.date =date;
    }
}
