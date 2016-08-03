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
import edu.iu.perfin.model.BankCard;
import edu.iu.perfin.service.BankCardService;
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
		bankMap.put("cardNumber", map.get("cardNumber"));
		bankMap.put("cardType", map.get("cardType"));
		bankMap.put("description", map.get("description"));
		bankMap.put("limit", map.get("limit"));
		bankMap.put("dept", map.get("dept"));
		return bankMap;
	}

	@RequestMapping(value = "/all", method = RequestMethod.POST)
	public @ResponseBody List<BankCard> getAll() {
		return bankservice.getAll();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody BankCard delete(@RequestBody Map<String, String> map) {

		String cardNumber = map.get("cardNumber"); // coddan çekiyo
		BankCard card = bankservice.get("cardNumber", cardNumber); // DB den
																	// çekiyor
		if (card == null)
			throw new RuntimeException("No record can be deleted");
		bankservice.delete(card);
		return card;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody BankCard cards(@RequestBody Map<String, String> map2) {

		BankCard card = new BankCard();
		card.setCardName(map2.get("cardName"));

		String incomingCard = map2.get("cardType");
		CardType type = null;
		if (incomingCard.equals("Debit Card")) {
			type = CardType.DebitCard;
		} else if (incomingCard.equals("Credit Card")) {
			type = CardType.CreditCard;
		}
		card.setCardType(type);
		card.setCardNumber(map2.get("cardNumber"));
		card.setDescription(map2.get("description"));
		String lim = map2.get("limit");
		BigDecimal limm = new BigDecimal(lim);
		card.setLimit(limm);

		String dep = map2.get("dept");
		BigDecimal depp = new BigDecimal(dep);
		card.setDept(depp);

		bankservice.add(card);
		return card;
	}
}
