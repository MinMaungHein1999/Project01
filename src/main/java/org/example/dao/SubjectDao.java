package org.example.dao;

import org.example.model.Subject;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
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

    public Subject findByCode(String code){
        Session session = sessionFactory.openSession();
        try {
            String hql = "FROM Subject WHERE code = :code";
            Subject subject =  session.createQuery(hql, Subject.class)
                    .setParameter("code", code)
                    .uniqueResult();
            return subject;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }

    public void deleteByCode(String code) {
        executeCommonTransaction(session -> {
            Subject subject = this.findByCode(code);
            if(subject!=null){
                session.delete(subject);
            }
        });
    }
}
