package attendance.holiday.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import attendance.holiday.model.Holiday;

@RestController
public interface HolidayController {
	@RequestMapping(value = "/holiday/add", method = RequestMethod.POST)
	Holiday add(@RequestBody Holiday holiday);

	@RequestMapping(value = "/holiday/update", method = RequestMethod.POST)
	void update(@RequestBody Holiday existingObj);

	@RequestMapping(value = "/holiday/get", method = { RequestMethod.POST })
	Holiday get(@RequestBody Long id);

	@RequestMapping(value = "/holiday/getAll", method = RequestMethod.POST)
	List<Holiday> getAll(@RequestBody List<Long> ids);

	@RequestMapping(value = "/holiday/list", method = RequestMethod.POST)
	List<Holiday> list();

	@RequestMapping(value = "/holiday/search", method = RequestMethod.POST)
	List<Holiday> search(@RequestBody String json);

	@RequestMapping(value = "/holiday/delete", method = RequestMethod.POST)
	void delete(@RequestBody Long id);

	@RequestMapping(value = "/holiday/deleteAll", method = RequestMethod.POST)
	void deleteAll(@RequestBody List<Long> ids);

}





