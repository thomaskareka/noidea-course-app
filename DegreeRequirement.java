import java.util.ArrayList;

public class DegreeRequirement {
    private String category;
    private ArrayList<String> courses;
    private int requirementCredits;

    public DegreeRequirement(String category, ArrayList<String> courses, int requirementCredits) {
        this.category = category;
        this.courses = courses;
        this.requirementCredits = requirementCredits;
    }

    public DegreeRequirement(String category) {
        this.category = category;
    }

    public ArrayList<String> getRequirements() {
        return courses;
    }

    public int getRequirementsCredits() {
        return requirementCredits;
    }

}