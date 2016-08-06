package edu.iu.perfin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;

import edu.iu.perfin.model.RecordIncomeExpense;
import edu.iu.perfin.type.IncomeExpense;;

@Service
public class RecordService {

	IncomeExpense toAssignEnum(String gelenDeger)
	{
	IncomeExpense gelirgider = null;
	if (gelenDeger.equals("GELİR")) {
		gelirgider = IncomeExpense.INCOME;
	} else if (gelenDeger.equals("GİDER")) {
		gelirgider = IncomeExpense.EXPENSE;
	} else if (gelenDeger.equals("DEVİR")) {
		gelirgider = IncomeExpense.ASSIGN;
	} else if (gelenDeger.equals("DEVİR KAPAMA")) {
		gelirgider = IncomeExpense.ASSIGN;}
		return gelirgider;
	}
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
