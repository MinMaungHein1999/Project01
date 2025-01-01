package org.example.dao;

import org.example.model.Student;
import org.example.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class StudentDao extends AbstractDao<Student>{
    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public StudentDao() {
        super(sessionFactory);
    }

    @Override
    public Class<Student> getEntityClass() {
        return Student.class;
    }
}

