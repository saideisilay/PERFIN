package edu.iu.perfin.type;

public enum IncomeExpense {
	INCOME("GELİR"), EXPENSE("GİDER"), ASSIGN("DEVİR"), OUTSTAND("DEVİR KAPAMA");

	private String gtype;

	private IncomeExpense(String gtype) {
		this.gtype = gtype;
	}

	public String getGtype() {
		return this.gtype;
	}
}