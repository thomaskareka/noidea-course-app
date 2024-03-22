import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdvisorTester {

	private ArrayList<Advisor> advisorList = new ArrayList<Advisor>();
	private ArrayList<UUID> students;
	private Advisor advisor;

	@BeforeClass
	public void oneTimeSetup() {
		advisorList.clear();
		advisorList.add(new Advisor("firstName1","lastName1", "email1", true, "password1"));
		advisorList.add(new Advisor("firstName2", "lastName2", "email2", false, "password2"));
		DataWriter.saveAdvisors();
	}
	
	@AfterClass
	public void oneTimeTearDown() {
		
	}
	
	@BeforeEach
	public void setup() {
		//runs before each test
		advisorList.clear();
		DataWriter.saveAdvisors();
	}
	
	@AfterEach
	public void tearDown() {
		//runs after each test
	}

	//Beginning of testing methods

	@Test 
	public void testSearchByStudentID(){

	}
	@Test 
	public void testAddAdvisee(){

	}
	@Test 
	public void testAddCourseForStudent(){

	}
	@Test 
	public void removeCourseForStudent(){

	}
	@Test 
	public void testAddNotes(){

	}
	@Test 
	public void testEnterFailureRisk(){
		boolean enterFR= advisor.enterFailureRisk(true);
	}
	@Test 
	public void testRemoveFailureRisk(){

	}
	/*
	@Test 
	public void TestIsAdmin(){
		boolean isAdmin=advisor.isAdmin(true);
	}*/

}
