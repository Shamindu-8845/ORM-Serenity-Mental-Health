package com.serenity.dao.custom.impl;

//import org.example.config.FactoryConfiguration;
//import org.example.dao.custom.TherapySessionsDAO;
//import org.example.entity.TherapySessions;
import com.serenity.config.FactoryConfiguration;
import com.serenity.dao.custom.TherapySessionsDAO;
import com.serenity.entity.TherapySessions;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.List;

public class TherapySessionsDAOImpl implements TherapySessionsDAO {
    @Override
    public boolean save(TherapySessions therapySessions) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            session.merge(therapySessions); // 🔥 Use merge instead of persist

            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(TherapySessions therapySessions) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.merge(therapySessions);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public int getLastId() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        List<TherapySessions> fromTherapySessions = session.createQuery("FROM TherapySessions", TherapySessions.class).list();

        if (fromTherapySessions.isEmpty()) {
            return 1;
        }

        int lastId = (int) session.createQuery("SELECT ts.id FROM TherapySessions ts ORDER BY ts.id DESC")
                .setMaxResults(1)
                .uniqueResult();

        return lastId + 1;
    }

    @Override
    public boolean delete(String lid) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        TherapySessions therapySessions = session.get(TherapySessions.class, lid);

        session.remove(therapySessions);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public List<TherapySessions> getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        return session.createQuery("from TherapySessions ").list();
    }

    @Override
    public TherapySessions getbyId(int text) throws IOException {
        return null;
    }

    @Override
    public List getAllId() throws IOException {
        return null;
    }
}
