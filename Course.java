import java.util.ArrayList;

public class Course {
    private String name;
    private String identifier;
    private int credits;
    private String description;
    private Grade minGrade;
    private ArrayList<String> attributes;
    private ArrayList<Requisite> requisites;

    public Course(String name, String identifier, int credits, String description, ArrayList<String> attributes,
            ArrayList<Requisite> requisites2) {
        this.name = name;
        this.identifier = identifier;
        this.credits = credits;
        this.description = description;
        this.attributes = attributes;
        this.requisites = new ArrayList<>();
        this.requisites.addAll(requisites2);
    }

    public Course(String name, String identifier, int credits) {
        this.name = name;
        this.identifier = identifier;
        this.credits = credits;
    }
    
    public String getIdentifier() {
        return this.identifier;
    }

    public String getName() {
        return this.name;
    }

}