package attendance.timelog.impl;

import org.springframework.web.bind.annotation.RestController;

import attendance.common.impl.GenericControllerImpl;
import attendance.timelog.controller.TimelogController;
import attendance.timelog.model.Timelog;



@RestController
public class TimelogControllerImpl extends GenericControllerImpl<Timelog> implements TimelogController {
	@Override
	public Class<Timelog> getObjectClass() {
		return Timelog.class;
	}
}
