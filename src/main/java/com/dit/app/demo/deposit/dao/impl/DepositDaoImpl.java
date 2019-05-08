package com.dit.app.demo.deposit.dao.impl;

import com.dit.app.demo.deposit.dao.DepositDao;
import com.dit.app.demo.deposit.model.Deposit;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DepositDaoImpl implements DepositDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Deposit> getDepositList(String warehouseCode, String warehouseName, String clientId, String clientName, String commodityCode, String commodityName, ZonedDateTime startDate, ZonedDateTime endDate, String status) {
        List<Deposit> depositList = null;

        Session session = sessionFactory.openSession();
        try {
            // create Criteria
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Deposit> criteriaQuery = builder.createQuery(Deposit.class);
            Root<Deposit> critRoot = criteriaQuery.from(Deposit.class);
            criteriaQuery.select(critRoot);
            // you have to add more predicates when you want more parameters
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (warehouseCode != null) {
                predicates.add(builder.equal(critRoot.get("warehouseCode"), warehouseCode));
            }
            if (warehouseName != null) {
                predicates.add(builder.like(critRoot.get("warehouseName"), "%"+warehouseName+"%"));
            }
            if (clientId != null) {
                predicates.add(builder.equal(critRoot.get("clientId"), clientId));
            }
            if (clientName != null) {
                predicates.add(builder.like(critRoot.get("clientName"), "%"+clientName+"%"));
            }
            if (commodityCode != null) {
                predicates.add(builder.equal(critRoot.get("commodityCode"), commodityCode));
            }
            if (commodityName != null) {
                predicates.add(builder.like(critRoot.get("commodityName"), "%"+commodityName+"%"));
            }
            if (startDate!=null || "".equals(startDate)){
                predicates.add(builder.greaterThanOrEqualTo(critRoot.<ZonedDateTime>get("depositDate"), startDate));
            }
            if(endDate!=null || "".equals(endDate)){
                predicates.add(builder.lessThanOrEqualTo(critRoot.<ZonedDateTime>get("depositDate"), endDate));
            }

            predicates.add(builder.equal(critRoot.get("active"), "Y"));

            if (status != null) {
                predicates.add(builder.equal(critRoot.get("status"), status));
            }

            criteriaQuery.where(predicates.toArray(new Predicate[0]));
            depositList = session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return depositList;
    }

    @Override
    public Deposit getDeposit(String id) {
        Session session = sessionFactory.openSession();
        Deposit deposit = null;
        try {

            deposit = session.get(Deposit.class, id);
        } catch (HibernateException h) {
            System.out.println(h.getMessage());
        } catch (Exception h) {
            System.out.println(h.getMessage());
        } finally {
            session.close();
        }
        return deposit;
    }

    @Override
    public Deposit saveDeposit(Deposit deposit) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.getTransaction();
            tx.begin();
            session.save(deposit);
            tx.commit();
        } catch (HibernateException h) {
            tx.rollback();
            h.printStackTrace();
            System.out.println(h.getMessage());
        } catch (Exception h) {
            tx.rollback();
            h.printStackTrace();
            System.out.println(h.getMessage());
        } finally {
            session.close();
        }
        return deposit;
    }

    @Override
    public Deposit updateDeposit(Deposit deposit) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.getTransaction();
            tx.begin();
            session.saveOrUpdate(deposit);
            tx.commit();
        } catch (HibernateException h) {
            tx.rollback();
            System.out.println(h.getMessage());
        } catch (Exception h) {
            tx.rollback();
            System.out.println(h.getMessage());
        } finally {
            session.close();
        }
        return deposit;
    }

    @Override
    public void deleteDeposit(String id) throws EntityNotFoundException {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.getTransaction();
            tx.begin();
            Deposit deposit = (Deposit) session.load(Deposit.class, id);
            if (deposit != null) {
                session.delete(deposit);
                tx.commit();
            } else {
                throw new EntityNotFoundException(id);
            }

        } catch (HibernateException h) {
            tx.rollback();
            System.out.println(h.getMessage());
        } catch (Exception h) {
            tx.rollback();
            System.out.println(h.getMessage());
        } finally {
            session.close();
        }
    }
}
