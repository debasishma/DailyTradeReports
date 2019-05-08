package com.jpmc.model;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Detail of Instuction received
 */
public class InstructionData {

	private final Currency currency; // currency of instruction

	private final BigDecimal fxRate; // fx rate w.r.t USD

	private final int units;

	private final BigDecimal pricePerUnit;

	// total amount of the trade
	private final BigDecimal tradeAmount;

	public InstructionData(Currency currency, BigDecimal fxRate, int units, BigDecimal pricePerUnit) {
		this.currency = currency;
		this.fxRate = fxRate;
		this.units = units;
		this.pricePerUnit = pricePerUnit;
		this.tradeAmount = findTradeAmount(this);
	}

	/**
	 * Calculate the trade amount based on ( pricePerUnit x units x fxRate )
	 */
	private static BigDecimal findTradeAmount(InstructionData ins) {
		return ins.getPricePerUnit().multiply(BigDecimal.valueOf(ins.getUnits())).multiply(ins.getFxrate());
	}

	public BigDecimal getFxrate() {
		return fxRate;
	}

	public int getUnits() {
		return units;
	}

	public BigDecimal getPricePerUnit() {
		return pricePerUnit;
	}

	public BigDecimal getTradeAmount() {
		return tradeAmount;
	}

	public Currency getCurrency() {
		return currency;
	}
}
