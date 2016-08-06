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
		account.setRefcode(KOD);
		Account acc = get("refcode", KOD);
		if (acc != null)
			throw new RuntimeException("This reference code has already exists.");
		Ebean.save(account);
	}

	public Account get(String columnName, Object value) {
		return GeneralService.getFirstByColumn(Account.class, Expr.eq(columnName, value));
	}

	// generate code
	public static String createRefCode() {
		String code = "";
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

		for (int i = 0; i < 10; i++) {
			code += chars.split("")[(int) (Math.random() * (62 - 1))];
		}
		return code;
	}

	public void delete(Account account) {
		List<User> userList = GeneralService.load(User.class, Expr.eq("account", account));
		for (User user : userList) {
			Ebean.delete(user);
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
