import static org.junit.Assert.assertNotNull;
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

	private ArrayList<Student> studentBackup = new ArrayList<Student>(students);
	private ArrayList<Advisor> advisorBackup = new ArrayList<Advisor>(advisors);
	
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
		userList.getStudents().clear();
		userList.getAdvisors().clear();
		userList.setStudents(studentBackup);
		userList.setAdvisors(advisorBackup);
		DataWriter.saveStudents();
		DataWriter.saveAdvisors();
	}

	@Test
	void testEmptyStudentList() {
		assertEquals(userList.getStudents().size(), 0);
	}

	void testLoadingNullStudents() {
		userList.getStudents().add(null);
		DataWriter.saveStudents();

		assertEquals(DataLoader.getStudents().size(), 0);
	}

	@Test
	void testEmptyAdvisorList() {
		assertEquals(userList.getAdvisors().size(), 0);
	}

	void testLoadingNullAdvisors() {
		userList.getAdvisors().add(null);
		DataWriter.saveAdvisors();

		assertEquals(DataLoader.getAdvisors().size(), 0);
	}

	@Test
	void testLoadingCourses() {
		courses = DataLoader.getCourses();
		assertTrue(courses.size() > 1000);
	}

	@Test
	void testCoursesUnique() {
		courses = DataLoader.getCourses();
		assertNotEquals(courses.get(0), courses.get(1));
	}

	@Test
	void testCourseLoadDescription() {
		courses = DataLoader.getCourses();
		assertNotNull(courses.get(0).getDescription());
	}

	@Test
	void testCourseLoadName() {
		courses = DataLoader.getCourses();
		assertNotNull(courses.get(0).getName());
	}

	@Test
	void testCourseExists() {
		courses = DataLoader.getCourses();
		for(Course c : courses) {
			if(c.getIdentifier().equals("CSCE247")) {
				assertEquals(c.getName(), "Software Engineering");
			}
		}
	}

	@Test
	void testLoadingDegrees() {
		degrees = DataLoader.getDegrees();
		assertTrue(degrees.size() > 5);
	}

	@Test
	void testDegreeRequirementsNotNull() {
		degrees = DataLoader.getDegrees();
		assertNotNull(degrees.get(0).getRequirements());
	}

	@Test
	void testDegreeRequirementsLoading() {
		degrees = DataLoader.getDegrees();
		assertEquals(degrees.get(0).getRequirements().get(0).getCategory(), "CC Communication and Writing");
	}

	@Test
	void testDegreeType() {
		degrees = DataLoader.getDegrees();
		assertEquals(degrees.get(0).getType(), "major");
	}
}

