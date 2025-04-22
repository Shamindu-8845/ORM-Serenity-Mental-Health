package com.serenity.bo.custom.impl;

/*import org.example.bo.custom.PatientTheropyHistoryBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.TherapySessionsDAO;
import org.example.dto.TherapySessionsDTO;
import org.example.entity.TherapySessions;*/

import com.serenity.bo.custom.PatientTheropyHistoryBO;
import com.serenity.dao.DAOFactory;
import com.serenity.dao.custom.TherapySessionsDAO;
import com.serenity.dto.TherapySessionsDTO;
import com.serenity.entity.TherapySessions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PatientTheropyHistoryBOImpl implements PatientTheropyHistoryBO {
    TherapySessionsDAO therapySessionsDAO = (TherapySessionsDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.THERAPY_SESSION);
    @Override
    public List<TherapySessionsDTO> getAll() throws IOException {

        List<TherapySessions> all = therapySessionsDAO.getAll();
        List<TherapySessionsDTO> therapySessionsDTOS = new ArrayList<>();

        for (TherapySessions therapySessions : all) {
            therapySessionsDTOS.add(new TherapySessionsDTO(
                    therapySessions.getId(),                              // id
                    therapySessions.getTherapyPrograms().getId(),         // therapy
                    therapySessions.getPatients().getId(),                // patient
                    therapySessions.getTherapyPrograms().getId(),         // program
                    therapySessions.getTherapyPrograms().getDescription(),                     // description
                    therapySessions.getDate()                             // date
            ));
        }

        return therapySessionsDTOS;
    }
}
