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
import edu.iu.perfin.service.ConstantsService;
import edu.iu.perfin.service.GeneralService;
import edu.iu.perfin.service.RecordService;
import edu.iu.perfin.service.UserService;
import edu.iu.perfin.type.IncomeExpense;
import edu.iu.perfin.type.PayloadType;

@Controller
@RequestMapping(value = "record")

public class RecordController {

	@Autowired
	RecordService recordservice;

	@Autowired
	ConstantsService consservice;

	@Autowired
	UserService userservice;

	@RequestMapping(value = "/display", method = RequestMethod.POST)

	public @ResponseBody Map<String, String> recorddisplay(@RequestBody Map<String, String> map) {
		Map<String, String> recordMap = new HashMap<String, String>();
		recordMap.put("recdate", map.get("recdate"));
		recordMap.put("descript", map.get("descript"));
		recordMap.put("amount", map.get("amount"));
		IncomeExpense incExp = consservice.toAssignEnum(map.get("incomeExpense"));
		String gelgit = incExp.toString();
		recordMap.put("incomeExpense", gelgit);

		PayloadType comeinPay = recordservice.toAssignPayload(map.get("payloadType"));
		String payInfo = comeinPay.toString();
		recordMap.put("payloadType", payInfo);

		recordMap.put("mainuserid", map.get("mainuserid"));
		recordMap.put("banks", map.get("banks"));
		recordMap.put("constId", map.get("constId"));

		return recordMap;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> recordekle(@RequestBody Map<String, String> map) {
		Map<String, Object> recordmap = new HashMap<String, Object>();
		RecordIncomeExpense record = new RecordIncomeExpense();

		IncomeExpense gelirgider = consservice.toAssignEnum(map.get("incomeExpense"));
		record.setIncomeExpense(gelirgider);

		record.setDescript(map.get("descript"));
		record.setDate(map.get("recdate"));

		String toStringamount = (map.get("amount"));
		BigDecimal toBigDecimalamount = new BigDecimal(toStringamount);
		record.setAmount(toBigDecimalamount);

		String fromUserid = map.get("mainuserid");
		User toGetusrId = GeneralService.getFirstByColumn(User.class, Expr.eq("username", fromUserid));
		record.setMainuserid(toGetusrId);

		// kişi yazarken büyük küçük dikkat etmezse diye OR kullandım

		PayloadType comeinPay = recordservice.toAssignPayload(map.get("PayType"));
		record.setPayloadType(comeinPay);

		String fromCategory = map.get("CATEGORYTYPE");
		Constants category = GeneralService.getFirstByColumn(Constants.class, Expr.eq("constID", fromCategory));
		record.setConstId(category);

		if (comeinPay.equals("Nakit")|| comeinPay.equals("nakit")) {
			recordmap.put("bankidenr", null);
		} else {
			String toGetBank = map.get("bankident");
			BankCard banks = GeneralService.getFirstByColumn(BankCard.class, Expr.eq("bankID", toGetBank));
			record.setBankid(banks);
		}

		recordservice.add(record);
		recordmap.put("record", record);
		// recordmap.put("User", toGetusrId);
		// recordmap.put("Assign", toGetAssignId);
		// recordmap.put("category", category);

		return recordmap;
	}

	@RequestMapping(value = "/all", method = RequestMethod.POST)
	public @ResponseBody List<RecordIncomeExpense> getAll() {
		return recordservice.getAll();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody RecordIncomeExpense delete(@RequestBody Map<String, String> maps) {
		String descript = maps.get("descript"); // coddan çekiyo
		RecordIncomeExpense rect = recordservice.get("descript", descript); // DB
																			// den
																			// çekiyor
		if (rect == null)
			throw new RuntimeException("No record can be deleted");
		recordservice.delete(rect);
		return rect;
	}
}