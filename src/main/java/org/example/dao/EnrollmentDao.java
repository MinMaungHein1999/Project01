package org.example.dao;

import org.example.model.Enrollment;
import org.example.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class EnrollmentDao extends AbstractDao<Enrollment>{
    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    public EnrollmentDao() {
        super(sessionFactory);
    }

    @Override
    public Class<Enrollment> getEntityClass() {
        return Enrollment.class;
    }
}
