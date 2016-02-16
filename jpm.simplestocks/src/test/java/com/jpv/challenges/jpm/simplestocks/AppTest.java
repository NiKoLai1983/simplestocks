package com.jpv.challenges.jpm.simplestocks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.jpv.challenges.jpm.simplestocks.enums.OperationType;
import com.jpv.challenges.jpm.simplestocks.services.IndexService;
import com.jpv.challenges.jpm.simplestocks.services.StockService;
import com.jpv.challenges.jpm.simplestocks.services.TradeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContextTest.xml" })
@Transactional(propagation=Propagation.REQUIRED)
public class AppTest {
	
	@Autowired
	private TradeService tradeService;
	
	@Autowired
	private StockService stockService;
	
	@Autowired
	private IndexService indexService;
	
		
	@Test
    public void testDividendYield() {		
		Assert.isTrue(stockService.calculateDividendYield("TEA") == 0);
		Assert.isTrue(stockService.calculateDividendYield("GIN") == 0.008355385f);
		Assert.isTrue(stockService.calculateDividendYield("POP") == 0.032653061f);
    }
	
	@Test
	public void testPERatio() {
		Assert.isTrue(stockService.calculatePERatio("TEA") == Float.POSITIVE_INFINITY);
		Assert.isTrue(stockService.calculatePERatio("GIN") == 29.920826f);
	}
	
	@Test
	public void testStockPrice() {
		Assert.isTrue(tradeService.calculateStockPrice("TEA") == 2.393666f);
		Assert.isTrue(tradeService.calculateStockPrice("POP") == 2.45f);
	}
	
	@Test
	public void testGBCEIndex() {
		Assert.isTrue(indexService.calculateGBCEIndex() == 3.0847020835431778);
	}
	
	@Test
	public void testAddingTrades() {
		tradeService.recordTrade("TEA", 4000, OperationType.BUY, 5.74f);
		Assert.isTrue(tradeService.calculateStockPrice("TEA") == 5.0804057f);
	}
	
	
}
