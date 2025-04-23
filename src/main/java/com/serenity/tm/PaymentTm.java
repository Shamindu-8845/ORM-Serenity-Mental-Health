package com.serenity.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentTm {
    private int therapy;
    private int patient;
    private double payment;
    private String status;
    private Date date;
    private String method;
}
