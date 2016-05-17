package attendance.user.impl;

import org.springframework.stereotype.Controller;

import attendance.common.impl.GenericControllerImpl;
import attendance.user.controller.UserController;
import attendance.user.model.LoginObject;
import attendance.user.model.User;

@Controller
public class UserControllerImpl extends GenericControllerImpl<User> implements UserController {
	@Override
	public Class<User> getObjectClass() {
		return User.class;
	}

	@Override
	public LoginObject login(User user) {
		if ("x".equals(user.getUsername()) && "x".equals(user.getPassword())) {
			LoginObject loginObject = new LoginObject();
			loginObject.setName("X Men");
			loginObject.setType("ADMINISTRATOR");
			return loginObject;
		}
		return oginObject;
	}
}
