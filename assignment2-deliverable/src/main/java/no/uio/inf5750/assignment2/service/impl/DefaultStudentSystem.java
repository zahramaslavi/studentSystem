package no.uio.inf5750.assignment2.service.impl;

import java.util.Collection;
import java.util.Set;

import no.uio.inf5750.assignment2.dao.CourseDAO;
import no.uio.inf5750.assignment2.dao.DegreeDAO;
import no.uio.inf5750.assignment2.dao.StudentDAO;
import no.uio.inf5750.assignment2.model.Course;
import no.uio.inf5750.assignment2.model.Degree;
import no.uio.inf5750.assignment2.model.Student;
import no.uio.inf5750.assignment2.service.StudentSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultStudentSystem implements StudentSystem{
	@Autowired
	private CourseDAO courseDAO;

	@Autowired
	private DegreeDAO degreeDAO;

	@Autowired
	private StudentDAO studentDAO;
	
	
	@Override
	public int addCourse(String courseCode, String name) {
		// TODO Auto-generated method stub
		Course course = new Course(courseCode, name);
		return courseDAO.saveCourse(course);
		
	}

	@Override
	public void updateCourse(int courseId, String courseCode, String name) {
		// TODO Auto-generated method stub
	    
		Course course = courseDAO.getCourse(courseId);
		course.setCourseCode(courseCode);
		course.setName(name);
		courseDAO.saveCourse(course);
		
	}

	@Override
	public Course getCourse(int courseId) {
		// TODO Auto-generated method stub
		return courseDAO.getCourse(courseId);
		
		
	}

	@Override
	public Course getCourseByCourseCode(String courseCode) {
		// TODO Auto-generated method stub
		return courseDAO.getCourseByCourseCode(courseCode);
	}

	@Override
	public Course getCourseByName(String name) {
		// TODO Auto-generated method stub
		return courseDAO.getCourseByName(name);
	}

	@Override
	public Collection<Course> getAllCourses() {
		// TODO Auto-generated method stub
	    return courseDAO.getAllCourses();
	}

	@Override
	public void delCourse(int courseId) {
		// TODO Auto-generated method stub
		Course course = getCourse(courseId);
		  for(Student student : course.getAttendants()) 
		     {
			   student.getCourses().remove(course);
		     }
		  for(Degree degree : degreeDAO.getAllDegrees())
		     {
			   degree.getRequiredCourses().remove(course);
		     }
	  courseDAO.delCourse(course);
		
	}

	@Override
	public void addAttendantToCourse(int courseId, int studentId) {
		// TODO Auto-generated method stub
		Course course = getCourse(courseId);
		Student student = getStudent(studentId);
		
		course.getAttendants().add(student);
		student.getCourses().add(course);
		
		studentDAO.saveStudent(student);
		
	}

	@Override
	public void removeAttendantFromCourse(int courseId, int studentId) {
		// TODO Auto-generated method stub
		Course course = getCourse(courseId);
		Student student = getStudent(studentId);
		
		course.getAttendants().remove(student);
		student.getCourses().remove(course);
		
		studentDAO.saveStudent(student);
		
		
	}

	@Override
	public int addDegree(String type) {
		// TODO Auto-generated method stub
		Degree degree = new Degree(type);
		
		return degreeDAO.saveDegree(degree);
		
	}

	@Override
	public void updateDegree(int degreeId, String type) {
		// TODO Auto-generated method stub
		Degree degree = getDegree(degreeId);
		degree.setType(type);
		degreeDAO.saveDegree(degree);
		
	}

	@Override
	public Degree getDegree(int degreeId) {
		// TODO Auto-generated method stub
		return degreeDAO.getDegree(degreeId);	
	}

	@Override
	public Degree getDegreeByType(String type) {
		// TODO Auto-generated method stub		
		return degreeDAO.getDegreeByType(type);
	}

	@Override
	public Collection<Degree> getAllDegrees() {
		// TODO Auto-generated method stub
		return degreeDAO.getAllDegrees();
		
	}

	@Override
	public void delDegree(int degreeId) {
		// TODO Auto-generated method stub
		Degree degree = getDegree(degreeId);
		for(Student student : studentDAO.getAllStudents() ) {
			student.getDegrees().remove(degree);
		}
	  
	  degreeDAO.delDegree(degree);
		
	}

	@Override
	public void addRequiredCourseToDegree(int degreeId, int courseId) {
		// TODO Auto-generated method stub
        Degree degree = getDegree(degreeId);
        Course course = courseDAO.getCourse(courseId);
        
        degree.getRequiredCourses().add(course);
        degreeDAO.saveDegree(degree);
		
	}

	@Override
	public void removeRequiredCourseFromDegree(int degreeId, int courseId) {
		// TODO Auto-generated method stub
		Degree degree = getDegree(degreeId);
		Course course = getCourse(courseId);
		
		degree.getRequiredCourses().remove(course);
		
		degreeDAO.saveDegree(degree);
	}

	@Override
	public int addStudent(String name) {
		// TODO Auto-generated method stub
		Student student = new Student(name);
		return studentDAO.saveStudent(student);
	}

	@Override
	public void updateStudent(int studentId, String name) {
		// TODO Auto-generated method stub
		Student student = getStudent(studentId);
		student.setName(name);
		
		studentDAO.saveStudent(student);
	}

	@Override
	public Student getStudent(int studentId) {
		// TODO Auto-generated method stub
		return studentDAO.getStudent(studentId);
	}

	@Override
	public Student getStudentByName(String name) {
		// TODO Auto-generated method stub
		
		return studentDAO.getStudentByName(name);
	}

	@Override
	public Collection<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return studentDAO.getAllStudents();
		
	}

	@Override
	public void delStudent(int studentId) {
		// TODO Auto-generated method stub
		Student student = getStudent(studentId);
		for(Course course : student.getCourses()){
			course.getAttendants().remove(student);
		}
	  studentDAO.delStudent(student);	
	}

	@Override
	public void addDegreeToStudent(int studentId, int degreeId) {
		// TODO Auto-generated method stub
		Student student = getStudent(studentId);
		Degree degree = getDegree(degreeId);
		
		student.getDegrees().add(degree);
		
	}

	@Override
	public void removeDegreeFromStudent(int studentId, int degreeId) {
		// TODO Auto-generated method stub
		Student student = getStudent(studentId);
		Degree  degree = getDegree(degreeId);
		
		student.getDegrees().remove(degree);
		
	}

	@Override
	public boolean studentFulfillsDegreeRequirements(int studentId, int degreeId) {
		// TODO Auto-generated method stub
		Student student = getStudent(studentId);
		Degree degree = getDegree(degreeId);
		
		
		Set<Course>  studentCourses = student.getCourses();
		
		if(degree.getRequiredCourses().isEmpty() || studentCourses.containsAll(degree.getRequiredCourses()))
			return true;
		return false;
	}

	@Override
	public void setStudentLocation(int studentId, String latitude,
			String longitude) {
		// TODO Auto-generated method stub
		Student student = getStudent(studentId);
		student.setLatitude(latitude);
		student.setLongitude(longitude);
		
	
	}

}
