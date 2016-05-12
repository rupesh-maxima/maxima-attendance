package attendance.services;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import attendance.model.Project;

@RestController
public interface ProjectService {
	@RequestMapping(value = "/project/add", method = RequestMethod.POST)
	Project add(@RequestBody Project project);

	@RequestMapping(value = "/project/update", method = RequestMethod.POST)
	void update(@RequestBody Project existingObj);

	@RequestMapping(value = "/project/get"  ,method = { RequestMethod.GET, RequestMethod.POST })
	Project get(@RequestParam(name = "id") Long id);

	@RequestMapping(value = "/project/getAll", method = RequestMethod.POST)
	List<Project> getAll(@RequestBody List<Long> ids);

	@RequestMapping(value = "/project/list", method = { RequestMethod.GET, RequestMethod.POST })
	List<Project> list();

	@RequestMapping(value = "/project/search", method = RequestMethod.POST)
	List<Project> search(@RequestBody String fieldName, @RequestBody Object value);

	@RequestMapping(value = "/project/delete", method = RequestMethod.POST)
	void delete(@RequestParam(name = "id") Long id);

	@RequestMapping(value = "/project/deleteAll", method = RequestMethod.POST)
	void deleteAll(@RequestBody List<Long> ids);
}
