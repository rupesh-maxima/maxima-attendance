package attendance.holiday.impl;

import org.springframework.web.bind.annotation.RestController;

import attendance.common.impl.GenericControllerImpl;
import attendance.holiday.controller.HolidayController;
import attendance.holiday.model.Holiday;

@RestController
public class HolidayControllerImpl extends GenericControllerImpl<Holiday> implements HolidayController {
	@Override
	public Class<Holiday> getObjectClass() {
		return Holiday.class;
	}

}  
