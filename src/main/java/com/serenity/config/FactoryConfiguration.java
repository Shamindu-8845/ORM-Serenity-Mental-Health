package com.serenity.config;

import com.serenity.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;


public class FactoryConfiguration {
    private static  FactoryConfiguration factoryConfiguration;
    private static SessionFactory session;

    private FactoryConfiguration() throws IOException {
        Properties properties = new Properties();
        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("hibernate.properties"));
        Configuration configuration = new Configuration();
            configuration.setProperties(properties);
               configuration .addAnnotatedClass(Users.class).addAnnotatedClass(Patients.class)
                .addAnnotatedClass(Payments.class).addAnnotatedClass(Therapists.class)
                .addAnnotatedClass(TherapyPrograms.class).addAnnotatedClass(TherapySessions.class);
        session = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() throws IOException {
        return (factoryConfiguration==null)?factoryConfiguration=new FactoryConfiguration():
                factoryConfiguration;
    }
    public static Session getSession(){
       return session.openSession();
    }

}
