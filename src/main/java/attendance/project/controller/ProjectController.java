package attendance.project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import attendance.project.model.Project;

@Controller
public interface ProjectController {
	@RequestMapping(value = "/project/add", method = RequestMethod.POST)
	@ResponseBody Project add(@RequestBody Project project);

	@RequestMapping(value = "/project/update", method = RequestMethod.POST)
	void update(@RequestBody Project existingObj);

	@RequestMapping(value = "/project/get"  ,method = { RequestMethod.POST })
	@ResponseBody Project get(@RequestBody Long id);

	@RequestMapping(value = "/project/getAll", method = RequestMethod.POST)
	@ResponseBody List<Project> getAll(@RequestBody List<Long> ids);

	@RequestMapping(value = "/project/list", method=RequestMethod.POST)
	@ResponseBody List<Project> list();

	@RequestMapping(value = "/project/search", method = RequestMethod.POST)
	@ResponseBody List<Project> search(@RequestBody String json);

	@RequestMapping(value = "/project/delete", method = RequestMethod.POST)
	void delete(@RequestBody Long id);

	@RequestMapping(value = "/project/deleteAll", method = RequestMethod.POST)
	void deleteAll(@RequestBody List<Long> ids);
}
