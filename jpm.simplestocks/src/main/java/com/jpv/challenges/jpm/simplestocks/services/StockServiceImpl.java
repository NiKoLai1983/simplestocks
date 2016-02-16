package com.jpv.challenges.jpm.simplestocks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jpv.challenges.jpm.simplestocks.dao.StockDAO;
import com.jpv.challenges.jpm.simplestocks.enums.StockType;
import com.jpv.challenges.jpm.simplestocks.model.Stock;

@Service("StockService")
@Transactional(propagation=Propagation.REQUIRED)
public class StockServiceImpl implements StockService {

	@Autowired
	private StockDAO stockDAO;
	
	@Autowired
	private TradeService tradeService;
	
	
	@Override
	public float calculateDividendYield(String symbol) {
		Stock stock = getStockBySymbol(symbol);
		float tickerPrice = tradeService.calculateStockPrice(symbol);
		// Avoid division by zero
		tickerPrice = tickerPrice == 0 ? 0.0000000001f : tickerPrice;
		if (StockType.COMMON.equals(stock.getType())) {
			return stock.getLastDividend()/tickerPrice;
		} else {
			return (stock.getFixedDividend() * stock.getParValue())/tickerPrice;
		}
	}

	@Override
	public float calculatePERatio(String symbol) {
		Stock stock = getStockBySymbol(symbol);
		float tickerPrice = tradeService.calculateStockPrice(symbol);
		// Avoid division by zero
		tickerPrice = tickerPrice == 0 ? 0.0000000001f : tickerPrice;
		return tickerPrice/stock.getLastDividend();
	}
	
	@Override
	public List<Stock> getStocks() {
		return stockDAO.getStocks();
	}
	
	@Override
	public Stock getStockBySymbol(String symbol) {
		return stockDAO.getStockByTicker(symbol);
	}

}
