package com.jpmc.input;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

import com.jpmc.model.Instruction;
import com.jpmc.model.InstructionData;
import com.jpmc.model.InstructionType;

public class SampleInput {
	public static Set<Instruction> getSampleInput() {
		// Set of Instructions for input to system
		return new HashSet<>(Arrays.asList(

				new Instruction("JPMC", InstructionType.BUY, LocalDate.of(2019, 10, 12), LocalDate.of(2019, 10, 13),
						new InstructionData(Currency.getInstance("USD"), BigDecimal.valueOf(1), 200,
								BigDecimal.valueOf(100.25))),

				new Instruction("Barclays", InstructionType.BUY, LocalDate.of(2019, 10, 10), LocalDate.of(2019, 10, 12),
						new InstructionData(Currency.getInstance("GBP"), BigDecimal.valueOf(1.12), 450,
								BigDecimal.valueOf(150.5))),

				new Instruction("Amazon", InstructionType.SELL, LocalDate.of(2018, 3, 9), LocalDate.of(2018, 3, 17),
						new InstructionData(Currency.getInstance("SAR"), BigDecimal.valueOf(0.27), 150,
								BigDecimal.valueOf(400.8))),

				new Instruction("Google", InstructionType.SELL, LocalDate.of(2019, 5, 9), LocalDate.of(2019, 5, 20),
						new InstructionData(Currency.getInstance("EUR"), BigDecimal.valueOf(0.34), 50,
								BigDecimal.valueOf(500.6))),

				new Instruction("UAEBank", InstructionType.BUY, LocalDate.of(2017, 1, 4), LocalDate.of(2017, 1, 06),
						new InstructionData(Currency.getInstance("AED"), BigDecimal.valueOf(0.22), 450,
								BigDecimal.valueOf(150.5))),

				new Instruction("SingFed", InstructionType.BUY, LocalDate.of(2016, 1, 1), LocalDate.of(2016, 1, 02),
						new InstructionData(Currency.getInstance("SGD"), BigDecimal.valueOf(0.50), 200,
								BigDecimal.valueOf(100.25))),

				new Instruction("HSBC", InstructionType.SELL, LocalDate.of(2018, 10, 12), LocalDate.of(2018, 10, 13),
						new InstructionData(Currency.getInstance("EUR"), BigDecimal.valueOf(0.34), 1000,
								BigDecimal.valueOf(210.6))),

				new Instruction("StanC", InstructionType.SELL, LocalDate.of(2018, 10, 6), LocalDate.of(2018, 10, 13),
						new InstructionData(Currency.getInstance("EUR"), BigDecimal.valueOf(0.34), 120,
								BigDecimal.valueOf(339.5)))));
	}
}
