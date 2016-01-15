package no.uio.inf5750.assignment2.dao.hibernate;

import java.util.Collection;
import java.util.List;

import no.uio.inf5750.assignment2.dao.StudentDAO;
import no.uio.inf5750.assignment2.model.Course;
import no.uio.inf5750.assignment2.model.Student;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateStudentDAO implements StudentDAO{
	
	static Logger logger = Logger.getLogger(HibernateCourseDAO.class);
	
	@Autowired
    private SessionFactory sessionFactory;

	@Override
	public int saveStudent(Student student) {
		// TODO Auto-generated method stub
	return (Integer)sessionFactory.getCurrentSession().save(student);
		
	}

	@Override
	public Student getStudent(int id) {
		// TODO Auto-generated method stub
	return	(Student)sessionFactory.getCurrentSession().get(Student.class, id);
		
	}

	@Override
	public Student getStudentByName(String name) {
		// TODO Auto-generated method stub
		List<Student> students = sessionFactory.getCurrentSession().createQuery("FROM Student WHERE name = '"+ name + "'").list();
		if(!students.isEmpty())
			return students.get(0);
		else
			return null;
		
	}

	@Override
	public Collection<Student> getAllStudents() {
		// TODO Auto-generated method stub
		List<Student> students = sessionFactory.getCurrentSession().createQuery("FROM Student").list();
		if(!students.isEmpty())
			return students;
		else
			return null;
	}

	@Override
	public void delStudent(Student student) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(student);
		
	}

}
