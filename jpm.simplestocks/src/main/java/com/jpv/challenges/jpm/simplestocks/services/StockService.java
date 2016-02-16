package com.jpv.challenges.jpm.simplestocks.services;

import java.util.List;

import com.jpv.challenges.jpm.simplestocks.model.Stock;

public interface StockService {

	float calculateDividendYield(String symbol);
	float calculatePERatio(String symbol);
	List<Stock> getStocks();
	Stock getStockBySymbol(String symbol);
	
}
