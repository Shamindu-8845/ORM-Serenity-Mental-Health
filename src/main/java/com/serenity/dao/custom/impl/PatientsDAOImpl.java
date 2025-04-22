package com.serenity.dao.custom.impl;

/*import org.example.config.FactoryConfiguration;*/
import com.serenity.config.FactoryConfiguration;
/*import org.example.dao.custom.PatientsDAO;*/
import com.serenity.dao.custom.PatientsDAO;
/*import org.example.entity.Patients;*/
import com.serenity.entity.Patients;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.List;

public class PatientsDAOImpl implements PatientsDAO {
    @Override
    public boolean save(Patients patients) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.persist(patients);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean update(Patients patients) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.merge(patients);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public List<Patients> getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();

        return session.createQuery("FROM Patients ").list();
    }

    @Override
    public int getLastId() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        List<Patients> fromPatients = session.createQuery("FROM Patients ", Patients.class).list();

        if (fromPatients.isEmpty()){
            return 1;
        }

        return (int) session.createQuery("SELECT p.id FROM Patients p ORDER BY p.id DESC").setMaxResults(1).uniqueResult();

    }

    @Override
    public boolean delete(String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Patients patients = session.get(Patients.class, id);

        session.remove(patients);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public List<Patients> search(String text) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();

        return session.createQuery("FROM Patients p WHERE p.name like :name OR p.duration like :duration " +
                "OR p.description like :description").
                setParameter("name",text).setParameter("duration",text).
                setParameter("description",text).list();

    }

    @Override
    public Patients getbyId(int patient) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Patients patients = session.get(Patients.class, patient);
        return patients;
    }

    @Override
    public List getAllId() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        return session.createQuery("SELECT p.id FROM Patients p").list();
    }
}
