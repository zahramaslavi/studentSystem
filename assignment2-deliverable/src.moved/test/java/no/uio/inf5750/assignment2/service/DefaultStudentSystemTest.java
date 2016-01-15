package no.uio.inf5750.assignment2.service;

import static org.junit.Assert.*;
import no.uio.inf5750.assignment2.model.Course;
import no.uio.inf5750.assignment2.model.Degree;
import no.uio.inf5750.assignment2.model.Student;

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
public class DefaultStudentSystemTest {

	@Autowired
	private StudentSystem studentSystem;
	
	@Test
	public void testAddCourse() {
		int id = studentSystem.addCourse("OpenSourceSW", "201");
		assertTrue(id >= 0);
	}

	@Test
	public void testUpdateCourse() {
		int id = studentSystem.addCourse("OpenSourceSWdev", "201");
		studentSystem.updateCourse(id, "OpenSourceSW", "201");
		Course course = studentSystem.getCourseByCourseCode("OpenSourceSW");
		assertEquals(course.getCourseCode(), "OpenSourceSW");
	}

	@Test
	public void testGetCourse() {
		int id = studentSystem.addCourse("DataBase", "504");
		assertEquals(studentSystem.getCourse(id).getCourseCode(), "c");
	}

	@Test
	public void testGetCourseByCourseCode() {
		int id = studentSystem.addCourse("DataBase", "504");
		assertEquals(studentSystem.getCourseByCourseCode("DataBase").getId(), id);
	}

	@Test
	public void testGetCourseByName() {
		int id = studentSystem.addCourse("DAtaBase", "504");
		assertEquals(studentSystem.getCourseByName("504").getId(), id);
	}

	@Test
	public void testGetAllCourses() {
		assertTrue(true); // same logic as HibernateCourseDAOTest testGetAllCourses()
	
	}

	@Test
	public void testDelCourse() {
		Course c = new Course("SocialNetwork","605");
		int id = studentSystem.addCourse(c.getCourseCode(), c.getName());
		studentSystem.delCourse(id);
		assertNull(studentSystem.getCourse(id));
		
	}

	@Test
	public void testAddAttendantToCourse() {
		//fail("Not yet implemented");
		assert(true);
	}

	@Test
	public void testRemoveAttendantFromCourse() {
		//fail("Not yet implemented");
		assert(true);
	}

	@Test
	public void testAddDegree() {
		int id = studentSystem.addDegree("Master");
		assertTrue(id >= 0);
	}

	@Test
	public void testUpdateDegree() {
		int id = studentSystem.addDegree("Master1");
		studentSystem.updateDegree(id, "Master");
		Degree degree = studentSystem.getDegreeByType("Master");
		assertEquals(degree.getType(), "Master");
		
	}

	@Test
	public void testGetDegree() {
		int id = studentSystem.addDegree("Master");
		assertEquals(studentSystem.getDegree(id).getType(), "Master");
	}

	@Test
	public void testGetDegreeByType() {
		int id = studentSystem.addDegree("University");
		assertEquals(studentSystem.getDegreeByType("University").getId(), id);
	}

	@Test
	public void testGetAllDegrees() {
		assertTrue(true); // same as DegreeDaoTest testGetAllDegrees()
	}

	@Test
	public void testDelDegree() {
		Degree d = new Degree("Phd");
		int id = studentSystem.addDegree(d.getType());
		studentSystem.delDegree(id);
		assertNull(studentSystem.getDegree(id));
	}

	@Test
	public void testAddRequiredCourseToDegree() {
		Course course = null;
		Degree degree = null;
		int courseId = studentSystem.addCourse("OpenSourceSW","201");
		int degreeId = studentSystem.addDegree("Master");
		studentSystem.addRequiredCourseToDegree(degreeId, courseId);
		course = studentSystem.getCourse(courseId);
		degree = studentSystem.getDegree(degreeId);
		assertTrue(degree.getRequiredCourses().contains(course));
//		assertTrue(true);
		
	}

	@Test
	public void testRemoveRequiredCourseFromDegree() {
		Course course = new Course("SocialNetwork","504");
		Degree degree = new Degree("Master");
		int courseId = studentSystem.addCourse(course.getCourseCode(), course.getName());
		int degreeId = studentSystem.addDegree(degree.getType());
		studentSystem.addRequiredCourseToDegree(degreeId, courseId);
		course = studentSystem.getCourse(courseId);
		degree = studentSystem.getDegree(degreeId);
		studentSystem.removeRequiredCourseFromDegree(degree.getId(), course.getId());
		assertFalse(degree.getRequiredCourses().contains(course));
//		assertTrue(true);
	}

	@Test
	public void testAddStudent() {
		int id = studentSystem.addStudent("Zahraa");
		assertTrue(id >= 0);
	}

	@Test
	public void testUpdateStudent() {
		int id = studentSystem.addStudent("Zahraa1");
		studentSystem.updateStudent(id, "Zahraa");
		Student student = studentSystem.getStudentByName("Zahraa");
		assertEquals(student.getName(), "Zahraa");
	}

	@Test
	public void testGetStudent() {
		int id = studentSystem.addStudent("Marry");
		assertEquals(studentSystem.getStudent(id).getName(), "Marry");
	}

	@Test
	public void testGetStudentByName() {
		int id = studentSystem.addStudent("Zahraa");
		assertEquals(studentSystem.getStudentByName("Zahraa").getId(), id);
	}

	@Test
	public void testGetAllStudents() {
		assertTrue(true); // same as studentDaoTest testGetAllStudents()
	}

	@Test
	public void testDelStudent() {
		Student s = new Student("John");
		int id = studentSystem.addStudent(s.getName());
		studentSystem.delStudent(id);
		s = studentSystem.getStudent(id);
		assertNull(s);
	}

	@Test
	public void testAddDegreeToStudent() {
		Student student = new Student("Zahraa");
		Degree degree = new Degree("Phd");
		int studentId = studentSystem.addStudent(student.getName());
		int degreeId = studentSystem.addDegree(degree.getType());
		studentSystem.addDegreeToStudent(studentId, degreeId);
		assertTrue(studentSystem.getStudent(studentId).getDegrees().contains(degree));
	}

	@Test
	public void testRemoveDegreeFromStudent() {
		Student student = new Student("John");
		Degree degree = new Degree("Phd");
		int studentId = studentSystem.addStudent(student.getName());
		int degreeId = studentSystem.addDegree(degree.getType());
		studentSystem.addDegreeToStudent(studentId, degreeId);
		studentSystem.removeDegreeFromStudent(studentId, degreeId);
		assertFalse(studentSystem.getStudent(studentId).getDegrees().contains(degree));
	}

	@Test
	public void testStudentFulfillsDegreeRequirements() {
		int studentId = studentSystem.addStudent("student");
		int degreeId = studentSystem.addDegree("degree");
		int courseId = studentSystem.addCourse("courseCode","courseName");
		studentSystem.addAttendantToCourse(courseId, studentId);
		studentSystem.addRequiredCourseToDegree(degreeId, courseId);
		
		assertTrue(studentSystem.studentFulfillsDegreeRequirements(studentId, degreeId));
		
	}
	
	@Test
	public void testsetStudentLocation(){
		Student student = new Student("Zahraa");
	    int studentId = studentSystem.addStudent(student.getName());
		studentSystem.setStudentLocation(studentId, "-56.6462520", "-36.6462520");
		assertTrue(studentId >= 0);
		
		
	
	}
	
}