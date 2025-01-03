package org.example.dao;

import org.example.model.Student;
import org.example.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class StudentDao extends AbstractDao<Student> {
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public StudentDao() {
        super(sessionFactory);
    }

    @Override
    public Student findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Student student = session.get(getEntityClass(), id);
            if (student != null) {
                Hibernate.initialize(student.getSubjects());
            }
            return student;
        } catch (Exception e) {
            System.out.println("Error finding Student with ID: {}"+ id+ e.getMessage());
            return null;
        }
    }

    @Override
    public Class<Student> getEntityClass() {
        return Student.class;
    }
}
