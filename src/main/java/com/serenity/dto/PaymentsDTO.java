package com.serenity.dto;

import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class PaymentsDTO {
    private int id;
    private int theropy;
    private int patient;
    private double payment;
    private String status;
    private Date date;
    private String method;

    public PaymentsDTO(int therapy, int patient, double payment, String status,Date date,String method) {
        this.theropy=therapy;
        this.patient=patient;
        this.payment=payment;
        this.status=status;
        this.date = date;
        this.method = method;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) { this.date = date; }

    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }
}
