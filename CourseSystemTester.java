import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Written by Alayna Wybranski

public class CourseSystemTester {
	
	private UserList userList = UserList.getInstance();
	private CourseList courseList = CourseList.getInstance();
	private CourseSystem system = new CourseSystem();
	private Student s;
	private Course c;
	private String att;

	@BeforeClass
	public void oneTimeSetup() {
		s = userList.getStudentFromStudentID("B001120");
		c = courseList.getCourseByIdentifer("CSCE145");
		att = courseList.getCoursesWithAttribute("AIU");
	}
	
	@AfterClass
	public void oneTimeTearDown() {
		
	}
	
	@BeforeEach
	public void setup() {
		
	}
	
	@AfterEach
	public void tearDown() {
		//runs after each test
	}

	//login testers
	@Test
	public void loginRegular(){
		assertTrue(system.login(s.getEmail(), s.getPassword()));
	}

	@Test
	public void loginNullEmail(){
		assertFalse(system.login(null, s.getPassword()));
	}

	@Test
	public void loginNullPassword(){
		assertFalse(system.login(s.getEmail(), null));
	}

	@Test
	public void loginWrongPassword(){
		assertFalse(system.login(s.getEmail(), "password"));
	}

	@Test
	public void loginNotAUser(){
		assertFalse(system.login("newperson", "newperson"));
	}

	@Test
	public void loginEmailIsWrongFormat(){
		assertTrue(system.login(s.getEmail().toUpperCase(), s.getPassword()));
	}

	//Sign Up testers
	@Test
	public void SignUpReg(){
		assertTrue(system.signUp(true, "jill", "dawn", "jdawn@email.com", "pass!"));
	}

	@Test
	public void SignUpNullName(){
		assertFalse(system.signUp(false, null, null, "nvick@gmail.com", "cat"));
	}

		@Test
	public void SignUpNullEmail(){
		assertFalse(system.signUp(false, "tim", "jones", null, "password1"));
	}

	@Test
	public void signUpNullPassowrd(){
		assertFalse(system.signUp(false, "lucy", "wick", "lwick@g.com", null));
	}

	@Test
	public void signUpEmptyStringName(){
		assertFalse(system.signUp(false, "", "", "gmail.com", "abc"));
	}

	@Test
	public void signUpEmptyStringEmail(){
		assertFalse(system.signUp(true, "a", "b", "", "abc"));
	}

	@Test
	public void signUpEmptyStringPass(){
		assertFalse(system.signUp(true, "n", "m", "nm@gmail.com", ""));
	}

	@Test
	public void signUpNameAleardyTaken(){
		assertTrue(system.signUp(true, s.getFirstName(), s.getLastName(), "hello", "luck"));
	}

	@Test
	public void signUpEmailAlreadyTaken(){
		assertFalse(system.signUp(true, "wyatt", "smith", s.getEmail(), "sWyatt"));
	}

	@Test
	public void signUpPersonAlreadyExists(){
		assertFalse(system.signUp(true, s.getFirstName(), s.getLastName(), s.getEmail(), s.getPassword()));
	}

	//Course by code testers
	@Test
	public void showCourseByCodeReg(){
		assertEquals(c.toString(), system.showCourseByCode("CSCE145"));
	}

	@Test
	public void showCourseByCodeEmptyString(){
		assertNull(system.showCourseByCode(""));
	}

	@Test
	public void showCourseByCodeNull(){
		assertNull(system.showCourseByCode(null));
	}

	@Test
	public void showCourseByCodeMistypedIdentifier(){
		assertEquals(c.toString(), system.showCourseByCode("cSCe145"));
	}

	@Test
	public void showCourseByCodeNotACourse(){
		assertNull(system.showCourseByCode("dog"));
	}

	//get courses with attributes testers
	@Test
	public void getCoursesWithAttributeReg(){
		assertEquals(att, system.getCoursesWithAttribute("AIU"));
	}

	@Test
	public void getCoursesWithAttributesMistyped(){
		assertEquals(att, system.getCoursesWithAttribute("aiU"));
	}

	@Test
	public void getCoursesWithAttributeNull(){
		String out = "Valid courses with attribute " + null + ":\n";
		assertEquals(out, system.getCoursesWithAttribute(null));
	}

	@Test
	public void getCoursesWithAttributeEmptyString(){
		String out = "Valid courses with attribute " +"" + ":\n";
		assertEquals(out, system.getCoursesWithAttribute(""));
	}

	@Test
	public void getCoursesWithAttributeNotAnAttribute(){
		String out = "Valid courses with attribute " + "cat" + ":\n";
		assertEquals(out, system.getCoursesWithAttribute("cat"));
	}

	// course description testers - by name and identifier
	@Test
	public void courseDescriptionSearchByNameReg(){
		assertEquals(c.getDescription(), system.courseDescriptionSearchByName("Algorithmic Design I"));
	}

	@Test
	public void courseDescriptionSearchByIdentifierReg(){
		assertEquals(c.getDescription(), system.courseDescriptionSearchByIdentifier("CSCE145"));
	}

	@Test
	public void courseDescriptionSearchByNameMistyped(){
		assertEquals(c.getDescription(), system.courseDescriptionSearchByName("algoRITHmic dESIgn i"));
	}

	@Test
	public void courseDescriptionSearchByIdentifierMistyped(){
		assertEquals(c.getDescription(), system.courseDescriptionSearchByIdentifier("csCE145"));
	}

	@Test
	public void courseDescriptionSearchByNameEmptyString(){
		assertNull(system.courseDescriptionSearchByName(""));
	}

	@Test
	public void courseDescriptionSearchByIdentifierEmptyString(){
		assertNull(system.courseDescriptionSearchByIdentifier(""));
	}

	@Test
	public void courseDescriptionSearchByNameNull(){
		assertNull(system.courseDescriptionSearchByName(null));
	}

	@Test
	public void courseDescriptionSearchByIdentifierNull(){
		assertNull(system.courseDescriptionSearchByIdentifier(null));
	}

	@Test
	public void courseDescriptionSearchByNameNotACourse(){
		assertNull(system.courseDescriptionSearchByName("Hello World"));
	}

	@Test
	public void courseDescriptionSearchByIdentifierNotACourse(){
		assertNull(system.courseDescriptionSearchByIdentifier("dogs101"));
	}

	//add Course testers

	//add grade testers

	//search by student ids testers
	
    
}
