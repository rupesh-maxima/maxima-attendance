package attendance.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import attendance.user.model.LoginObject;
import attendance.user.model.User;

@Controller
public interface UserController {
	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	User add(@RequestBody User User);

	@RequestMapping(value = "/user/update", method = RequestMethod.POST)
	void update(@RequestBody User existingObj);


    @RequestMapping(value = "/user/get", method =RequestMethod.POST )
	User get(@RequestBody String name);
	
	@RequestMapping(value = "/user/login", method = {RequestMethod.POST })
	LoginObject login(@RequestBody User user);
}
