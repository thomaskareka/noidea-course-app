import java.util.ArrayList;

public class DegreeRequirement {
    private String category;
    private ArrayList<Course> courses;
    private int requirementCredits;

    public DegreeRequirement(String category, ArrayList<Course> courses, int requirementCredits) {
        this.category = category;
        this.courses = courses;
        this.requirementCredits = requirementCredits;
    }

    public DegreeRequirement(String category) {
        this.category = category;
    }

    public ArrayList<Course> getRequirements() {
        return courses;
    }

    public int getRequirementsCredits() {
        return requirementCredits;
    }

}