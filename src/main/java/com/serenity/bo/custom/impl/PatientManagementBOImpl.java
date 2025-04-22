package com.serenity.bo.custom.impl;

/*import org.example.bo.custom.PatientManagementBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.PatientsDAO;
import org.example.dto.PatientsDTO;
import org.example.entity.Patients;*/

import com.serenity.bo.custom.PatientManagementBO;
import com.serenity.dao.DAOFactory;
import com.serenity.dao.custom.PatientsDAO;
import com.serenity.dto.PatientsDTO;
import com.serenity.entity.Patients;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PatientManagementBOImpl implements PatientManagementBO {
    PatientsDAO patientsDAO = (PatientsDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PATIENTS);
    @Override
    public boolean savePatient(PatientsDTO patientsDTO) throws IOException {
        return patientsDAO.save(new Patients(patientsDTO.getName(),patientsDTO.getGender(),patientsDTO.getPhoneNo()));
    }

    @Override
    public boolean updatePatient(PatientsDTO patientsDTO) throws IOException {
        return patientsDAO.update(new Patients(patientsDTO.getId(),patientsDTO.getName(),patientsDTO.getGender(),patientsDTO.getPhoneNo()));
    }

    @Override
    public List<PatientsDTO> getAllPatients() throws IOException {
        List<Patients> all = patientsDAO.getAll();
        List<PatientsDTO> patientsDTOS = new ArrayList<>();
        for (Patients patients:all){
            patientsDTOS.add(new PatientsDTO(patients.getId(),patients.getGender(),patients.getPhoneNo(),patients.getName()));
        }

        return patientsDTOS;
    }

    @Override
    public String getLastId() throws IOException {
        int lastId = patientsDAO.getLastId();
        return String.format("P%03d", lastId);
    }

    @Override
    public boolean deletePatient(String text) throws IOException {
        return patientsDAO.delete(text);
    }

    @Override
    public List<PatientsDTO> searchPatient(String text) throws IOException {
        List<Patients> search = patientsDAO.search(text);
        List<PatientsDTO> patientsDTOS = new ArrayList<>();

        for (Patients patients:search){
            patientsDTOS.add(new PatientsDTO(patients.getId(),patients.getGender(),patients.getPhoneNo(),patients.getName()));
        }

        return patientsDTOS;
    }
}
