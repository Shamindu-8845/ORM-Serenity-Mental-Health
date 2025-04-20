package com.serenity.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class FactoryConfiguration {
    private static  FactoryConfiguration factoryConfiguration;
    private static SessionFactory session;

    private FactoryConfiguration(){
        Configuration configuration = new Configuration().configure();
        session = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance(){
        return (factoryConfiguration==null)?factoryConfiguration=new FactoryConfiguration():
                factoryConfiguration;
    }
    public static Session getSession(){
       return session.openSession();
    }

}
