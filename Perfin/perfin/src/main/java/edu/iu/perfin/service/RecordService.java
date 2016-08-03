package edu.iu.perfin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;

import edu.iu.perfin.model.RecordIncomeExpense;;

@Service
public class RecordService {

	public void add(RecordIncomeExpense rec) {
		Ebean.save(rec);
	}

	public void delete(RecordIncomeExpense rec) {
		Ebean.delete(rec);
	}

	public void update(RecordIncomeExpense rec) {
		Ebean.update(rec);
	}

	public RecordIncomeExpense get(String columnName, Object value) {
		return GeneralService.getFirstByColumn(RecordIncomeExpense.class, Expr.eq(columnName, value));
	}

	public List<RecordIncomeExpense> getAll() {
		return GeneralService.loadAll(RecordIncomeExpense.class);
	}

}
