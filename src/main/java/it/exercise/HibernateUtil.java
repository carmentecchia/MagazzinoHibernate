package it.exercise;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    private static SessionFactory sessionFactory = null;

    static {
        try {
            Configuration cfg = new Configuration().configure();
            cfg.addAnnotatedClass(Prodotti.class);
            cfg.addAnnotatedClass(OrdiniProdotti.class);
            cfg.addAnnotatedClass(Ordini.class);
            cfg.addAnnotatedClass(OrdiniProdotti.class);
            cfg.addAnnotatedClass(Cliente.class);

            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                    .applySettings(cfg.getProperties());
            sessionFactory = cfg.buildSessionFactory(builder.build());
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}