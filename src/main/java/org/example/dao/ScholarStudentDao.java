package org.example.dao;

import org.example.model.ScholarStudent;
import org.example.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class ScholarStudentDao extends AbstractDao<ScholarStudent>{
    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public ScholarStudentDao() {
        super(sessionFactory);
    }

    @Override
    public Class<ScholarStudent> getEntityClass() {
        return ScholarStudent.class;
    }
}
