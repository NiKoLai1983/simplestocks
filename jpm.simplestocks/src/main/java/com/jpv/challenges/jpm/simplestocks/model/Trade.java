package com.jpv.challenges.jpm.simplestocks.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.jpv.challenges.jpm.simplestocks.enums.OperationType;

@Entity
@Table(name="TRADES")
public class Trade implements Serializable {

	private static final long serialVersionUID = 6743175772497601751L;

	@Id
	@SequenceGenerator(name="trades_seq", sequenceName="TRADE_ID_SEQ", allocationSize=10)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="trades_seq")
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="STOCK_ID")
	private Stock stock;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="OP_TIMESTAMP")
	private Date timestamp;
	
	@Column(name="NUM_OF_SHARES")
	private Long numOfShares;
	
	@Column(name="OP_TYPE")
	@Enumerated(EnumType.STRING)
	private OperationType opType;
	
	@Column(name="PRICE")
	private Float price;

	
	@PrePersist
	protected void onCreate() {
		timestamp = new Date();
	}
	
	public Trade() {
		//Default constructor
	}
	
	public Trade(Stock stock, long numShares, OperationType type, float price) {
		setStock(stock);
		setNumOfShares(numShares);
		setOpType(type);
		setPrice(price);
	}
	
	public Stock getStock() {
		return stock;
	}
	
	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Long getNumOfShares() {
		return numOfShares;
	}

	public void setNumOfShares(Long numOfShares) {
		this.numOfShares = numOfShares;
	}

	public OperationType getOpType() {
		return opType;
	}

	public void setOpType(OperationType opType) {
		this.opType = opType;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}
	
}
