package com.jpmc.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

/**
 * Instruction definition for a trade received
 */
public class Instruction {

	// Entity name of sender
	private final String entity;

	// Type of trade BUY / SELL
	private final InstructionType instructionType;

	// Date of receipt
	private final LocalDate instructionDate;

	// Date of trade execution NON final for modifications
	private LocalDate settlementDate;

	private final InstructionData instructionData;

	public Instruction(String entity, InstructionType instructionType, LocalDate instructionDate,
			LocalDate settlementDate, InstructionData instructionData) {
		this.entity = entity;
		this.instructionType = instructionType;
		this.instructionDate = instructionDate;
		this.settlementDate = settlementDate;
		this.instructionData = instructionData;
	}

	public String getEntity() {
		return entity;
	}

	public InstructionType getInstructionType() {
		return instructionType;
	}

	public LocalDate getInstructionDate() {
		return instructionDate;
	}

	public void setSettlementDate(LocalDate newDate) {
		settlementDate = newDate;
	}

	public LocalDate getSettlementDate() {
		return settlementDate;
	}

	public InstructionData getInstructionData() {
		return instructionData;
	}

	public Currency getCurrency() {
		return getInstructionData().getCurrency();
	}

	public BigDecimal getFxRate() {
		return getInstructionData().getFxrate();
	}

	public int getUnits() {
		return getInstructionData().getUnits();
	}

	public BigDecimal getPricePerUnit() {
		return getInstructionData().getPricePerUnit();
	}

	public BigDecimal getTradeAmount() {
		return getInstructionData().getTradeAmount().setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}

	@Override
	public String toString() {
		return entity;
	}
}
