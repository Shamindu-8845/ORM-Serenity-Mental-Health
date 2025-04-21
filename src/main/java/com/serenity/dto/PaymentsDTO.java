package com.serenity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentsDTO {
    private int id;
    private int theropy;
    private int patient;
    private double payment;
    /*private String status;*/
    private Date date;
    private String method;

    public PaymentsDTO(int therapy, int patient, double payment/*, String statu*/,Date date,String method) {
        this.theropy=therapy;
        this.patient=patient;
        this.payment=payment;
       /* this.status=status;*/
        this.date = date;
        this.method = method;
    }
}
