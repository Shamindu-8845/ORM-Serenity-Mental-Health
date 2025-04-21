package com.serenity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PatientsDTO {
    private int id;
    private String name;
   /* private String duration;
    private double cost;
    private String description;*/
    private String gender;
    private String phoneNo;

    public PatientsDTO(int id, String name/*tring duration, double cost, String description*/,String gender,String phoneNo) {
            this.id=id;
            this.name=name;
            /*this.duration=duration;
            this.cost=cost;
            this.description=description;*/
            this.gender = gender;
            this.phoneNo =phoneNo;
    }

    public PatientsDTO(String name/*String duration, double cost, String description*/,String gender,String phoneNo) {
        this.name=name;
      /*  this.duration=duration;
        this.cost=cost;
        this.description=description;*/
        this.gender = gender;
        this.phoneNo =  phoneNo;
    }
}
