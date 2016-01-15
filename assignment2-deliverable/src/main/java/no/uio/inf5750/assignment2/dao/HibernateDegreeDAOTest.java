 package no.uio.inf5750.assignment2.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;

import no.uio.inf5750.assignment2.model.Degree;
import no.uio.inf5750.assignment2.model.Degree;

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
public class HibernateDegreeDAOTest {

	@Autowired
	private DegreeDAO degreeDAO;


	
	@Test
	public void testSaveDegree(){
		Degree degree = new Degree("Master");
		int id = degreeDAO.saveDegree(degree);
		assertNotNull(degreeDAO.getDegree(id));
			
	}
	
	@Test
	public void testGetDegree(){
		Degree degree = new Degree("Bachlor");
		int id = degreeDAO.saveDegree(degree);
		assertEquals(degreeDAO.getDegree(id).getId(),id);
			
	}
	
	@Test
	public void testGetAllDegrees(){
		ArrayList<Degree> degrees = new ArrayList<Degree>();  
		Degree degree1 = new Degree("Bachlor");
		Degree degree2 = new Degree("Master");
		Degree degree3 = new Degree("Phd");
		degrees.add(degree1);
		degrees.add(degree2);
		degrees.add(degree3);
		
		degreeDAO.saveDegree(degree1);
		degreeDAO.saveDegree(degree2);
		degreeDAO.saveDegree(degree3);
		
		assertTrue(degreeDAO.getAllDegrees().containsAll(degrees));
	}
	
	@Test
	public void testDelDegree(){
		Degree degree = new Degree("Phd");
		int id = degreeDAO.saveDegree(degree);
		degree = degreeDAO.getDegree(id);
		degreeDAO.delDegree(degree);
		assertNull(degreeDAO.getDegree(id));
		
	}
	@Test
	public void testGetDegreeByType() {
		Degree degree = new Degree("a");
		int id = degreeDAO.saveDegree(degree);
		
		assertEquals(degreeDAO.getDegreeByType("a").getId(), id);
		
	}
	

}
