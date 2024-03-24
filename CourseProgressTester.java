import static org.junit.jupiter.api.Assertions.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CourseProgressTester {
	private CourseProgress courseProgress;
	
	
	@BeforeEach
	public void setup() {
		//runs before each test
		courseProgress = new CourseProgress("CSCE247", Grade.B, false);
	}
	
	@AfterEach
	public void tearDown() {
		//runs after each test
		courseProgress = null;
	}

	//Beginning of testing methods

	@Test 
	void testEditCourseGrade(){
		boolean result = courseProgress.editCourseGrade(Grade.B_PLUS);
        assertTrue(result);
        assertEquals(Grade.B_PLUS, courseProgress.getCourseGrade());
	}
	@Test
    void testEditCourseGradeToNullGrade() {
        boolean result = courseProgress.editCourseGrade(null);
        assertFalse(result);
        assertEquals(Grade.B_PLUS, courseProgress.getCourseGrade());
    }
	@Test
	void testMakeCourseCompleteTrue(){
		courseProgress.makeCourseComplete();
        assertTrue(courseProgress.getCompletionStatus());
	}
	@Test
	void testMakeCourseCompleteFalse(){
		courseProgress.makeCourseUncomplete();
        courseProgress.makeCourseComplete();
        assertTrue(courseProgress.getCompletionStatus());
	}
	@Test
	void testMakeCourseInProgressNormal(){
		courseProgress.makeCourseInProgress();
        assertTrue(courseProgress.getCompletionStatus());
	}
	@Test
    void testMakeCourseInProgressAfterComplete() {
        courseProgress.makeCourseComplete();
        courseProgress.makeCourseInProgress();
        assertFalse(courseProgress.getCompletionStatus());
    }
	@Test
    void testMakeCourseInProgressWhenAlreadyInProgress() {
        courseProgress.makeCourseInProgress(); 
        assertFalse(courseProgress.getCompletionStatus());
    }
	@Test
	void testMakeCourseUncompleteNormal(){
		courseProgress.makeCourseComplete();
        courseProgress.makeCourseUncomplete();
        assertFalse(courseProgress.getCompletionStatus());
	}
	@Test
    void testMakeCourseUncompleteAfterComplete() {
        courseProgress.makeCourseComplete();
        courseProgress.makeCourseUncomplete();
        assertTrue(courseProgress.getCompletionStatus());
    }
}


