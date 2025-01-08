package org.example.dao;

import org.example.model.ScholarStudent;
import org.example.model.Student;
import org.example.model.Subject;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class StudentDao extends AbstractDao<Student> {
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private final ScholarStudentDao scholarStudentDao =  new ScholarStudentDao();

    public StudentDao() {
        super(sessionFactory);
    }

    public List<Subject> fetchSubjectsById(int studentId){
        String hql = "SELECT e.subject FROM Enrollment e WHERE e.student.id = :studentId";
        Session session = null;
        List<Subject> subjects = new ArrayList<>();
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Query<Subject> query = session.createQuery(hql, Subject.class);
            query.setParameter("studentId", studentId);
            subjects = query.getResultList();
            session.getTransaction().commit();
            session.close();
        }catch (Exception e){
            if(session != null && session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }finally {
            if(session != null){
                session.close();
            }
        }
        return subjects;
    }

    @Override
    public Student findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Student student = session.get(getEntityClass(), id);
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
