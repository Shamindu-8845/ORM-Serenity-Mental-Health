package com.serenity.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TherapyProgramTm {
    private String programId;
    private String name;
    private String duration;
    private double cost;
    private String description;


}
