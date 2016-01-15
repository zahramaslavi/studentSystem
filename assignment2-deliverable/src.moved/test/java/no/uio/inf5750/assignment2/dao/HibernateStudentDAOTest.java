package no.uio.inf5750.assignment2.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;

import no.uio.inf5750.assignment2.model.Student;
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
public class HibernateStudentDAOTest {

	@Autowired
	private StudentDAO studentDAO;
	

	@Test
	public void testSaveStudent(){
		Student student = new Student("Zahraa");
		int id = studentDAO.saveStudent(student);
		assertNotNull(studentDAO.getStudent(id));
			
	}
	
	@Test
	public void testGetStudent(){
		Student student = new Student("Zahraa");
		int id = studentDAO.saveStudent(student);
		assertEquals(studentDAO.getStudent(id).getId(),id);
			
	}
	
	@Test
	public void testGetAllStudents(){
		ArrayList<Student> students = new ArrayList<Student>();  
		Student student1 = new Student("Zahraa");
		Student student2 = new Student("John");
		Student student3 = new Student("Frank");
		Student student4 = new Student("Marry");
		
		students.add(student1);
		students.add(student2);
		students.add(student3);
		students.add(student4);
		
		studentDAO.saveStudent(student1);
		studentDAO.saveStudent(student2);
		studentDAO.saveStudent(student3);
		studentDAO.saveStudent(student4);
		
		assertTrue(studentDAO.getAllStudents().containsAll(students));
	}
	
	@Test
	public void testDelStudent(){
		Student student = new Student("John");
		int id = studentDAO.saveStudent(student);
		student = studentDAO.getStudent(id);
		studentDAO.delStudent(student);
		assertNull(studentDAO.getStudent(id));
		
	}
	@Test
	public void testGetStudentByName() {
		Student student = new Student("Zahraa");
		int id = studentDAO.saveStudent(student);
		
		assertEquals(studentDAO.getStudentByName("Zahraa").getId(), id);
		
	}
	

}
