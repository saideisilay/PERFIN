package edu.iu.perfin.controller;
//update nasıl çalıştırılıp test edilecek?
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.iu.perfin.model.Account;
import edu.iu.perfin.service.AccountService;

@Controller
@RequestMapping(value = "account")

// @RestController
public class AccountController {

	@Autowired
	public AccountService accservice;

	@RequestMapping(value = "/all", method = RequestMethod.POST)
	public @ResponseBody List<Account> getAll() {
		return accservice.getAll();
	}

	@RequestMapping(value = "/ekle", method = RequestMethod.POST)
	public @ResponseBody Account accekle(@RequestBody Map<String, String> map2) {
		// Map<String, Object> returnmap2 = new HashMap<String, Object>();

		Account account = new Account();
		account.setAccountName(map2.get("accountName"));
		account.setAccountDesc(map2.get("accountDesc"));
		account.setAddress(map2.get("address"));
		account.setRefCode(map2.get("refCode"));
		accservice.add(account);
		// returnmap2.put("kasa", account);
		return account;
	}
//görüyo
	@RequestMapping(value = "/goruntule", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> list(@RequestBody Map<String, String> map) {
		Map<String, String> accMap = new HashMap<String, String>();
		accMap.put("accountName", map.get("accountName"));
		accMap.put("accountDesc", map.get("accountDesc"));
		accMap.put("address", map.get("address"));
		accMap.put("refcode", map.get("refcode"));
		return accMap;
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody Account delete(@RequestBody Map<String, String> maps) {
		String refCode = maps.get("refCode");
		Account account = accservice.get("referencecode", refCode);
		if (account == null)
			throw new RuntimeException("No record can be deleted");
		accservice.delete(account);
		return account;
	}
}
