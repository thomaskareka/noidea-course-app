import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudentTester {
	private UserList userList;
	private Student s;

	@BeforeClass
	public void oneTimeSetup() {
		userList= UserList.getInstance();
		s = userList.getStudentByEmail("johndoe@example.com");
	}
	
	@AfterClass
	public void oneTimeTearDown() {
		
	}
	
	@BeforeEach
	public void setup() {
		
	}
	
	@AfterEach
	public void tearDown() {
		//runs after each test
	}

	@Test
	public void CheckIfAtRiskTrue(){
		s.setOverallGPA(2.5);
		assertTrue(s.checkIfAtRisk());
	}

	@Test
	public void CheckIfAtRiskFalse(){
		s.setOverallGPA(3.6);
		assertFalse(s.checkIfAtRisk());
	}
	@Test
	public void CheckIfAtRiskZero(){
		s.setOverallGPA(0);
		assertTrue(s.checkIfAtRisk());
	}

	@Test 
	public void CheckIfAtRiskFour(){
		s.setOverallGPA(4.0);
		assertFalse(s.checkIfAtRisk());
	}

	@Test
	public void CheckIfAtRiskFalseLowBarrier(){
		s.setOverallGPA(3.0);
		assertFalse(s.checkIfAtRisk());
	}

	@Test
	public void CheckIfAtRiskTrueHighBarrier(){
		s.setOverallGPA(2.999999999);
		assertTrue(s.checkIfAtRisk());
	}

	@Test
	public void CheckIfAtRiskOutOfBoundsOverFour(){
		s.setOverallGPA(2.5);
		s.setOverallGPA(4.01);
		assertTrue(s.checkIfAtRisk());
	}

	@Test
	public void checkIfAtRiskOutOfBoundsNeg(){
		s.setOverallGPA(3.7);
		s.setOverallGPA(-7.5);
		assertFalse(s.checkIfAtRisk());
	}



}