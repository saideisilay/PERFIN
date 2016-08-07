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
import edu.iu.perfin.model.User;
import edu.iu.perfin.service.GeneralService;
import edu.iu.perfin.service.UserService;

//bu controller arayüz olmadığı için yapılıyor postmandan

@Controller // spring controller old söylüyor bu annotationla
@RequestMapping(value = "user") // adress çubuğuna yazmıyor buraya önceden bir
								// tanımlama yapıyor
public class UserController {

	@Autowired
	UserService service;

	@RequestMapping(value = "/display", method = RequestMethod.POST)
	// postman'de url adresi ve POST yapılması
	public @ResponseBody Map<String, String> liste(@RequestBody Map<String, String> map) {
		Map<String, String> userMap = new HashMap<String, String>();
		userMap.put("name", map.get("name"));
		userMap.put("surname", map.get("surname"));
		userMap.put("username", map.get("username"));
		userMap.put("email", map.get("email"));
		userMap.put("password", map.get("password"));

		String arefcode = map.get("refcode");
		Account account = GeneralService.getFirstByColumn(Account.class, Expr.eq("refcode", arefcode));
		userMap.put("refcode", map.get("refcode"));
		return userMap;
	}

	@RequestMapping(value = "/all", method = RequestMethod.POST)
	public @ResponseBody List<User> getAll() {
		return service.getAll();
	}
	
//update etmek için bi request yaz
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> ekle(@RequestBody Map<String, String> map) {
		Map<String, Object> returnmap = new HashMap<String, Object>();
		User user = new User();
		user.setName(map.get("name"));
		user.setSurname(map.get("surname"));
		user.setUsername(map.get("username"));
		user.setEmail(map.get("email"));
		user.setPassword(map.get("password"));
		
		String arefcode = map.get("refcode");
		Account account = GeneralService.getFirstByColumn(Account.class, Expr.eq("refcode", arefcode));
		user.setAccount(account);
		service.add(user);
		returnmap.put("user", user);	//user ı ekler üstüne set account old için eklemeyle beraber gösterir
		return returnmap;				//ama gereksiz ondan comment yaptım
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody User delete(@RequestBody Map<String, String> map) {

		String username = map.get("username"); // coddan çekiyo
		User user = service.get("username", username); // DB den çekiyor
		if (user == null)
			throw new RuntimeException("No record can be deleted");
		service.delete(user);
		return user;
	}
}