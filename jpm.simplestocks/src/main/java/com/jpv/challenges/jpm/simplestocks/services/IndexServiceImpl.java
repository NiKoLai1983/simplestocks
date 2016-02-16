package com.jpv.challenges.jpm.simplestocks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpv.challenges.jpm.simplestocks.model.Stock;

@Service("IndexService")
public class IndexServiceImpl implements IndexService {

	@Autowired
	private StockService stockService;
	
	@Autowired
	private TradeService tradeService;
	
	@Override
	public double calculateGBCEIndex() {
		double productOfStocks = 1;
		
		List<Stock> stocks = stockService.getStocks();
		int numberOfStocks = stocks.size();
		
		for (Stock stock : stocks) {
			Float stockPrice = tradeService.calculateStockPrice(stock.getSymbol());
			// If we cannot get a price, this stock will no be considered
			if (stockPrice == null) {
				numberOfStocks--;
				stockPrice = 1f;
			}
			// Avoid voiding all the result due to a failing stock
			stockPrice = stockPrice == 0 ? 0.0000000001f : stockPrice;
			productOfStocks *= stockPrice;
		}
		
		if (stocks.size() == 0) { return 0.0; }
		double result = Math.pow(productOfStocks, Math.pow(numberOfStocks, -1));
		return result;
	}

}
