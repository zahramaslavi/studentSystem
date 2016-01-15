package no.uio.inf5750.assignment2.dao.hibernate;


import java.util.Collection;
import java.util.List;

import no.uio.inf5750.assignment2.dao.CourseDAO;
import no.uio.inf5750.assignment2.model.Course;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository
@Transactional
public class HibernateCourseDAO implements CourseDAO{
	
	static Logger logger = Logger.getLogger(HibernateCourseDAO.class);
	
	@Autowired
    private SessionFactory sessionFactory;

	@Override
	public int saveCourse(Course course) {
		// TODO Auto-generated method stub
		
		return (Integer)sessionFactory.getCurrentSession().save(course);
		
	}

	@Override
	public Course getCourse(int id) {
		// TODO Auto-generated method stub
	  return  (Course) sessionFactory.getCurrentSession().get(Course.class, id);
	
	}

	@Override
	public Course getCourseByCourseCode(String courseCode) {
		// TODO Auto-generated method stub
		List<Course> courses = sessionFactory.getCurrentSession().createQuery("FROM Course WHERE courseCode = '"+ courseCode + "'").list();
		if(!courses.isEmpty())
			return courses.get(0);
		else
			return null;
	}

	@Override
	public Course getCourseByName(String name) {
		// TODO Auto-generated method stub
		List<Course> courses = sessionFactory.getCurrentSession().createQuery("FROM Course WHERE name = '"+ name + "'").list();
		if(!courses.isEmpty())
			return courses.get(0);
		else
			return null;
	}

	@Override
	public Collection<Course> getAllCourses() {
		// TODO Auto-generated method stub
		List<Course> courses = sessionFactory.getCurrentSession().createQuery("FROM Course").list();
		if(!courses.isEmpty())
			return courses;
		else
			return null;
		
	}

	@Override
	public void delCourse(Course course) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(course);
		
	}

	

}
