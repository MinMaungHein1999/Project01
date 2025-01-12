package org.example.dao;

import org.example.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.example.model.Major;
public class MajorDao extends AbstractDao<Major>{
    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public MajorDao() {
        super(sessionFactory);
    }

    @Override
    public Class<Major> getEntityClass() {
        return Major.class;
    }
}
