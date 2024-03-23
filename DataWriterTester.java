import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DataWriterTester {
	private DegreeList degreeList = DegreeList.getInstance();
	private UserList userList = UserList.getInstance();
	private CourseList courseList = CourseList.getInstance();
	
	private ArrayList<Student> students = userList.getStudents();
	private ArrayList<Advisor> advisors = userList.getAdvisors();
	private ArrayList<Course> courses = courseList.getCourses();
	private ArrayList<Degree> degrees = degreeList.getDegrees();
	
	@BeforeClass
	public void oneTimeSetup() {
		
	}
	
	@AfterClass
	public void oneTimeTearDown() {
		
	}
	
	@BeforeEach
	public void setup() {
		UserList.getInstance().getStudents().clear();
		UserList.getInstance().getAdvisors().clear();
		DataWriter.saveStudents();
		DataWriter.saveAdvisors();
	}
	
	@AfterEach
	public void tearDown() {
		UserList.getInstance().getStudents().clear();
		UserList.getInstance().getAdvisors().clear();
		DataWriter.saveStudents();
		DataWriter.saveAdvisors();
	}

	@Test 
	void testWritingNoStudents() {
		DataWriter.saveStudents();
		assertEquals(0, DataLoader.getStudents().size());
	}

	@Test 
	void testWritingNullStudent() {
		students = userList.getStudents();
		students.add(null);
		DataWriter.saveStudents();
		assertEquals(0, DataLoader.getStudents().size());
	}

	@Test
	void testWritingNewStudent() {
		students = userList.getStudents();
		students.add(new Student("testF", "testL", "test@email.sc.edu", "Computer Information Systems", "password"));
		DataWriter.saveStudents();
		assertEquals("testF", DataLoader.getStudents().get(0).getFirstName());
	}

	@Test
	void testDuplicateManuallyAddedStudents() {
		students = userList.getStudents();
		students.add(new Student("testF", "testL", "test@email.sc.edu", "Computer Information Systems", "password"));
		students.add(new Student("testF", "testL", "test@email.sc.edu", "Computer Information Systems", "password"));

		DataWriter.saveStudents();
		assertEquals(DataLoader.getStudents().get(0), DataLoader.getStudents().get(1));
	}

	@Test
	void testWritingDuplicateStudentsFromSignup() {
		students = userList.getStudents();
		userList.signUp(true, "testF", "testL", "test@email.sc.edu", "password");
		userList.signUp(true, "testF", "testL", "test@email.sc.edu", "password");
		userList.signUp(true, "testF", "testL", "test@email.sc.edu", "password");
		DataWriter.saveStudents();
		assertEquals(1, DataLoader.getStudents().size());
	}

	@Test 
	void testWritingNoAdvisors() {
		DataWriter.saveStudents();
		assertEquals(0, DataLoader.getAdvisors().size());
	}

	@Test 
	void testWritingNullAdvisor() {
		advisors = userList.getAdvisors();
		advisors.add(null);
		DataWriter.saveAdvisors();
		assertEquals(0, DataLoader.getAdvisors().size());
	}
}
