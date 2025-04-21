package com.serenity.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TherapistTm {
    private int id;
    private String name;
    private String phoneNo;
    private String program;

    public TherapistTm(String name/* String status,*/,String phoneNo, String program) {
        this.name=name;
        this.phoneNo = phoneNo;
        this.program=program;
    }
}
