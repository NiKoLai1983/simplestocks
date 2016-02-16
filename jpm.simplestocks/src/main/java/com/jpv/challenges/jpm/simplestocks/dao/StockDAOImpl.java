package com.jpv.challenges.jpm.simplestocks.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.jpv.challenges.jpm.simplestocks.model.Stock;

@Repository("StockDAO")
public class StockDAOImpl implements StockDAO {

	@PersistenceContext
	protected EntityManager em;

	
	public Stock create(Stock entity) {
    	this.em.persist(entity);
        return entity;
    }

    public Stock update(Stock entity) {
        return this.em.merge(entity);
    }

    public void delete(Stock entity) {
        this.em.remove(entity);
    }
    
	public Stock find(long primaryKey) {
		return this.em.find(Stock.class, primaryKey);
	}
	
	public void refresh(Stock entity) {
		Session session = em.unwrap(Session.class);
		session.flush();
		session.refresh(entity);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Stock> getStocks() {
		Query query = em.createQuery(
				"from Stock"
				);
		return (List<Stock>) query.getResultList();
	}
	
	@Override
	public Stock getStockByTicker(String stockTicker) {
		Query query = em.createQuery(
				"from Stock as s where s.symbol = :stockTicker"
				).setParameter("stockTicker", stockTicker);
		
		return (Stock) query.getSingleResult();
	}

}
