import java.util.ArrayList;

public class Course {
    private String name;
    private String identifier;
    private int credits;
    private String description;
    private Grade minGrade;
    private ArrayList<String> attributes;
    private ArrayList<Requisite> requisites;
    
    public Course(String name, String identifier, int credits, String description, ArrayList<String> attributes, Requisite requisites) {

    }

    public Course(String name, String identifier, int credits) {
        
    }

    
}