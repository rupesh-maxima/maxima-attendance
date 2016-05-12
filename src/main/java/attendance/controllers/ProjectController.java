package attendance.controllers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import attendance.model.Project;

@RestController
public class ProjectController {

    @Autowired
    EntityManagerFactory manager;

    @RequestMapping("/project/get")
    public Project get(@RequestParam(value="id") Integer id) {
    	return null;
    }
    
    @RequestMapping(value = "/project/add", method = RequestMethod.POST)
    public Project add(@RequestBody Project project) {
        EntityManager entityManager = manager.createEntityManager(); 
    	entityManager.getTransaction().begin();
    	entityManager.persist(project);
    	entityManager.getTransaction().commit();
        return project;
    }
    
    
}
