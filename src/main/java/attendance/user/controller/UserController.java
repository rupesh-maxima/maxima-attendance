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
	@ResponseBody
	User add(@RequestBody User User);

	@RequestMapping(value = "/user/update", method = RequestMethod.POST)
	void update(@RequestBody User existingObj);

	@RequestMapping(value = "/user/get", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	User get(@RequestParam(name = "id") Long id);
	
	@RequestMapping(value = "/user/login", method = {RequestMethod.POST })
	@ResponseBody
	LoginObject login(@RequestBody User user);
}
