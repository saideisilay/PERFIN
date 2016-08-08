package edu.iu.perfin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;

import edu.iu.perfin.model.RecordIncomeExpense;
import edu.iu.perfin.type.PayloadType;


@Service
public class RecordService {
	
	public PayloadType toAssignPayload(String comingValue){
		PayloadType payType = null;
		if (comingValue.equals("Kredi Kartı") || comingValue.equals("kredi kartı")
				|| comingValue.equals("kredi kart")) {
			payType = PayloadType.CreditCard;
		} else if (comingValue.equals("Debit kart") || comingValue.equals("maaş kartı")
				|| comingValue.equals("Banka kartı")) {
			payType = PayloadType.DebitCard;
		} else if (comingValue.equals("Nakit") || comingValue.equals("nakit")) {
			payType = PayloadType.Cash;
		}
		return payType;
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
