package edu.iu.perfin.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.avaje.ebean.Expr;

import edu.iu.perfin.model.BankCard;
import edu.iu.perfin.model.Constants;
import edu.iu.perfin.model.RecordIncomeExpense;
import edu.iu.perfin.model.User;
import edu.iu.perfin.service.GeneralService;
import edu.iu.perfin.service.RecordService;
import edu.iu.perfin.type.IncomeExpense;
import edu.iu.perfin.type.PayloadType;

@Controller
@RequestMapping(value = "record")
// denemesi yapılmadı
public class RecordController {

	@Autowired
	RecordService recordservice;

	@RequestMapping(value = "/display", method = RequestMethod.POST)

	public @ResponseBody Map<String, String> recorddisplay(@RequestBody Map<String, String> map) {
		Map<String, String> recordMap = new HashMap<String, String>();
		recordMap.put("recdate", map.get("recdate"));
		recordMap.put("descript", map.get("descript"));
		recordMap.put("amount", map.get("amount"));
		recordMap.put("incomeExpense", map.get("incomeExpense"));
		recordMap.put("payloadType", map.get("payloadType"));

		String userid = map.get("mainuserid");
		User usr = GeneralService.getFirstByColumn(User.class, Expr.eq("username", userid));
		recordMap.put("mainuserid", map.get("mainuserid"));

		String cardNumber = map.get("cardNumber");
		BankCard bnkid = GeneralService.getFirstByColumn(BankCard.class, Expr.eq("cardNumber", cardNumber));
		recordMap.put("cardNumber", map.get("cardNumber"));

		String asuserid = map.get("assignUserId");
		User asuser = GeneralService.getFirstByColumn(User.class, Expr.eq("username", asuserid));
		recordMap.put("assignUserId", map.get("usrId"));

		String constId = map.get("constId");
		Constants consid = GeneralService.getFirstByColumn(Constants.class, Expr.eq("categories", constId));
		recordMap.put("constId", map.get("constId"));

		return recordMap;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> recordekle(@RequestBody Map<String, String> map) {
		Map<String, Object> recordmap = new HashMap<String, Object>();
		RecordIncomeExpense record = new RecordIncomeExpense();

		String gelenDeger = map.get("incomeExpense");
		IncomeExpense gelirgider = null;
		if (gelenDeger.equals("GELİR")) {
			gelirgider = IncomeExpense.INCOME;
		} else if (gelenDeger.equals("GİDER")) {
			gelirgider = IncomeExpense.EXPENSE;
		} else if (gelenDeger.equals("DEVİR")) {
			gelirgider = IncomeExpense.ASSIGN;
		} else if (gelenDeger.equals("DEVİR KAPAMA")) {
			gelirgider = IncomeExpense.ASSIGN;
		}
		record.setIncomeExpense(gelirgider);

		record.setDescript(map.get("descript"));
		record.setDate(map.get("recdate"));

		String toStringamount = (map.get("amount"));
		BigDecimal toBigDecimalamount = new BigDecimal(toStringamount);
		record.setAmount(toBigDecimalamount);

		String fromUserid = map.get("mainuserid");
		User toGetusrId = GeneralService.getFirstByColumn(User.class, Expr.eq("username", fromUserid));
		record.setMainuserid(toGetusrId);

		String fromAssignid = map.get("assignUserid");
		User toGetAssignId = GeneralService.getFirstByColumn(User.class, Expr.eq("username", fromAssignid));
		record.setAssignUserId(toGetAssignId);

		String fromPayType = map.get("PayType");
		PayloadType payType = null;
		if (fromPayType.equals("Credit Card")) {
			payType = PayloadType.CreditCard;
		} else if (fromPayType.equals("Debit Card")) {
			payType = PayloadType.DebitCard;
		} else if (fromPayType.equals("Cash")) {
			payType = PayloadType.Cash;
		} else if (fromPayType.equals("Overdraft")) {
			payType = PayloadType.OverdraftAcc;
		}
		record.setPayloadType(payType);

		String fromCategory = map.get("CATEGORYTYPE");
		Constants category = GeneralService.getFirstByColumn(Constants.class, Expr.eq("constID", fromCategory));
		record.setConstId(category);

		if(fromPayType.equals("Cash"))
		{	record.setBankid(null);
			throw new RuntimeException("You choose cash. You cannot enter the bank.");
			}	
		
		String toGetBank = map.get("bankident");
		BankCard banks = GeneralService.getFirstByColumn(BankCard.class, Expr.eq("bankID", toGetBank));
		record.setBankid(banks);

		recordservice.add(record);
		recordmap.put("record", record);
		//recordmap.put("User", toGetusrId);
		//recordmap.put("Assign", toGetAssignId);
		//recordmap.put("category", category);
		//recordmap.put("bank", banks);
		
		return recordmap;
	}

	@RequestMapping(value = "/all", method = RequestMethod.POST)
	public @ResponseBody List<RecordIncomeExpense> getAll() {
		return recordservice.getAll();
	}
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody RecordIncomeExpense delete(@RequestBody Map<String, String> maps) {
		String descript = maps.get("descript"); // coddan çekiyo
		RecordIncomeExpense rect = recordservice.get("descript", descript); // DB den çekiyor
		if (rect == null)
			throw new RuntimeException("No record can be deleted");
		recordservice.delete(rect);
		return rect;
	}
}