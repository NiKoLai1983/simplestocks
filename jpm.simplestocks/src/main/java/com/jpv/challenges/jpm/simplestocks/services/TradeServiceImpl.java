package com.jpv.challenges.jpm.simplestocks.services;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jpv.challenges.jpm.simplestocks.dao.TradeDAO;
import com.jpv.challenges.jpm.simplestocks.enums.OperationType;
import com.jpv.challenges.jpm.simplestocks.model.Stock;
import com.jpv.challenges.jpm.simplestocks.model.Trade;

@Service("TradeService")
@Transactional(propagation=Propagation.REQUIRED)
public class TradeServiceImpl implements TradeService {
	
	@Autowired
	private TradeDAO tradeDAO;
	
	@Autowired
	private StockService stockService;
	
	
	@Override
	public void recordTrade(String symbol, long numShares, OperationType type, float price) {
		
		Stock stock = stockService.getStockBySymbol(symbol);
		Trade trade = new Trade(stock, numShares, type, price);
		tradeDAO.create(trade);
	}
	
	@Override
	public Float calculateStockPrice(String symbol) {
		
		long now = System.currentTimeMillis();
		long nowMinus15Min = now - (15L * 60L * 1000L);
		Date nowMinus15MinDate = new Date(nowMinus15Min);

		List<Trade> tradesLast15Min =
				tradeDAO.getTradesAfterTimestamp(symbol, nowMinus15MinDate);
		
		float tradePriceAndQuantity = 0.0f;
		float quantity = 0.0f;
		
		for (Trade trade : tradesLast15Min) {
			tradePriceAndQuantity += trade.getPrice() * trade.getNumOfShares();
			quantity += trade.getNumOfShares();
		}
		
		// If it is 0 there has been no trades in the last 15 min
		// We return the last ticker price
		if (quantity == 0) {
			return getTickerPrice(symbol);
		} else {
			return tradePriceAndQuantity/quantity;
		}
	}

	@Override
	public Float getTickerPrice(String symbol) {
		Trade trade = null;
		try {
			trade = tradeDAO.getLastTrade(symbol);
		} catch (NoResultException nre) {
			return null;
		}
		return trade.getPrice();
	}

}
