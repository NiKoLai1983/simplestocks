package com.jpv.challenges.jpm.simplestocks.model;

import java.io.Serializable;
import java.util.SortedSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.jpv.challenges.jpm.simplestocks.enums.StockType;

@Entity
@Table(name="STOCKS")
public class Stock implements Serializable {

	private static final long serialVersionUID = -5945155125998291789L;

	@Id
	@SequenceGenerator(name="stocks_seq", sequenceName="TRADE_ID_SEQ", allocationSize=10)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="stocks_seq")
	private long id;
	
	@Column(name="SYMBOL")
	private String symbol;
	
	@Column(name="SYMBOL_TYPE")
	@Enumerated(EnumType.STRING)
	private StockType type;
	
	@Column(name="LAST_DIVIDEND")
	private Float lastDividend;
	
	@Column(name="FIXED_DIVIDEND")
	private Float fixedDividend;
	
	@Column(name="PAR_VALUE")
	private Float parValue;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="STOCK_ID")
	@OrderBy("timestamp")
	private SortedSet<Trade> trades;
	
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public StockType getType() {
		return type;
	}
	public void setType(StockType type) {
		this.type = type;
	}
	public Float getLastDividend() {
		return lastDividend;
	}
	public void setLastDividend(Float lastDividend) {
		this.lastDividend = lastDividend;
	}
	public Float getFixedDividend() {
		return fixedDividend;
	}
	public void setFixedDividend(Float fixedDividend) {
		this.fixedDividend = fixedDividend;
	}
	public Float getParValue() {
		return parValue;
	}
	public void setParValue(Float parValue) {
		this.parValue = parValue;
	}
	public SortedSet<Trade> getTrades() {
		return trades;
	}
	public void setTrades(SortedSet<Trade> trades) {
		this.trades = trades;
	}
	
}
