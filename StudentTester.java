import static org.junit.jupiter.api.Assertions.*;

//import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Written by Alayna Wybranski

public class StudentTester {
	private UserList userList = UserList.getInstance();
	private Student s;

	@BeforeClass
	public void oneTimeSetup() {
	}
	
	@AfterClass
	public void oneTimeTearDown() {
	
	}
	
	@BeforeEach
	public void setup() {
		s = userList.getStudentFromStudentID("B001120");
	}
	
	@AfterEach
	public void tearDown() {
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

	@Test
	public void editFailureRiskFalse(){
		s.editFailureRisk(false);
		assertFalse(s.getFailureRisk());
	}

	@Test
	public void editFailureRiskTrue(){
		s.editFailureRisk(true);
		assertTrue(s.getFailureRisk());
	}

	@Test
	public void addCourseNull(){
		assertFalse(s.addCourse(null));
	}

	@Test
	public void addCourseNotACourse(){
		assertFalse(s.addCourse("dog"));
	}

	@Test
	public void addCourseRegular(){
		assertTrue(s.addCourse("CSCE146"));
	}

	@Test
	public void addCourseIncorrectFormat(){
		assertTrue(s.addCourse("cScE145"));
	}

	@Test
	public void removeCourseRegular(){
		s.addCourse("CHEM112");
		assertTrue(s.removeCourse("CHEM112"));
	}

	@Test 
	public void removeCourseNull(){
		assertFalse(s.removeCourse(null));
	}

	@Test
	public void removeCourseIncorrectFormat(){
		s.addCourse("ENGL102");
		assertTrue(s.removeCourse("enGl102"));
	}

	@Test
	public void removeCourseNotACoure(){
		assertFalse(s.removeCourse("cat"));
	}

	@Test 
	public void addNotesNull(){
		assertFalse(s.addNotes(null));
	}

	@Test 
	public void addNotesEmptyString(){
		assertFalse(s.addNotes(""));
	}

	@Test
	public void addNotesRegular(){
		assertTrue(s.addNotes("New notes"));
	}

	@Test
	public void addGradeRegular(){
		s.addCourse("CSCE145");
		assertTrue(s.addGrade("CSCE145", Grade.A));
	}

	@Test 
	public void addGradeNullCourse(){
		assertFalse(s.addGrade(null, Grade.B_PLUS));
	}

	@Test
	public void addGradeNullGrade(){
		s.addCourse("CHEM112");
		assertFalse(s.addGrade("CHEM112", null));
	}

	@Test
	public void addGradeNotACourse(){
		assertFalse(s.addGrade("CSCS002", Grade.D));
	}









}