package com.serenity.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientsTm {
    private  int id;
    private String name;
    /*private String duration;
    private double cost;
    private String description;*/
    private String gender;
    private String phoneNo;
}
