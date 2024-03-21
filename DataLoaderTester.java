import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DataLoaderTester {
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
		students.clear();
		advisors.clear();
		courses = courseList.getCourses();
		degrees = degreeList.getDegrees();
		DataWriter.saveStudents();
		DataWriter.saveAdvisors();
	}
	
	@AfterEach
	public void tearDown() {
		userList.getAdvisors().clear();
		userList.getStudents().clear();
		DataWriter.saveStudents();
		DataWriter.saveAdvisors();
	}

	@Test
	void testEmptyStudentList() {
		assertEquals(userList.getStudents().size(), 0);
	}

	@Test
	void testEmptyAdvisorList() {
		assertEquals(userList.getAdvisors().size(), 0);
	}

	@Test
	void testLoadingCourses() {
		courses = DataLoader.getCourses();
		assertTrue(courses.size() > 1000);
	}

	@Test
	void testLoadingDegrees() {
		degrees = DataLoader.getDegrees();
		assertTrue(degrees.size() > 5);
	}
}

