package com.dit.app.demo.deposit.dao.impl;

import com.dit.app.demo.deposit.dao.QuantityLedgerDao;
import com.dit.app.demo.deposit.model.QuantityLedger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.ZonedDateTime;
import java.util.List;

@Repository
public class QuantityLedgerDaoImpl implements QuantityLedgerDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<QuantityLedger> getQuantityLedgerList(String warehouseCode, String warehouseName, String clientId, String clientName, String commodityCode, String commodityName, ZonedDateTime startDate, ZonedDateTime endDate) {
        List<QuantityLedger> quantityLedgerList = null;
        List<String> warehouseList = null;
        List<String> commodityList = null;
        List<String> clientList = null;

        Session session = sessionFactory.openSession();
        EntityManager em = sessionFactory.createEntityManager();
        try {
            if(warehouseCode==null && warehouseName!=null) {
                Query query = em.createQuery("Select distinct d.warehouseCode from Deposit d where d.warehouseName LIKE '%:name%'");
                query.setParameter("name", warehouseName);
                warehouseList = query.getResultList();
            }

            if(clientId==null && clientName!=null) {
                Query query = em.createQuery("Select distinct d.clientId from Deposit d where d.clientName LIKE '%:name%'");
                query.setParameter("name", clientName);
                clientList = query.getResultList();
            }

            if(commodityCode==null && commodityName!=null) {
                Query query = em.createQuery("Select distinct d.commodityCode from Deposit d where d.commodityName LIKE '%:name%'");
                query.setParameter("name", commodityName);
                commodityList = query.getResultList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
