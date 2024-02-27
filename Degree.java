import java.util.ArrayList;

public class Degree {
    private String major;
    private String minor;
    private String title;
    private ArrayList<DegreeRequirement> requirements;
    private int requiredCredits;

    public Degree(String major, String minor, String title, ArrayList<DegreeRequirement> requirements,
            int requiredCredits) {
        this.major = major;
        this.minor = minor;
        this.title = title;
        this.requirements = requirements;
        this.requiredCredits = requiredCredits;
    }

    public Degree(String major, String title, ArrayList<DegreeRequirement> requirements) {
        this.major = major;
        this.title = title;
        this.requirements = requirements;
    }
}
