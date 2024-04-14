package model;
import java.util.ArrayList;

public class Degree {
    private String type;
    private String title;
    private ArrayList<DegreeRequirement> requirements;
    private int requiredCredits;

    public Degree(String type, String title, ArrayList<DegreeRequirement> requirements, int requiredCredits) {
        this.type = type;
        this.title = title;
        this.requirements = requirements;
        this.requiredCredits = requiredCredits;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<DegreeRequirement> getRequirements() {
        return requirements;
    }

    public int getCredits() {
        return requiredCredits;
    }

    public String toString() {
        // String out = "title:" + title + ", " + requiredCredits + " credits, type: " + type + "\n";
        String out = "";
        for (DegreeRequirement i : requirements) {
            out += "  " + i.toStringDetailed() + "\n";
        }
        return out;
    }
}
