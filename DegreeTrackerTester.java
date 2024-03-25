import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DegreeTrackerTester {
	private UserList userList = UserList.getInstance();
	DegreeTracker degreeTracker;
	private Student student;
	ArrayList<CourseProgress> courses = new ArrayList<>();
	CourseProgress cp1;
	CourseProgress cp2;
	CourseProgress cp3;
	CourseProgress cp4;
	CourseProgress cp5;
	CourseProgress cp6;
	CourseProgress cp7;
	CourseProgress cp8;
	CourseProgress cp9;
	CourseProgress cp10;
	CourseProgress cp11;

	
	@BeforeClass
	public void oneTimeSetup() {

	}
	
	@AfterClass
	public void oneTimeTearDown() {
		
	}
	
	@BeforeEach
	public void setup() {
		cp1 = new CourseProgress("CSCE520", Grade.A, true);
		cp2 = new CourseProgress("ENGL101", Grade.A, true);
		cp3 = new CourseProgress("CSCE247", Grade.IN_PROGRESS, false);
		cp4 = new CourseProgress("CSCE145", Grade.A, true);
		cp5 = new CourseProgress("CSCE146", Grade.A, true);
		cp6 = new CourseProgress("CSCE190", Grade.A, true);
		cp7 = new CourseProgress("CSCE215", Grade.A, true);
		cp8 = new CourseProgress("MATH141", Grade.A, true);
		cp9 = new CourseProgress("MATH142", Grade.B, true);
		cp10 = new CourseProgress("PSYC101", Grade.B, true);
		cp11 = new CourseProgress("CSCE211", Grade.B, true);

	}
	
	@AfterEach
	public void tearDown() {
		//runs after each test
	}

	@Test
    public void testGetGradeLevel_Zero() {
		degreeTracker = new DegreeTracker(courses);
        assertEquals("Freshman", degreeTracker.getGradeLevel());
    }

    @Test
    public void testGetGradeLevel_Sophomore() {
		courses.add(cp1);
		courses.add(cp2);
		courses.add(cp3);
		courses.add(cp4);
		courses.add(cp5);
		courses.add(cp6);
		courses.add(cp7);
		courses.add(cp8);
		courses.add(cp9);
		courses.add(cp10);
		courses.add(cp11);
		degreeTracker = new DegreeTracker(courses);
        assertEquals("Sophomore", degreeTracker.getGradeLevel());
    }

	@Test
    public void testGetCompletedCredits_AllCompletedCourses() {
		courses.add(cp9);
		courses.add(cp10);
		courses.add(cp11);
		degreeTracker = new DegreeTracker(courses);
        assertEquals(10, degreeTracker.getCompletedCredits());
    }

    @Test
    public void testGetCompletedCredits_SomeInProgressCourses() {
		courses.add(cp2);
		courses.add(cp3);
		courses.add(cp4);
		degreeTracker = new DegreeTracker(courses);
        assertEquals(7, degreeTracker.getCompletedCredits());
    }

    @Test
    public void testGetCompletedCredits_NoCompletedCourses() {
		courses.add(cp3);
		degreeTracker = new DegreeTracker(courses);
		assertEquals(0, degreeTracker.getCompletedCredits());
    }

	@Test
    public void testCalculateGPA_MixOfGrades() {
		courses.add(cp4);
		courses.add(cp5);
		courses.add(cp9);
		courses.add(cp10);
		degreeTracker = new DegreeTracker(courses);
		double actual = degreeTracker.CalculateGPA();
        assertEquals(3.533333333333333, actual);
    }

    @Test
    public void testCalculateGPA_AllGradesA() {
		courses.add(cp4);
		courses.add(cp5);
		courses.add(cp6);
		courses.add(cp7);
		degreeTracker = new DegreeTracker(courses);
		double actual = degreeTracker.CalculateGPA();
        assertEquals(4.00, actual);
    }

    @Test
    public void testCalculateGPA_NoGrades() {
		degreeTracker = new DegreeTracker(courses);
		double actual = degreeTracker.CalculateGPA();
        assertEquals(0.00, actual);
    }

	@Test
    public void testCalculateMajorGPA_AllCoursesMajor() {
		courses.add(cp1);
		courses.add(cp4);
		courses.add(cp5);
		degreeTracker = new DegreeTracker(courses);
		double actual = degreeTracker.CalculateMajorGPA();
        assertEquals(4.00, actual);
    }

	@Test
    public void testCalculateMajorGPA_MixCourses() {
		courses.add(cp1);
		courses.add(cp2);
		courses.add(cp4);
		courses.add(cp10); //non major + B course
		degreeTracker = new DegreeTracker(courses);
		double actual = degreeTracker.CalculateMajorGPA();
        assertEquals(4.00, actual);
    }

	@Test
    public void testCalculateMajorGPA_NoMajorCourses() {
		courses.add(cp2);
		courses.add(cp8);
		courses.add(cp9); 
		degreeTracker = new DegreeTracker(courses);
		double actual = degreeTracker.CalculateMajorGPA();
        assertEquals(0.00, actual);
    }

	//failed division by zero
	@Test
    public void testCalculateProgress_ZeroCompleted() {
		degreeTracker = new DegreeTracker(courses);
        assertEquals(0.0, degreeTracker.CalculateProgress(), 0.01);
    }

	//failed division by zer0
    @Test
    public void testCalculateProgress_SomeCreditsCompleted() {
		courses.add(cp1);
		courses.add(cp2);
		courses.add(cp3); 
		courses.add(cp4);
		courses.add(cp5);
		courses.add(cp6); 
		degreeTracker = new DegreeTracker(courses);
        assertEquals(24.0, degreeTracker.CalculateProgress(), 0.01);
    }

	@Test
    public void testGetCourseGrade_CourseProgressFound() {
		courses.add(cp1);
		courses.add(cp5);
		courses.add(cp6); 
		degreeTracker = new DegreeTracker(courses);
        assertEquals("A", degreeTracker.getCourseGrade("Database System Design", "CSCE520"));
    }

    @Test
    public void testGetCourseGrade_CourseProgressNotFound() {
		courses.add(cp5);
		courses.add(cp6); 
		degreeTracker = new DegreeTracker(courses);
        assertNull("A", degreeTracker.getCourseGrade("Database System Design", "CSCE520"));
    }

    @Test
    public void testGetCourseGrade_NoCourses() {
		degreeTracker = new DegreeTracker(courses);
        assertEquals(null, degreeTracker.getCourseGrade("Database System Design", "CSCE520"));
    }

	@Test
    public void testAddGrade_AddGradeToInProgress() {
		courses.add(cp3);
		courses.add(cp6); 
		degreeTracker = new DegreeTracker(courses);
		degreeTracker.addGrade("CSCE247", Grade.B);
        assertEquals("B", degreeTracker.getCourseGrade("Software Engineering", "CSCE247"));
    }

    @Test
    public void testAddGrade_AlreadyGraded() {
		courses.add(cp3);
		courses.add(cp1); 
		degreeTracker = new DegreeTracker(courses);
		degreeTracker.addGrade("CSCE520", Grade.B);
        assertEquals("B", degreeTracker.getCourseGrade("Database System Design", "CSCE520"));
    }

    
    @Test
    public void testGetIncompleteCourses_SomeIncompleteCourses() {
		courses.add(cp3); 
		courses.add(cp4);
		courses.add(cp5);
		courses.add(cp6); 
		degreeTracker = new DegreeTracker(courses);
        ArrayList<String> expectedIncompleteCourses = new ArrayList<>();
        expectedIncompleteCourses.add("CSCE247");
        assertEquals(expectedIncompleteCourses, degreeTracker.GetIncompleteCourses());
    }

    @Test
    public void testGetIncompleteCourses_NoIncompleteCourses() {
		courses.add(cp4);
		courses.add(cp5);
		courses.add(cp6); 
		degreeTracker = new DegreeTracker(courses);
        ArrayList<String> expectedIncompleteCourses = new ArrayList<>();
        assertEquals(expectedIncompleteCourses, degreeTracker.GetIncompleteCourses());
    }

    @Test
    public void testGetIncompleteCourses_NoCourses() {
		degreeTracker = new DegreeTracker(courses);
        ArrayList<String> expectedIncompleteCourses = new ArrayList<>();
        assertEquals(expectedIncompleteCourses, degreeTracker.GetIncompleteCourses());
    }

	@Test
    public void testGetCompleteCourses_SomeCompleteCourses() {
		courses.add(cp3); 
		courses.add(cp4);
		courses.add(cp5);
		courses.add(cp6); 
		degreeTracker = new DegreeTracker(courses);
        ArrayList<String> expectedCompleteCourses = new ArrayList<>();
        expectedCompleteCourses.add("CSCE145");
		expectedCompleteCourses.add("CSCE146");
		expectedCompleteCourses.add("CSCE190");
        assertEquals(expectedCompleteCourses, degreeTracker.GetCompleteCourses());
    }

    @Test
    public void testGetCompleteCourses_NoCompleteCourses() {
		courses.add(cp3);
		degreeTracker = new DegreeTracker(courses);
        ArrayList<String> expectedCompleteCourses = new ArrayList<>();
        assertEquals(expectedCompleteCourses, degreeTracker.GetCompleteCourses());
    }

    @Test
    public void testGetCompleteCourses_NoCourses() {
		degreeTracker = new DegreeTracker(courses);
        ArrayList<String> expectedCompleteCourses = new ArrayList<>();
        assertEquals(expectedCompleteCourses, degreeTracker.GetCompleteCourses());
    }

	@Test
    public void testGetCourses_SomeCourses() {
		courses.add(cp3); 
		courses.add(cp4);
		courses.add(cp5);
		courses.add(cp6); 
		degreeTracker = new DegreeTracker(courses);
        ArrayList<String> expectedCourses = new ArrayList<>();
        expectedCourses.add("CSCE247");
        expectedCourses.add("CSCE145");
		expectedCourses.add("CSCE146");
		expectedCourses.add("CSCE190");
        assertEquals(expectedCourses, degreeTracker.getCourses());
    }

    @Test
    public void testGetCourses_NoCourses() {
		degreeTracker = new DegreeTracker(courses);
        ArrayList<String> expectedCourses = new ArrayList<>();
        assertEquals(expectedCourses, degreeTracker.getCourses());
    }


    @Test
    public void testAddCourse_SuccessfulAddition() {
		degreeTracker = new DegreeTracker(courses);
        assertTrue(degreeTracker.addCourse("CSCE101"));
    }

    @Test
    public void testAddCourse_AlreadyAdded() {
		courses.add(cp5);
		degreeTracker = new DegreeTracker(courses);
        assertFalse(degreeTracker.addCourse("CSCE146"));
    }

	//doesnt work
	@Test
    public void testRemoveCourse_SuccessfulRemoval() {
		courses.add(cp5);
		degreeTracker = new DegreeTracker(courses);
        assertTrue(degreeTracker.removeCourse("CSCE146"));
    }

	@Test
    public void testRemoveCourse_CourseNotInList() {
		courses.add(cp6);
		degreeTracker = new DegreeTracker(courses);
        assertFalse(degreeTracker.removeCourse("CSCE146"));
    }

	@Test
    public void testRequirementCreditsCompleted_SomeCreditsCompleted() {
		courses.add(cp3); 
		courses.add(cp4);
		courses.add(cp5);
		courses.add(cp6); 
		courses.add(cp7);
		degreeTracker = new DegreeTracker(courses);
        assertEquals(10, degreeTracker.requirementCreditsCompleted("CSCE")); 
    }

	//not sure what we want from this method 
    @Test
    public void testRequirementCreditsCompleted_MixOfRequiredAndNot() {
		courses.add(cp3); 
		courses.add(cp4);
		courses.add(cp5);
		courses.add(cp10); 
		courses.add(cp11);
		degreeTracker = new DegreeTracker(courses);
        assertEquals(8, degreeTracker.requirementCreditsCompleted("CSCE")); 
    }

	//need to figure out above
	@Test
    public void testRequirementCreditsRemaining_SomeCreditsRemaining() {
		courses.add(cp3); 
		courses.add(cp4);
		courses.add(cp5);
		courses.add(cp10); 
		courses.add(cp11);
		degreeTracker = new DegreeTracker(courses);
        assertEquals(0, degreeTracker.requirementCreditsRemaining("CSCE", "Computer Science")); 
    }

	@Test
    public void testGetAllCourses_SomeCourses() {
		courses.add(cp3); 
		courses.add(cp4);
		courses.add(cp5);
		degreeTracker = new DegreeTracker(courses);
		String expected = "CSCE247: Software Engineering\n" + 
						  "CSCE145: Algorithmic Design I\n" +
						  "CSCE146: Algorithmic Design II\n";
        assertEquals(expected, degreeTracker.getAllCourses());
    }

    @Test
    public void testGetAllCourses_NoCourses() {
		degreeTracker = new DegreeTracker(courses);
		String expected = "";
        assertEquals(expected, degreeTracker.getAllCourses());
    }

	@Test
    public void testCreateTranscript_SomeCourses() {
		courses.add(cp3); 
		courses.add(cp4);
		courses.add(cp5);
		degreeTracker = new DegreeTracker(courses);
		String expected = "8 credits completed.\n" +
						  "CSCE247: Software Engineering - IN_PROGRESS\n" + 
						  "CSCE145: Algorithmic Design I - A\n" +
						  "CSCE146: Algorithmic Design II - A\n";
        assertEquals(expected, degreeTracker.createTranscipt());
    }

    @Test
    public void testCreateTranscript_NoCourses() {
		degreeTracker = new DegreeTracker(courses);
		String expected = "0 credits completed.\n";
        assertEquals(expected, degreeTracker.createTranscipt());
    }
	
}

