import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DegreeListTester {
	private DegreeList degreeList = DegreeList.getInstance();

	@BeforeClass
	public void oneTimeSetup() {
		
	}
	
	@AfterClass
	public void oneTimeTearDown() {
		
	}
	
	@BeforeEach
	public void setup() {
		degreeList = degreeList.getInstance();
	}
	
	@AfterEach
	public void tearDown() {
		//runs after each test
	}

	@Test
	void loadValidMajor() {
		Degree m = degreeList.getMajor("Computer Information Systems");
		assertNotNull(m);
	}

	@Test
	void majorInformationPreserved() {
		Degree m = degreeList.getMajor("Computer Information Systems");
		assertEquals(m.getCredits(), 120);
	}

	@Test
	void loadInvalidMajor() {
		Degree m = degreeList.getMajor("test");
		assertNull(m);
	}

	@Test
	void loadNullMajor() {
		Degree m = degreeList.getMajor(null);
		assertNull(m);
	}

	@Test
	void loadValidMinor() {
		Degree m = degreeList.getMajor("Mathematics");
		assertNotNull(m);
	}

	@Test
	void minorInformationPreserved() {
		Degree m = degreeList.getMajor("Mathematics");
		assertEquals(m.getCredits(), 18);
	}

	@Test
	void loadInvalidMinor() {
		Degree m = degreeList.getMajor("test");
		assertNull(m);
	}

	@Test
	void loadNullMinor() {
		Degree m = degreeList.getMajor(null);
		assertNull(m);
	}

	@Test
	void loadValidMajorMap() {
		Degree m = degreeList.getMajorMap("Computer Information Systems");
		assertNotNull(m);
	}

	@Test
	void majorMapInformationPreserved() {
		Degree m = degreeList.getMajorMap("Computer Information Systems");
		assertEquals(m.getRequirements().get(0).getCategory(), "Semester 1");
	}

	@Test
	void loadInvalidMajorMap() {
		Degree m = degreeList.getMajorMap("test");
		assertNull(m);
	}

	@Test
	void loadNullMajorMap() {
		Degree m = degreeList.getMajorMap(null);
		assertNull(m);
	}

	@Test
	void testListApplicationAreas() {
		String s = degreeList.getAllApplicationAreas();
		assertTrue(s.contains("Required Application Area Courses") && s.contains("Recommended Application Area Courses"));
	}

	@Test
	void testGetDegreesNotNull() {
		ArrayList<Degree> d = degreeList.getDegrees();
		 assertNotNull(d);
	}

	@Test
	void testGetDegreesHasValidData() {
		ArrayList<Degree> d = degreeList.getDegrees();
		 assertNotNull(d.get(0));
	}

	@Test
	void testGetDegreesHasValid() {
		ArrayList<Degree> d = degreeList.getDegrees();
		 assertTrue(d.size() > 8);
	}
}

