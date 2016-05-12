package attendance.services.impl;


import java.util.HashMap;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import attendance.model.Project;
import attendance.services.ProjectService;

@RestController
public class ProjectServiceImpl extends ObjectServiceImpl<Project> implements ProjectService {
	@Override
	public Class<Project> getObjectClass() {
		return Project.class;
	}

	@Override
	@Transactional
	public void update(Project projectObj) {
		List<Project> result = getProjectDetails(projectObj.getAgreement());
		if (result != null && result.size() > 0 && !result.get(0).getId().equals(projectObj.getId())) {
		}
		super.update(projectObj);
	}

	public List<Project> getProjectDetails(String agreement) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("agreement", agreement);
		return (super.search("agreement=(:agreement)", params));
	}

}
