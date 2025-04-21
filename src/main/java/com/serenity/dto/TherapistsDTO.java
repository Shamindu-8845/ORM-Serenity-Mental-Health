package com.serenity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TherapistsDTO {
    private int id;
    private String name;
    /*private String status;*/
    private String phoneNo;
    private String program;


    public TherapistsDTO(String name/*, String status*/,String phoneNo, String program) {
        this.name=name;
        /*this.status=status;*/
        this.phoneNo =phoneNo;
        this.program=program;
    }
}
