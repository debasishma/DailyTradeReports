package com.jpmc.model;

public enum InstructionType {
	BUY("B"), SELL("S");

	private String text;

	InstructionType(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}

	public static InstructionType fromString(String text) {

		if (text != null) {
			for (InstructionType iType : InstructionType.values()) {
				if (text.equalsIgnoreCase(iType.text)) {
					return iType;
				}
			}

			throw new IllegalArgumentException("Instuction type not found for : " + text);
		} else {
			throw new NullPointerException("No data provided for Instruction Type");
		}
	}
}
