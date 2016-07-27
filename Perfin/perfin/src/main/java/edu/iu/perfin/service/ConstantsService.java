package edu.iu.perfin.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;
import edu.iu.perfin.model.Constants;
import edu.iu.perfin.type.IncomeExpense;

@Service
public class ConstantsService {

	// private static final IncomeExpense IncomeExpense = null;

	public String choose(IncomeExpense chs) {
		String ch = "";

		if (chs == edu.iu.perfin.type.IncomeExpense.INCOME) {
			ch = edu.iu.perfin.type.IncomeExpense.INCOME.getGtype();
		} else if (chs == edu.iu.perfin.type.IncomeExpense.EXPENSE) {
			ch = edu.iu.perfin.type.IncomeExpense.EXPENSE.getGtype();
		} else if (chs == edu.iu.perfin.type.IncomeExpense.ASSIGN) {
			ch = edu.iu.perfin.type.IncomeExpense.ASSIGN.getGtype();
		} else {
			ch = edu.iu.perfin.type.IncomeExpense.OUTSTAND.getGtype();
		}
		return ch;
	}

	public void add(Constants constants) {
		String ch =choose(get("categories",))	// BU KISIMLARI YAPAMADIM
		
		constants.setCategories("ch");
		Ebean.save(constants);
	}

	public Constants get(String columnName, Object value) {
		return GeneralService.getFirstByColumn(Constants.class, Expr.eq(columnName, value));
	}

	public void delete(Constants cons) {
		Ebean.delete(cons);
	}

	public void update(Constants cons) {
		Ebean.update(cons);
	}

	public List<Constants> getAll() {
		return GeneralService.loadAll(Constants.class);
	}
}