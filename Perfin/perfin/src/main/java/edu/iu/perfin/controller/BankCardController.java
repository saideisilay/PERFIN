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
import edu.iu.perfin.model.BankCards;
import edu.iu.perfin.model.User;
import edu.iu.perfin.service.BankCardService;
import edu.iu.perfin.service.GeneralService;
import edu.iu.perfin.type.CardType;

@Controller
@RequestMapping(value = "bankcard")

public class BankCardController {
	@Autowired
	BankCardService bankservice;

	@RequestMapping(value = "/display", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> list(@RequestBody Map<String, String> map) {
		Map<String, String> bankMap = new HashMap<String, String>();
		bankMap.put("cardName", map.get("cardName"));
		bankMap.put("cardType", map.get("cardType"));
		bankMap.put("description", map.get("description"));
		bankMap.put("limit", map.get("limit"));
		bankMap.put("dept", map.get("dept"));
		return bankMap;
	}

	@RequestMapping(value = "/all", method = RequestMethod.POST)
	public @ResponseBody List<BankCards> getAll() {
		return bankservice.getAll();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody BankCards delete(@RequestBody Map<String, String> map) {

		String cardName = map.get("cardName"); // coddan çekiyo
		BankCards card = bankservice.get("cardName", cardName); // DB den
																// çekiyor
		if (card == null)
			throw new RuntimeException("No record can be deleted");
		bankservice.delete(card);
		return card;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody BankCards cards(@RequestBody Map<String, String> map2) {

		BankCards card = new BankCards();
		card.setCardName(map2.get("cardName"));
		
		String incomingCard = map2.get("cardType");
		CardType type =null;
		if (incomingCard.equals("Debit Card")) {
			type= CardType.DebitCard;}
		else if(incomingCard.equals("Credit Card")){
			type=CardType.CreditCard;
		}
		card.setCardType (type);
		card.setDescription(map2.get("description"));
		String lim = map2.get("limit");
		Double limm = Double.parseDouble(lim);
		card.setLimit(limm);
		
		String dep = map2.get("dept");
		Double depp = Double.parseDouble(dep);
		card.setDept(depp);
		bankservice.add(card);

		return card;
	}
}
