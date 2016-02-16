package com.jpv.challenges.jpm.simplestocks.dao;

import java.util.List;

import com.jpv.challenges.jpm.simplestocks.model.Stock;

public interface StockDAO {

	Stock create(Stock entity);
	Stock update(Stock entity);
    void delete(Stock entity);
    Stock find(long primaryKey);
	public void refresh(Stock entity);
	Stock getStockByTicker(String stockTicker);
	List<Stock> getStocks();
	
}
