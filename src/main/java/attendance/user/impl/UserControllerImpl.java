package attendance.user.impl;

import java.util.List;

import org.springframework.stereotype.Controller;

import attendance.common.impl.GenericControllerImpl;
import attendance.user.controller.UserController;
import attendance.user.model.LoginObject;
import attendance.user.model.User;

@Controller
public class UserControllerImpl extends GenericControllerImpl<User> implements
		UserController {
	@Override
	public Class<User> getObjectClass() {
		return User.class;
	}

	@Override
	public LoginObject login(User user) {
		List<User> result = entityManager
				.createQuery(
						"from "
								+ getObjectClass().getName()
								+ " where username =(:username) and password=(:password)",
						getObjectClass())
				.setParameter("username", user.getUsername())
				.setParameter("password", user.getPassword()).getResultList();
		LoginObject loginObject = null;
		if (result != null && !result.isEmpty()) {
			loginObject = new LoginObject();
			loginObject.setName(result.get(0).getName());
			loginObject.setType(result.get(0).getType());
		}
		return loginObject;
	}
}
