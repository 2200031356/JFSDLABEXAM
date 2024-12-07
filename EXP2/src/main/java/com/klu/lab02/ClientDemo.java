package com.klu.lab02;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ClientDemo {
    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = config.buildSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            // Update operation using HQL
            String hql = "UPDATE Department SET name = :name, location = :location WHERE id = :id";
            int updatedEntities = session.createQuery(hql)
                    .setParameter("name", "aids")
                    .setParameter("location", "	qwert")
                    .setParameter("id", 2)
                    .executeUpdate();

            transaction.commit();
            System.out.println(updatedEntities + " record(s) updated successfully.");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
