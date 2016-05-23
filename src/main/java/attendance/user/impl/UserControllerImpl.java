package attendance.user.impl;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import attendance.common.impl.GenericControllerImpl;
import attendance.user.controller.UserController;
import attendance.user.model.LoginObject;
import attendance.user.model.User;

@RestController
public class UserControllerImpl extends GenericControllerImpl<User> implements UserController {
	@Override
	public Class<User> getObjectClass() {
		return User.class;
	}

	@Override
	public LoginObject login(User user) {
		List<User> result = entityManager
				.createQuery(
						"from " + getObjectClass().getName() + " where username =(:username) and password=(:password)",
						getObjectClass())
				.setParameter("username", user.getUsername()).setParameter("password", user.getPassword())
				.getResultList();
		LoginObject loginObject = null;
		if (result != null && !result.isEmpty()) {
			loginObject = new LoginObject();
			loginObject.setName(result.get(0).getName());
			loginObject.setType(result.get(0).getType());
		}
		return loginObject;
	}

	@Override
	public User get(String name) {
		User admin = (User) entityManager.find(User.class, name);
		return admin;
	}
}
