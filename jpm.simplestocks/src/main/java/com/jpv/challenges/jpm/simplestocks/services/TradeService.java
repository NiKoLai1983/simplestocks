package com.jpv.challenges.jpm.simplestocks.services;

import com.jpv.challenges.jpm.simplestocks.enums.OperationType;

public interface TradeService {

	void recordTrade(String symbol, long numShares, OperationType type, float price);
	Float calculateStockPrice(String symbol);
	Float getTickerPrice(String symbol);
	
}
