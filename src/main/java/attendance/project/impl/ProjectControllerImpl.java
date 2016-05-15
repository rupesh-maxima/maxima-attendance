package attendance.project.impl;


import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import attendance.common.impl.GenericControllerImpl;
import attendance.project.controller.ProjectController;
import attendance.project.model.Project;

@Controller
public class ProjectControllerImpl extends GenericControllerImpl<Project> implements ProjectController {
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
