package edu.iu.perfin.controller;

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

import edu.iu.perfin.model.Account;
import edu.iu.perfin.model.Constants;
import edu.iu.perfin.service.ConstantsService;
import edu.iu.perfin.service.GeneralService;
import edu.iu.perfin.type.IncomeExpense;

@Controller // controller restcontrollerdan daha generic bu sadece request için
			// elde edilen mapping leri çekiyor
@RequestMapping(value = "constant")

public class ConstantController {

	@Autowired
	ConstantsService consservice;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> ekle(@RequestBody Map<String, String> map) {
		Map<String, Object> returnmap = new HashMap<String, Object>();
		Constants constants = new Constants();
		
		IncomeExpense kategoriEnumum= consservice.toAssignEnum(map.get("categories"));
		constants.setCategories(kategoriEnumum);
		constants.setClassification(map.get("classification"));
		String arefcode = map.get("caseid");
		Account account = GeneralService.getFirstByColumn(Account.class, Expr.eq("refcode", arefcode));
		constants.setCaseid(account);

		consservice.add(constants);
		returnmap.put("constants", constants);
		return returnmap;
	}

	@RequestMapping(value = "/display", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> liste(@RequestBody Map<String, String> map) {
		Map<String, String> constMap = new HashMap<String, String>();
		constMap.put("categories", map.get("categories"));
		constMap.put("classification", map.get("classification"));
		constMap.put("caseid", map.get("caseid"));
		return constMap;
	}

	@RequestMapping(value = "/all", method = RequestMethod.POST)
	public @ResponseBody List<Constants> getAll() {
		return consservice.getAll();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Constants delete(@RequestBody Map<String, String> maps) {
		String classific = maps.get("classification");
		Constants constant = consservice.get("classification", classific);
		consservice.delete(constant);
		return constant;
	}

}
