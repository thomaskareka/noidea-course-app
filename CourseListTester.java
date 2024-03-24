import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CourseListTester {
	private CourseList courseList;
	//private Advisor advisor;

	@BeforeEach
	public void setup() {
		//runs before each test
		courseList= CourseList.getInstance();

	}
	
	@AfterEach
	public void tearDown() {
		//runs after each test
		courseList=null;
	}
	
	//Beginning of testing methods

	@Test
    void testAddCourseWhenAdmin() {
        boolean added = courseList.addCourse(new Advisor("Admin", "Test", "email.sc.edu", true, "password"), "Software Engineering", "CSCE247", 3, "Description", new ArrayList<>(), new ArrayList<>(), "");
        assertTrue(added);
        assertEquals(added, courseList.getCourseByName("Software Engineering"));
    }

    @Test
    void testAddCourseWhenNotAdmin() {
        boolean added = courseList.addCourse(new Advisor("Non Admin", "Test", "email@sc.edu", false, "password"), "Software Engineering", "CSCE247", 3, "Description", new ArrayList<>(), new ArrayList<>(), "");
        assertFalse(added);
    }

    @Test
    void testContainsCourseWhenExists() {
		String courseName = "Software Engineering";
        Course testCourse = courseList.getCourseByName(courseName);
        assertNotNull(testCourse);
    }

    @Test
    void testContainsCourseWhenNotExists() {
        String courseName = "Nonexistent course here";
        Course testCourse = courseList.getCourseByName(courseName);
        assertNull(testCourse);
    }
}


