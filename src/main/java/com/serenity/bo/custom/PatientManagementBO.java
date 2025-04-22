package com.serenity.bo.custom;

/*import org.example.bo.SuperBO;*/
/*import org.example.dto.PatientsDTO;*/

import com.serenity.bo.SuperBO;
import com.serenity.dto.PatientsDTO;

import java.io.IOException;
import java.util.List;

public interface PatientManagementBO extends SuperBO {
    boolean savePatient(PatientsDTO patientsDTO) throws IOException;

    boolean updatePatient(PatientsDTO patientsDTO) throws IOException;

    List<PatientsDTO> getAllPatients() throws IOException;

    String getLastId() throws IOException;

    boolean deletePatient(String text) throws IOException;

    List<PatientsDTO> searchPatient(String text) throws IOException;
}
