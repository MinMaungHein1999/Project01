package org.example.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public abstract class AbstractDao<T>{

    private final SessionFactory sessionFactory;
    public abstract Class<T> getEntityClass();

    public AbstractDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public T findById(int id){
        Session session = sessionFactory.openSession();
        try {
            T entity= session.get(getEntityClass(), id);
            return entity;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }

    public List<T> getAll(){
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("FROM "+this.getEntityClass().getSimpleName(), this.getEntityClass()).list();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }

    public void delete(int id){
        executeCommonTransaction(session -> {
            T entity = this.findById(id);
            if(entity != null){
                session.delete(entity);
            }
        });
    }

    public void create(T entity){
        saveOrUpdate(entity);
    }

    public void update(T entity){
        this.saveOrUpdate(entity);
    }

    private void saveOrUpdate(T entity){
        executeCommonTransaction(session -> {
            session.saveOrUpdate(entity);
        });
    }

    private void executeCommonTransaction(TransactionalAction action){
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            action.execute(session);
            transaction.commit();
        }catch (Exception e){
            if(transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }

    }

    @FunctionalInterface
    private interface TransactionalAction {
        void execute(Session session);
    }


}
