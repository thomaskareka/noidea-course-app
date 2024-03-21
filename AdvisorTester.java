import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class AdvisorTester {
	private Advisor testAdvisor;
	private Student testStudent;

	@BeforeClass
	public void oneTimeSetup() {
		users = users.getInstance;
	}
	
	@AfterClass
	public void oneTimeTearDown() {
		
	}
	
	public void testAddAdvisor() {
 
	}

	public void testSearch() {

	}

	public void testAddClass() {

	}

	public void testRemoveClass() {

	}

	public void testAddNotes() {
		Advisor.addNotes(tStudent, "Sample Notes");
	}

	public void testFailureRisk() {

	}


	@BeforeEach
	public void setup() {
		//runs before each test
	}
	
	@AfterEach
	public void tearDown() {
		//runs after each test
	}
}
