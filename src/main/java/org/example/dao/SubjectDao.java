package org.example.dao;

import org.example.model.Subject;
import org.example.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class SubjectDao extends AbstractDao<Subject>{
    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    public SubjectDao() {
        super(sessionFactory);
    }

    @Override
    public Class<Subject> getEntityClass() {
        return Subject.class;
    }
}
