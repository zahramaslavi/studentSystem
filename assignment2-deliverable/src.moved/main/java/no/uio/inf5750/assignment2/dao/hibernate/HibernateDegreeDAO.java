package no.uio.inf5750.assignment2.dao.hibernate;

import java.util.Collection;
import java.util.List;

import no.uio.inf5750.assignment2.dao.DegreeDAO;
import no.uio.inf5750.assignment2.model.Course;
import no.uio.inf5750.assignment2.model.Degree;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class HibernateDegreeDAO implements DegreeDAO{

	static Logger logger = Logger.getLogger(HibernateCourseDAO.class);
	
	@Autowired
    private SessionFactory sessionFactory;
	
	
	@Override
	public int saveDegree(Degree degree) {
		// TODO Auto-generated method stub
		return (Integer)sessionFactory.getCurrentSession().save(degree);
		
	}

	@Override
	public Degree getDegree(int id) {
		// TODO Auto-generated method stub
		return (Degree)sessionFactory.getCurrentSession().get(Degree.class, id);
		
	}

	@Override
	public Degree getDegreeByType(String type) {
		// TODO Auto-generated method stub
		List<Degree> degree = sessionFactory.getCurrentSession().createQuery("FROM Degree WHERE type = '"+ type + "'").list();
		if(!degree.isEmpty())
			return degree.get(0);
		else
			return null;
		
	}

	@Override
	public Collection<Degree> getAllDegrees() {
		// TODO Auto-generated method stub
		List<Degree> degrees = sessionFactory.getCurrentSession().createQuery("FROM Degree").list();
		if(!degrees.isEmpty())
			return degrees;
		else
			return null;
	}

	@Override
	public void delDegree(Degree degree) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(degree);
		
	}

}
