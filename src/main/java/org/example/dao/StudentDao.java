package org.example.dao;

import org.example.database.PgSqlConnectionFactory;
import org.example.model.Student;
import org.example.util.HibernateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
        HibernateUtil.closeSession();
    }
}

