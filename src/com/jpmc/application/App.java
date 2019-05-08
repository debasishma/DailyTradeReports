package com.jpmc.application;

import java.util.Set;

import com.jpmc.input.SampleInput;
import com.jpmc.model.Instruction;
import com.jpmc.report.TradeReport;
import com.jpmc.report.TradeReportImpl;

public class App {

	public static void main(String[] args) {
		final Set<Instruction> instructions = SampleInput.getSampleInput();
		final TradeReport tradeReport = new TradeReportImpl();

		System.out.println(tradeReport.createTradeReport(instructions));
	}
}
