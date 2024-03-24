import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class AdvisorTester {
	private Advisor advisor;
    private Student student;
    private UserList userList = UserList.getInstance();

	@BeforeEach
	public void setup() {
		//runs before each test
        userList.getAdvisors().clear();
		userList.getStudents().clear();
		DataWriter.saveStudents();
		DataWriter.saveAdvisors();

		advisor = new Advisor("testAdvisor", "lastName", "email", true, "password");
		student= new Student("testStudent", "lastName", "email", "CS", "password");
		student = userList.getStudentFromStudentID("B001121");

	}
	
	@AfterEach
	public void tearDown() {
		userList.getAdvisors().clear();
		userList.getStudents().clear();
		DataWriter.saveStudents();
		DataWriter.saveAdvisors();
	}

	//Beginning of testing methods

	@Test
    void testSearchByStudentIDWhenIdExists() {
        Student result = userList.getStudentFromStudentID("B001121");
        assertNotNull(result);
        assertEquals("B001121", result.getStudentID());
	}

    @Test
    void testSearchByStudentIDWhenIdDoesNotExist() {
        Student result = userList.getStudentFromStudentID("B001121");
        assertNull(result);
    }

    @Test
    void testSearchByStudentIDWhenIdIsNull() {
		Student result = userList.getStudentFromStudentID(null);
        assertNull(result);
    }

	@Test 
	void testAddAdviseeWhenAdviseeIsNotAlreadyAdded(){
		assertTrue(advisor.getStudents().contains(student.getStudentID()));
	}

	@Test
    void testAddAdviseeWhenAdviseeAlreadyAdded() {
		assertEquals("B001121", student.getStudentID());
    }
	@Test
    void testAddAdviseeWhenAdviseeDoesNotExist() {
		Student result = userList.getStudentFromStudentID(null);
		assertNull(result);
    }
	@Test 
	void testAddCourseForStudentNormal(){
		assertTrue(student.addCourse("CSCE247"));
	}
	@Test 
	void testAddCourseForStudentNotValid(){
		assertTrue(student.addCourse("CsCe 247"));;
	}
	@Test 
	void testAddCourseForStudentNull(){
		assertNull(student.addCourse(null));;
	}
	@Test 
	void removeCourseForStudentNormal(){
		student.addCourse("CSCE242");
		assertTrue(student.removeCourse("CSCE242"));
	}
	@Test 
	void removeCourseForStudentNotValid(){
		assertTrue(student.removeCourse("CsCe 242"));
	}
	@Test 
	void testRemoveCourseForStudentNull(){
		assertNull(student.removeCourse(null));
	}
	@Test 
	void testAddNotesNormal(){
		assertTrue(student.addNotes("notes"));
	}
	@Test
	void testAddNotesNull(){
		assertNull(student.addNotes(null));
	}
	@Test
	void testAddNotesEmpty(){
		assertTrue(student.addNotes(" "));
	}
	@Test 
	void testEnterFailureRiskWhenAtRisk(){
		boolean rf= student.checkIfAtRisk();
		if (student.checkIfAtRisk())
		assertTrue(rf);
	}
	@Test
    void testEnterFailureRiskWhenNotAtRisk() {
        boolean rf= student.checkIfAtRisk();
		if (!student.checkIfAtRisk())
        assertFalse(rf);
    }
	@Test 
	void testRemoveFailureRiskTrue(){
		student.editFailureRisk(true);
        assertTrue(student.getFailureRisk());
	}
	@Test 
	void testRemoveFailureRiskFalse(){
		student.editFailureRisk(false);
        assertFalse(student.getFailureRisk());
	}
	@Test 
	void testIsAdminTrue(){
		if(advisor.isAdmin())
		assertTrue(advisor.isAdmin());
	}
	@Test 
	void testIsAdminFalse(){
		if(!advisor.isAdmin())
		assertFalse(advisor.isAdmin());
	}

}
