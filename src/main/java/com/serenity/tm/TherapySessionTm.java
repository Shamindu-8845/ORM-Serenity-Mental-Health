package com.serenity.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TherapySessionTm {
    private int therapy;
    private int patient;
    private int program;
    private Date date;

}
