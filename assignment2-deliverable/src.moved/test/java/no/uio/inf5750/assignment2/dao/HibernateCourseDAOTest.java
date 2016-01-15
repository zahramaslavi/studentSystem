 package no.uio.inf5750.assignment2.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;

import no.uio.inf5750.assignment2.model.Course;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration //this will look for a file EventDaoTest-context.xml
@ContextConfiguration(locations={"classpath*:META-INF/assignment2/beans.xml"})
@Transactional
public class HibernateCourseDAOTest {

	@Autowired
	private CourseDAO courseDAO;
	
	
	
	@Test
	public void testSaveCourse(){
		Course course = new Course("201", "OpenSourceSW");
		int id = courseDAO.saveCourse(course);
		assertNotNull(courseDAO.getCourse(id));
			
	}
	
	@Test
	public void testGetCourse(){
		Course course = new Course("201", "OpenSourceSW");
		int id = courseDAO.saveCourse(course);
		assertEquals(courseDAO.getCourse(id).getId(),id);
			
	}
	
	@Test
	public void testGetAllCourses(){
		ArrayList<Course> courses = new ArrayList<Course>();  
		Course course1 = new Course("505","DataBase");
		Course course2 = new Course("201","OpenSourceSW");
		Course course3 = new Course("403","SocialNetwork");
		Course course4 = new Course("424","ModelBasedSW");
		courses.add(course1);
		courses.add(course2);
		courses.add(course3);
		courses.add(course4);
		
		courseDAO.saveCourse(course1);
		courseDAO.saveCourse(course2);
		courseDAO.saveCourse(course3);
		courseDAO.saveCourse(course4);
		
		assertTrue(courseDAO.getAllCourses().containsAll(courses));
	}
	
	@Test
	public void testDelCourse(){
		Course course = new Course("201","OpenSourceSW");
		int id = courseDAO.saveCourse(course);
		course = courseDAO.getCourse(id);
		courseDAO.delCourse(course);
		assertNull(courseDAO.getCourse(id));
		
	}
	@Test
	public void testGetCourseByCourseCode() {
		Course course = new Course("201","OpenSourceSW");
		int id = courseDAO.saveCourse(course);
		
		assertEquals(courseDAO.getCourseByCourseCode("201").getId(), id);
		
	}
	
	@Test
	public void testGetCourseByName() {
		Course course = new Course("201","OpenSourceSW");
		int id = courseDAO.saveCourse(course);
		
		assertEquals(courseDAO.getCourseByName("OpenSourceSW").getId(), id);
	}
	
	
	
	

}

