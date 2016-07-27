package edu.iu.perfin.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;

import edu.iu.perfin.model.Account;
import edu.iu.perfin.model.User;

@Service
public class AccountService {
	public void add(Account account) {
		
		String KOD = createRefCode();
		account.setRefCode(KOD);
		Account acc = get("refCode", KOD);
		if (acc != null)
			throw new RuntimeException("This reference code has already exists.");
		Ebean.save(account);
	}

	public Account get(String columnName, Object value) {
		return GeneralService.getFirstByColumn(Account.class, Expr.eq(columnName, value));
	}
	//generate code
	public static String createRefCode(){
	    String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	    String ret = "";
	    int length = chars.length();
	    for (int i = 0; i < 10; i ++){
	        ret += chars.split("")[ (int) (Math.random() * (length - 1)) ];
	    }
	    return ret;
	}

	public void delete(Account account) {
		List<User> userList = GeneralService.load(User.class, Expr.eq("account", account));
		for (User user : userList) {
			user.setAccount(null);
			Ebean.update(user);
		}
		Ebean.delete(account);
	}

	public void update(Account account) {
		Ebean.update(account);
	}

	public Account getAccount(String columnName, Object value) {
		return GeneralService.getFirstByColumn(Account.class, Expr.eq(columnName, value));
	}

	public List<Account> getAll() {
		return GeneralService.loadAll(Account.class);
	}
}
