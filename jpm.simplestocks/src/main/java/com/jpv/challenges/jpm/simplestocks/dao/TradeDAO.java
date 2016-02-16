package com.jpv.challenges.jpm.simplestocks.dao;

import java.util.Date;
import java.util.List;

import com.jpv.challenges.jpm.simplestocks.model.Trade;

public interface TradeDAO {
	
	Trade create(Trade entity);
    Trade update(Trade entity);
    void delete(Trade entity);
    Trade find(long primaryKey);
	public void refresh(Trade entity);
	List<Trade> getTradesAfterTimestamp(String symbol, Date limit);
	Trade getLastTrade(String symbol);
	
}
