package attendance.timelog.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import attendance.timelog.model.Timelog;

@RestController
public interface TimelogController {
	@RequestMapping(value = "/timelog/add", method = RequestMethod.POST)
	Timelog add(@RequestBody Timelog timelog);

	@RequestMapping(value = "/timelog/update", method = RequestMethod.POST)
	void update(@RequestBody Timelog existingObj);

	@RequestMapping(value = "/timelog/get"  ,method = { RequestMethod.POST })
	Timelog get(@RequestBody Long id);

	@RequestMapping(value = "/timelog/getAll", method = RequestMethod.POST)
	List<Timelog> getAll(@RequestBody List<Long> ids);

	@RequestMapping(value = "/timelog/list", method=RequestMethod.POST)
	List<Timelog> list();

	@RequestMapping(value = "/timelog/search", method = RequestMethod.POST)
	List<Timelog> search(@RequestBody String json);

	@RequestMapping(value = "/timelog/delete", method = RequestMethod.POST)
	void delete(@RequestBody Long id);

	@RequestMapping(value = "/timelog/deleteAll", method = RequestMethod.POST)
	void deleteAll(@RequestBody List<Long> ids);
}
