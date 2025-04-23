package com.serenity.bo.custom.impl;

/*import org.example.bo.custom.PaymentInvoiceManagementBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.PatientsDAO;
import org.example.dao.custom.PaymentsDAO;
import org.example.dao.custom.TherapistDAO;
import org.example.dto.PaymentsDTO;
import org.example.entity.Patients;
import org.example.entity.Payments;
import org.example.entity.Therapists;*/

import com.serenity.bo.custom.PaymentInvoiceManagementBO;
import com.serenity.dao.DAOFactory;
import com.serenity.dao.custom.PatientsDAO;
import com.serenity.dao.custom.PaymentsDAO;
import com.serenity.dto.PaymentsDTO;
import com.serenity.entity.Patients;
import com.serenity.entity.Therapists;
import com.serenity.dao.custom.TherapistDAO;
import com.serenity.entity.Payments;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PaymentInvoiceManagementBOImpl implements PaymentInvoiceManagementBO {
    PaymentsDAO paymentsDAO = (PaymentsDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PAYMENTS);
    PatientsDAO patientsDAO = (PatientsDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PATIENTS);
    TherapistDAO therapistDAO = (TherapistDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.THERAPIST);
    /*@Override
    public boolean savePayment(PaymentsDTO paymentsDTO) throws IOException {
        Patients patients = patientsDAO.getbyId(paymentsDTO.getPatient());
        Therapists therapists = therapistDAO.getbyId(paymentsDTO.getTheropy());
        return paymentsDAO.save(new Payments(paymentsDTO.getPayment(), paymentsDTO.getMethod(), patients, therapists));
    }

    @Override
    public boolean updatePayment(PaymentsDTO paymentsDTO) throws IOException {
        Patients patients = patientsDAO.getbyId(paymentsDTO.getPatient());
        Therapists therapists = therapistDAO.getbyId(paymentsDTO.getTheropy());
        return paymentsDAO.update(new Payments(paymentsDTO.getId(),paymentsDTO.getPayment(), paymentsDTO.getMethod(), patients, therapists));
    }*/
    @Override
    public boolean savePayment(PaymentsDTO paymentsDTO) throws IOException {
        Patients patients = patientsDAO.getbyId(paymentsDTO.getPatient());
        Therapists therapists = therapistDAO.getbyId(paymentsDTO.getTheropy());

        Payments payments = new Payments(
                paymentsDTO.getId(),
                paymentsDTO.getPayment(),
                paymentsDTO.getMethod(),
                paymentsDTO.getDate(),
                paymentsDTO.getStatus(),
                patients,
                therapists
        );

        return paymentsDAO.save(payments);
    }

    @Override
    public boolean updatePayment(PaymentsDTO paymentsDTO) throws IOException {
        Patients patients = patientsDAO.getbyId(paymentsDTO.getPatient());
        Therapists therapists = therapistDAO.getbyId(paymentsDTO.getTheropy());

        Payments payments = new Payments(
                paymentsDTO.getId(),
                paymentsDTO.getPayment(),
                paymentsDTO.getMethod(),
                paymentsDTO.getDate(),
                paymentsDTO.getMethod(),
                patients,
                therapists
        );

        return paymentsDAO.update(payments);
    }

    @Override
    public String getlastId() throws IOException {
        int lastId = paymentsDAO.getLastId();
        return String.format("P%03d",lastId);
    }

    @Override
    public boolean deletePayment(String text) throws IOException {
        return paymentsDAO.delete(text);
    }

    @Override
    public List<PaymentsDTO> getAllPayments() throws IOException {
        List<Payments> all = paymentsDAO.getAll();
        List<PaymentsDTO> paymentsDTOS = new ArrayList<>();

      /*  for (Payments payments:all){
            paymentsDTOS.add(new PaymentsDTO(payments.getTherapists().getId(),payments.getPatients().getId(),payments.getPayment(),payments.getStatus()));
        }
        return paymentsDTOS;*/
        for (Payments payments : all) {
            paymentsDTOS.add(new PaymentsDTO(
                    payments.getTherapists().getId(),
                    payments.getPatients().getId(),
                    payments.getPayment(),
                    payments.getStatus(),
                    payments.getDate(),
                    payments.getMethod()
            ));
        }
        return paymentsDTOS;
    }

    @Override
    public List getAllPatientsId() throws IOException {
        return patientsDAO.getAllId();
    }

    @Override
    public List getAllTherapyId() throws IOException {
        return therapistDAO.getAllId();
    }
}
