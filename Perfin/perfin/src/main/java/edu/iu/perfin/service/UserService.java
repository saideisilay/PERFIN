package edu.iu.perfin.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;
import edu.iu.perfin.model.User;

@Service
public class UserService {

	public void add(User user) {
//username ve email unique oldu
		User user1 = get("username", user.getUsername());
		if (user1 != null)
			throw new RuntimeException("This username has already exists.");
		else
		{	User user2 = get("email", user.getEmail());
		if (user2 != null)
			throw new RuntimeException("This email has already exists.");}
		Ebean.save(user);
	}

	public void delete(User user) {
		Ebean.delete(user);
	}

	public void update(User user) {
		Ebean.update(user);
	}

	public User get(String columnName, Object value) {
		return GeneralService.getFirstByColumn(User.class, Expr.eq(columnName, value));
	}

	public List<User> getAll() {
		return GeneralService.loadAll(User.class);
	}
}
