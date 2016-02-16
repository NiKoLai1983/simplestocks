package com.jpv.challenges.jpm.simplestocks.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.jpv.challenges.jpm.simplestocks.model.Trade;

@Repository("TradeDAO")
public class TradeDAOImpl implements TradeDAO {

	@PersistenceContext
	protected EntityManager em;

	
	public Trade create(Trade entity) {
    	this.em.persist(entity);
        return entity;
    }

    public Trade update(Trade entity) {
        return this.em.merge(entity);
    }

    public void delete(Trade entity) {
        this.em.remove(entity);
    }
    
	public Trade find(long primaryKey) {
		return this.em.find(Trade.class, primaryKey);
	}
	
	public void refresh(Trade entity) {
		Session session = em.unwrap(Session.class);
		session.flush();
		session.refresh(entity);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Trade> getTradesAfterTimestamp(String symbol, Date limit) {
		Query query = em.createQuery(
				"select tr from Trade as tr "
				+ "join tr.stock as st "
				+ "where tr.timestamp > :limit and st.symbol = :symbol "
				+ "order by tr.timestamp"
				);
		query.setParameter("limit", limit);
		query.setParameter("symbol", symbol);
		List<Trade> results = query.getResultList();
		return results;
	}

	@Override
	public Trade getLastTrade(String symbol) {
		Query query = em.createQuery(
				"select tr from Trade as tr "
				+ "join tr.stock as st "
				+ "where st.symbol = :symbol "
				+ "order by tr.timestamp"
				);
		query.setParameter("symbol", symbol);
		query.setMaxResults(1);
		Trade result = (Trade) query.getSingleResult();
		return result;
	}
	
}
