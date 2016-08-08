package edu.iu.perfin.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;
import edu.iu.perfin.model.Constants;
import edu.iu.perfin.type.IncomeExpense;


@Service
public class ConstantsService {
	
	public IncomeExpense toAssignEnum(String gelenDeger)
	{
		// büyük küçük harfe dikkat et
	IncomeExpense gelirgider = null;
	if (gelenDeger.equals("GELİR")|| gelenDeger.equals("gelir")||gelenDeger.equals("Gelir")) {
		gelirgider = IncomeExpense.INCOME;
	} else if (gelenDeger.equals("GİDER")|| gelenDeger.equals("gider")||gelenDeger.equals("Gider")) {
		gelirgider = IncomeExpense.EXPENSE;
	}
		return gelirgider;
	}
	
	public void add(Constants constants) {
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