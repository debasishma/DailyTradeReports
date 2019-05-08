package com.jpmc.report;

import java.util.Set;

import com.jpmc.model.Instruction;

public interface TradeReport {
	String createTradeReport(Set<Instruction> instructions);
}
