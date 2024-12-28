package org.example.dao;

import org.example.model.Student;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.SQLException;
import java.util.List;

public class StudentDao {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public List<Student> getAll() throws SQLException {
      return null;
    }

    public void create(Student stu) throws SQLException{
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(stu);
        session.getTransaction().commit();
        session.close();
        HibernateUtil.shutdown();
    }
}

