import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Written by Alayna Wybranski

public class CourseSystemTester {
	
	private UserList userList;
	private CourseList courseList;
	private CourseSystem system = new CourseSystem();
	private Student s;
	private Course c;
	private String att;
	private String reqText;

	@BeforeClass
	public void oneTimeSetup() {
		att = courseList.getCoursesWithAttribute("AIU");
		ArrayList<Requisite> reqs = c.getRequisites();
		for (Requisite requisite : reqs) {
			reqText += requisite + "\n";
		}
	}
	
	@AfterClass
	public void oneTimeTearDown() {
		userList=null;
	}
	
	@BeforeEach
	public void setup() {
		userList = UserList.getInstance();
		courseList= CourseList.getInstance();
		s = userList.getStudentFromStudentID("B001120");
		c = courseList.getCourseByIdentifer("CSCE145");
		system.login(s.getEmail(), s.getPassword());
	}
	
	@AfterEach
	public void tearDown() {
		
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
		assertFalse(system.signUp(true, "lucy", "wick", "lwick@g.com", null));
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

	// course req search - both by name and identifer - testers
	@Test
	public void courseRequistesSearchByNameReg(){
		assertEquals(reqText, system.courseRequistesSearchByName("Algorithmic Design I"));
	}

	@Test
	public void courseRequistesSearchByIdentiferReg(){
		assertEquals(reqText, system.courseRequistesSearchByIdentifer("CSCE145"));
	}

	@Test
	public void courseRequistesSearchByNameMistyped(){
		assertEquals(reqText, system.courseRequistesSearchByName("aLGORithmic dESIgn i"));
	}

	@Test
	public void courseRequistesSearchByIdentiferMistyped(){
		assertEquals(reqText, system.courseRequistesSearchByIdentifer("csCE145"));
	}

	@Test
	public void courseRequistesSearchByNameNull(){
		assertNull(system.courseRequistesSearchByName(null));
	}

	@Test
	public void courseRequistesSearchByIdentiferNull(){
		assertNull(system.courseRequistesSearchByIdentifer(null));
	}

	@Test
	public void courseRequistesSearchByNameEmptyString(){
		assertNull(system.courseRequistesSearchByName(""));
	}

	@Test
	public void courseRequistesSearchByIdentiferEmptyString(){
		assertNull(system.courseRequistesSearchByIdentifer(""));
	}

	@Test
	public void courseRequistesSearchByNameNotACourse(){
		assertNull(system.courseRequistesSearchByName("dog"));
	}

	@Test
	public void courseRequistesSearchByIdentiferNotACOurse(){
		assertNull(system.courseRequistesSearchByIdentifer("bear"));
	}

	// add grade testers
	@Test
	public void addGradeReg(){
		system.login(s.getEmail(), s.getPassword());
		s.addCourse("STAT509");
		assertTrue(system.addGrade("STAT509", Grade.A));
	}

	@Test
	public void addGradeMistyped(){
		s.addCourse("CHEM111L");
		assertTrue(system.addGrade("CheM111l", Grade.B));
	}

	@Test
	public void addgradeNotACourse(){
		assertFalse(system.addGrade("horse", Grade.C));
	}

	@Test
	public void addGradeEmptyString(){
		assertFalse(system.addGrade("", Grade.D));
	}

	@Test
	public void addGradeNull(){
		assertFalse(system.addGrade(null, Grade.C));
	}

	@Test
	public void addGradeNullGrade(){
		system.login(s.getEmail(), s.getPassword());
		assert(system.addGrade("CSCE145", null));
	}

	
    
}
