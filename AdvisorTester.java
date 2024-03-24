import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class AdvisorTester {
	private UserList userList = UserList.getInstance();
	private ArrayList<Student> students = userList.getStudents();
	private ArrayList<Advisor> advisors = userList.getAdvisors();
	UUID id = UUID.randomUUID();

	@BeforeEach
	public void setup() {
		//runs before each test
		userList.getAdvisors().clear();
		userList.getStudents().clear();
		DataWriter.saveStudents();
		DataWriter.saveAdvisors();

		userList.addAdvisorUser("AdivsorFriend", "Yay", "email@email.sc.edu", true, "password");
		userList.addStudentUser("StudentFriend", "Yay", "email@email.sc.edu", "Computer Science", "password");
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
        Student result = UserList.getInstance().getStudentFromID(id);
        assertNotNull(result);
		assertEquals(id.toString(), result.getStudentID());
    }

    @Test
    void testSearchByStudentIDWhenIdDoesNotExist() {
        Student result = UserList.getInstance().getStudentFromID(id);
        assertNull(result);
    }

    @Test
    void testSearchByStudentIDWhenIdIsNull() {
		Student result = UserList.getInstance().getStudentFromID(null);
        assertNull(result);
    }

	@Test 
	void testAddAdvisee(){

	}
	@Test 
	void testAddCourseForStudent(){

	}
	@Test 
	void removeCourseForStudent(){

	}
	@Test 
	void testAddNotes(){

	}
	@Test 
	void testEnterFailureRisk(){
		
	}
	@Test 
	void testRemoveFailureRisk(){

	}
	

}
