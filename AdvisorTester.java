import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class AdvisorTester {
	

	@BeforeClass
	public void oneTimeSetup() {
		
	}
	
	@AfterClass
	public void oneTimeTearDown() {
		
	}

	@BeforeEach
	public void setup() {
		//runs before each test

	
	}
	
	@AfterEach
	public void tearDown() {
		
	}

	//Beginning of testing methods

	@Test
    void testSearchByStudentIDWhenIdExists() {
       
    }

    @Test
    void testSearchByStudentIDWhenIdDoesNotExist() {
    
    }

    @Test
    void testSearchByStudentIDWhenIdIsNull() {
      
    }

	@Test 
	public void testAddAdvisee(){

	}
	@Test 
	public void testAddCourseForStudent(){

	}
	@Test 
	public void removeCourseForStudent(){

	}
	@Test 
	public void testAddNotes(){

	}
	@Test 
	public void testEnterFailureRisk(){
		
	}
	@Test 
	public void testRemoveFailureRisk(){

	}
	

}
