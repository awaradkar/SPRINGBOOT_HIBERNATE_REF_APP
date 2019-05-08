package com.dit.app.demo.deposit.dao.impl;

import com.dit.app.demo.deposit.dao.WithdrawalDao;
import com.dit.app.demo.deposit.model.Withdrawal;
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
public class WithdrawalDaoImpl implements WithdrawalDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Withdrawal> getWithDrawalList(String withdrawalId, String depositId, String warehouseCode, String warehouseName, String clientId, String clientName, String commodityCode, String commodityName, ZonedDateTime startDate, ZonedDateTime endDate) {
        List<Withdrawal> withdrawalList = null;

        Session session = sessionFactory.openSession();
        try {
            // create Criteria
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Withdrawal> criteriaQuery = builder.createQuery(Withdrawal.class);
            Root<Withdrawal> critRoot = criteriaQuery.from(Withdrawal.class);
            criteriaQuery.select(critRoot);
            // you have to add more predicates when you want more parameters
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (withdrawalId != null) {
                predicates.add(builder.equal(critRoot.get("withdrawalId"), withdrawalId));
            }
            if (depositId != null) {
                predicates.add(builder.equal(critRoot.get("depositId"), depositId));
            }
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
                predicates.add(builder.greaterThanOrEqualTo(critRoot.<ZonedDateTime>get("withdrawalDate"), startDate));
            }
            if(endDate!=null || "".equals(endDate)){
                predicates.add(builder.lessThanOrEqualTo(critRoot.<ZonedDateTime>get("withdrawalDate"), endDate));
            }

            predicates.add(builder.equal(critRoot.get("active"), "Y"));
            criteriaQuery.where(predicates.toArray(new Predicate[0]));
            withdrawalList = session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return withdrawalList;
    }

    @Override
    public Withdrawal getWithdrawal(String id) {
        Session session = sessionFactory.openSession();
        Withdrawal withdrawal = null;
        try {

            withdrawal = session.get(Withdrawal.class, id);
        } catch (HibernateException h) {
            System.out.println(h.getMessage());
        } catch (Exception h) {
            System.out.println(h.getMessage());
        } finally {
            session.close();
        }
        return withdrawal;
    }

    @Override
    public Withdrawal saveWithDrawal(Withdrawal withdrawal) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.getTransaction();
            tx.begin();
            session.save(withdrawal);
            tx.commit();
        } catch (HibernateException h) {
            tx.rollback();
            System.out.println(h.getMessage());
        } catch (Exception h) {
            tx.rollback();
            h.printStackTrace();
            System.out.println(h.getMessage());
        } finally {
            session.close();
        }
        return withdrawal;
    }

    @Override
    public Withdrawal updateWithDrawal(Withdrawal withdrawal) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.getTransaction();
            tx.begin();
            session.saveOrUpdate(withdrawal);
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
        return withdrawal;
    }

    @Override
    public void deleteWithdrawal(String id) throws EntityNotFoundException {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.getTransaction();
            tx.begin();
            Withdrawal withdrawal = (Withdrawal) session.load(Withdrawal.class, id);
            if (withdrawal != null) {
                session.delete(withdrawal);
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
