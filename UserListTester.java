import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserListTester {
	private UserList userList = UserList.getInstance();
    private User expected;
    private Student student;
	
	@BeforeClass
	public void oneTimeSetup() {
		
	}
	
	@AfterClass
	public void oneTimeTearDown() {
		
	}
	
	@BeforeEach
	public void setup() {
        expected = userList.login("janedoe@example.com", "f46fab9f9f91073a4262a6bce61dc3d05ad0a077");
        student = userList.getStudentFromStudentID("B001120");

        //sign = userList.signUp(true, "example", "case", "examplecase@example.com", "example");

	}
	
	@AfterEach
	public void tearDown() {
		//runs after each test
	}

    @Test
    public void testLogin_InvalidEmail() {
        assertNull(userList.login("invalid@example.com", "password"));
    }

    @Test
    public void testLogin_InvalidPassword() {
        assertNull(userList.login("advisor1@example.com", "wrongpassword"));
    }

    @Test
    public void testLogin_NullEmail() {
        assertNull(userList.login(null, "password"));
    }

    @Test
    public void testLogin_NullPassword() {
        assertNull(userList.login("advisor1@example.com", null));
    }

    @Test
    public void testLogin_EmptyEmail() {
        assertNull(userList.login("", "password"));
    }

    @Test
    public void testLogin_EmptyPassword() {
        assertNull(userList.login("advisor1@example.com", ""));
    }

    @Test
    public void testSignUp_UserAlreadyExists() {
        assertNotNull(userList.signUp(true, "Jane", "Doe", "janedoe@example.com", "f46fab9f9f91073a4262a6bce61dc3d05ad0a077"));
    }

    @Test
    public void testSignUp_UserAlreadyExistsThenSignIn() {
        assertEquals(expected, userList.signUp(true, "Jane", "Doe", "janedoe@example.com", "f46fab9f9f91073a4262a6bce61dc3d05ad0a077"));
    }

    //ask about these 4 how to handle improper log in
    @Test
    public void testSignUp_NullEmail() {
        assertNull(userList.signUp(true, "John", "Doe", null, "password"));
    }

    @Test
    public void testSignUp_NullPassword() {
        assertNull(userList.signUp(true, "John", "Doe", "john@example.com", null));
    }

    @Test
    public void testSignUp_EmptyEmail() {
        assertNull(userList.signUp(true, "John", "Doe", "", "password"));
    }

    @Test
    public void testSignUp_EmptyPassword() {
        assertNull(userList.signUp(true, "John", "Doe", "john@example.com", ""));
    }



	@Test
    public void testGetStudentByEmail_ValidEmailFound() {
        assertEquals(expected, userList.getStudentByEmail("janedoe@example.com"));
    }

    @Test
    public void testGetStudentByEmail_ValidEmailNotFound() {
        assertNull(userList.getStudentByEmail("unknown@example.com"));
    }

    @Test
    public void testGetStudentByEmail_InvalidEmailFormat() {
        assertNull(userList.getStudentByEmail("invalid_email_format"));
    }

    @Test
    public void testGetStudentByEmail_NullEmail() {
        assertNull(userList.getStudentByEmail(null));
    }

    @Test
    public void testGetStudentFromID_ExistingID() {
        assertEquals(expected, userList.getStudentFromID(expected.getID()));
    }

    @Test
    public void testGetStudentFromID_NonExistingID() {
        assertNull(userList.getStudentFromID(UUID.randomUUID()));
    }

    @Test
    public void testGetStudentFromID_NullID() {
        assertNull(userList.getStudentFromID(null));
    }

    @Test
    public void testCreateUserTranscript_ExistingID() {
        String expectedTranscript = "6 credits completed.\nENGL101: Critical Reading and Composition - B\nCSCE520: Database System Design - A\nCSCE247: Software Engineering - IN_PROGRESS\n";
        assertEquals(expectedTranscript, userList.createUserTranscript(userList.getStudents().get(0).getID()));
    }

    @Test
    public void testCreateUserTranscript_NonExistingID() {
        assertEquals("", userList.createUserTranscript(UUID.randomUUID()));
    }

    @Test
    public void testCreateUserTranscript_NullID() {
        assertEquals("", userList.createUserTranscript(null));
    }

    @Test
    public void testGetAllStudentCourses_ExistingID() {
        String expectedCourses = "ENGL101: Critical Reading and Composition\n" +
                                "CSCE520: Database System Design\n" +
                                "CSCE247: Software Engineering\n";
        assertEquals(expectedCourses, userList.getAllStudentCourses(userList.getStudents().get(0).getID()));
    }

    @Test
    public void testGetAllStudentCourses_NonExistingID() {
        assertEquals("", userList.getAllStudentCourses(UUID.randomUUID()));
    }

    @Test
    public void testGetAllStudentCourses_NullID() {
        assertEquals("", userList.getAllStudentCourses(null));
    }

    @Test
    public void testAddCourseForStudent() {
        String courses = "ENGL101: Critical Reading and Composition\n" +
                         "CSCE520: Database System Design\n" +
                         "CSCE247: Software Engineering\n" +
                         "MATH141: Calculus I\n";
        userList.addCourseForStudent(student, "MATH141");
        assertEquals(courses, userList.getAllStudentCourses(userList.getStudents().get(0).getID()));
    }

    @Test
    public void testAddCourseForStudent_DuplicateCourse() {
        String courses = "ENGL101: Critical Reading and Composition\n" +
                         "CSCE520: Database System Design\n" +
                         "CSCE247: Software Engineering\n" +
                         "MATH141: Calculus I\n";
        userList.addCourseForStudent(student, "MATH141");
        userList.addCourseForStudent(student, "MATH141");
        assertEquals(courses, userList.getAllStudentCourses(userList.getStudents().get(0).getID()));
    }

    //throws null pointer because it cant handle unknown class
    @Test
    public void testAddCourseForStudent_NullCourse() {
        String courses = "ENGL101: Critical Reading and Composition\n" +
                         "CSCE520: Database System Design\n" +
                         "CSCE247: Software Engineering\n";
        userList.addCourseForStudent(student, "MATH");
        assertEquals(courses, userList.getAllStudentCourses(userList.getStudents().get(0).getID()));

    }

    //remove doesnt work
    @Test
    public void testRemoveCourseForStudent() {
        String courses = "ENGL101: Critical Reading and Composition\n" +
                         "CSCE520: Database System Design\n";
        userList.removeCourseForStudent(student, "CSCE247");
        assertEquals(courses, userList.getAllStudentCourses(userList.getStudents().get(0).getID()));
    }

    @Test
    public void testRemoveCourseForStudent_NonExistingCourse() {
        String courses = "ENGL101: Critical Reading and Composition\n" +
                         "CSCE520: Database System Design\n" +
                         "CSCE247: Software Engineering\n";
        userList.removeCourseForStudent(student, "MATH");
        assertEquals(courses, userList.getAllStudentCourses(userList.getStudents().get(0).getID()));
    }

    @Test
    public void testRemoveCourseForStudent_NullCourse() {
        String courses = "ENGL101: Critical Reading and Composition\n" +
                         "CSCE520: Database System Design\n" +
                         "CSCE247: Software Engineering\n";
        userList.removeCourseForStudent(student, null);
        assertEquals(courses, userList.getAllStudentCourses(userList.getStudents().get(0).getID()));
    }

    @Test
    public void testAddGradeValid() {
        assertFalse(userList.addGrade(student, "CSCE247", Grade.A));
    }

    @Test
    public void testAddGrade_AlreadyGraded() {
        assertFalse(userList.addGrade(student, "CSCE520", Grade.A));
    }

    //cant handle unknown course
    @Test
    public void testAddGrade_NonExistingCourse() {
        assertFalse(userList.addGrade(student, "MATH", Grade.A));
    }

    //cant handle null course
    @Test
    public void testAddGrade_NullCourse() {
        assertFalse(userList.addGrade(student, null, Grade.A));
    }

    //cant handle null grade
    @Test
    public void testAddGrade_NullGrade() {
        assertFalse(userList.addGrade(student, "CSCE247", null));
    }

    @Test
    public void testGetStudentIncompleteCourses_NoIncompleteCourses() {
        userList.addGrade(student, "CSCE247", Grade.A);
        assertEquals("", userList.getStudentIncompleteCourses(student));
    }

    @Test
    public void testGetStudentIncompleteCourses_SomeIncompleteCourses() {
        String expected = "CSCE247\n";
        assertEquals(expected, userList.getStudentIncompleteCourses(student));
    }

    @Test
    public void testGetStudentIncompleteCourses_AllIncompleteCourses() {
        userList.addGrade(student, "ENGL101", Grade.IN_PROGRESS);
        userList.addGrade(student, "CSCE520", Grade.IN_PROGRESS);
        String expected = "ENGL101\n" +
                          "CSCE520\n" +
                          "CSCE247\n";
        assertEquals(expected, userList.getStudentIncompleteCourses(student));
    }

    @Test
    public void testGetStudentCompleteCourses_NoCompleteCourses() {
        userList.addGrade(student, "ENGL101", Grade.IN_PROGRESS);
        userList.addGrade(student, "CSCE520", Grade.IN_PROGRESS);
        assertEquals("", userList.getStudentCompleteCourses(student));
    }

    @Test
    public void testGetStudentCompleteCourses_SomeCompleteCourses() {
        String expected = "ENGL101\n" +
                          "CSCE520\n";
        assertEquals(expected, userList.getStudentCompleteCourses(student));
    }

    @Test
    public void testGetStudentCompleteCourses_AllCompleteCourses() {
        userList.addGrade(student, "CSCE247", Grade.A);
        String expected = "ENGL101\n" +
                          "CSCE520\n" +
                          "CSCE247\n";
        assertEquals(expected, userList.getStudentCompleteCourses(student));
    }
    

}

