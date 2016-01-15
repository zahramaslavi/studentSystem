package no.uio.inf5750.assignment2.gui.controller;

import java.util.Collection;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import no.uio.inf5750.assignment2.model.Student;
import no.uio.inf5750.assignment2.model.Course;
import no.uio.inf5750.assignment2.model.Degree;
import no.uio.inf5750.assignment2.service.StudentSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ApiController {

	static Logger logger = Logger.getLogger(ApiController.class); 
	
	@Autowired
	private StudentSystem studentSystem;
	
	
	@RequestMapping(value="/api/student", method = RequestMethod.GET)
	@ResponseBody
	public Collection<Student> allStudents(ModelMap model){
	 Collection<Student> allStudents = studentSystem.getAllStudents();
	 model.addAttribute("students", allStudents);
	 
	 return allStudents;
	 
	 
	}
	
	@RequestMapping(value="/api/student/{student}/location", method = RequestMethod.GET)
    @ResponseBody
    public Collection<Student> setLocation(@PathVariable("student") int studentId,
            @RequestParam("longitude") String longitude ,
            @RequestParam("latitude") String latitude
            ) {
     studentSystem.setStudentLocation(studentId, longitude, latitude);
     return studentSystem.getAllStudents();
    }

	
	@RequestMapping(value="/api/course", method = RequestMethod.GET)
	@ResponseBody
	public Collection<Course> allCourses(ModelMap model) {
	 
	 Collection<Course> allCourses = studentSystem.getAllCourses();
	 model.addAttribute("courses", allCourses);
	 return allCourses;
	}
	
	@RequestMapping(value="/api/degree", method = RequestMethod.GET)
	@ResponseBody
	public Collection<Degree> allDegrees(ModelMap model) {
	 
	 Collection<Degree> allDegrees = studentSystem.getAllDegrees();
	 model.addAttribute("degrees", allDegrees);
	 return allDegrees;
	 
	}
	
}
