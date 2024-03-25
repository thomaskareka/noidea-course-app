import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class DegreeRequirementTester {
	private CourseList courseList;


	@BeforeEach
	public void setup() {
		courseList = CourseList.getInstance();
        courseList.getCourses().clear();
        initializeCourse();
	}
	
	@AfterEach
	public void tearDown() {
		courseList.getCourses().clear();
	}

	@Test
	private void initializeCourse() {
		Advisor adminAdvisor = new Advisor("Bob", "Smith", "admin@example.com", true, "123");

		ArrayList<String> aCSCE145 = new ArrayList<>(Arrays.asList("Carolina Core"));
		ArrayList<Requisite> requisitesCS101 = new ArrayList<>();
		courseList.addCourse(adminAdvisor, "Intro to Comp Science", "CS145", 3, "Basic concepts of computer science", aCSCE145, requisitesCS101, "No prerequisites");

		ArrayList<String> aMATH101 = new ArrayList<>(Arrays.asList("Math"));
		ArrayList<Requisite> rMath101 = new ArrayList<>();
		courseList.addCourse(adminAdvisor, "Calculus I", "MATH121", 4, "Intro to Calculus", aMATH101, rMath101, "Prerequisite: High school algebra");
	}

	@Test
	void testRequirementCredits() {
        ArrayList<String> requiredCourses = new ArrayList<>(Arrays.asList("MATH121", "CSCE145"));
        DegreeRequirement degreeRequirement = new DegreeRequirement("CC", requiredCourses, 6);

        ArrayList<Course> studentCourses = new ArrayList<>();
        studentCourses.add(courseList.getCourseByIdentifer("CSCE145"));

        String calculationResult = degreeRequirement.calculateRequirement(studentCourses);
        assertTrue(calculationResult.contains("3 credits completed"), "Credits did not match expected value");
    }

	@Test
	void testCompleteRequirement() {
        ArrayList<String> requiredCourses = new ArrayList<>(Arrays.asList("CSCE145"));
        DegreeRequirement degreeRequirement = new DegreeRequirement("Single Course Requirement", requiredCourses, 4);

        ArrayList<Course> studentCourses = new ArrayList<>();
        studentCourses.add(courseList.getCourseByIdentifer("CSCE145"));

        String calculationResult = degreeRequirement.calculateRequirement(studentCourses);
        assertTrue(calculationResult.contains("COMPLETE"), "Requirement should be marked as complete.");
    }

	@Test
	void testPartialCompletion() {
		ArrayList<String> requiredCourses = new ArrayList<>(Arrays.asList("MATH101", "PHYS101"));
		DegreeRequirement scienceRequirement = new DegreeRequirement("Science Requirement", requiredCourses, 8);

		ArrayList<Course> studentCourses = new ArrayList<>();
		studentCourses.add(new Course("Calculus I", "MATH141", 4, "Intro to Calc", new ArrayList<>(), new ArrayList<>(), "None"));

		String calculationResult = scienceRequirement.calculateRequirement(studentCourses);
		assertFalse(calculationResult.contains("COMPLETE"), "Partial completion should not be marked as complete.");
		assertTrue(calculationResult.contains("4 credits completed"), "Credits calculation did not match");
	}

	@Test
    void testRequirementWithNoCourses() {
        ArrayList<String> requiredCourses = new ArrayList<>(Arrays.asList("MATH121", "CSCE145"));
        DegreeRequirement degreeRequirement = new DegreeRequirement("Empty Requirement", requiredCourses, 6);
        ArrayList<Course> studentCourses = new ArrayList<>();

        String result = degreeRequirement.calculateRequirement(studentCourses);
        assertFalse(result.contains("COMPLETE"), "Requirement should not be marked as complete with no courses taken.");
    }

	@Test
    void testRequirementWithExactCredits() {
        ArrayList<String> requiredCourses = new ArrayList<>(Arrays.asList("CSCE145"));
        DegreeRequirement degreeRequirement = new DegreeRequirement("Exact Credits Requirement", requiredCourses, 3);

        ArrayList<Course> studentCourses = new ArrayList<>();
        studentCourses.add(courseList.getCourseByIdentifer("CSCE145"));

        String calculationResult = degreeRequirement.calculateRequirement(studentCourses);
        assertTrue(calculationResult.contains("COMPLETE"), "Requirement should be marked as complete with exact required credits.");
    }

}

