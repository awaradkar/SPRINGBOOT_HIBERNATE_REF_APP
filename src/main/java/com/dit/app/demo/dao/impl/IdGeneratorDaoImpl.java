package com.dit.app.demo.dao.impl;

import com.dit.app.demo.dao.IdGeneratorDao;
import com.dit.app.demo.model.IdGenerator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Repository
public class IdGeneratorDaoImpl implements IdGeneratorDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public IdGenerator getNewId(String key) {
        Session session = sessionFactory.openSession();
        IdGenerator idGenerator = null;
        Transaction tx = null;
        try {

            idGenerator = session.get(IdGenerator.class, key);
            if(idGenerator==null){
                idGenerator = new IdGenerator();
                idGenerator.setIdKey(key);
                idGenerator.setIdValue(new BigDecimal(1));
                idGenerator.setCreatedDate(ZonedDateTime.now());
                idGenerator.setCreatedBy("");

                tx = session.getTransaction();
                tx.begin();
                session.save(idGenerator);
                tx.commit();
            }else{
                idGenerator.setIdValue(idGenerator.getIdValue().add(new BigDecimal(1)));
                idGenerator.setModifiedDate(ZonedDateTime.now());
                idGenerator.setModifiedBy("");

                tx = session.getTransaction();
                tx.begin();
                session.saveOrUpdate(idGenerator);
                tx.commit();
            }

        } catch (HibernateException h) {
            System.out.println(h.getMessage());
        } catch (Exception h) {
            System.out.println(h.getMessage());
        } finally {
            session.close();
        }
        return idGenerator;
    }
}
