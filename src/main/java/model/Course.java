package model;
import java.util.ArrayList;

public class Course {
    private String name;
    private String identifier;
    private int credits;
    private String description;
    private Grade minGrade;
    private ArrayList<String> attributes;
    private ArrayList<Requisite> requisites;
    private String reqText;

    public Course(String name, String identifier, int credits, String description, ArrayList<String> attributes,
            ArrayList<Requisite> requisites2, String reqText) {
        this.name = name;
        this.identifier = identifier;
        this.credits = credits;
        this.description = description;
        this.attributes = attributes;
        this.requisites = new ArrayList<>();
        this.requisites.addAll(requisites2);
        this.reqText = reqText;
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

    public int getCredits() {
        return this.credits;
    }

    public String getRequisiteText() {
        return reqText;
    }

    public ArrayList<Requisite> getRequisites() {
        return requisites;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getAttributes() {
        return attributes;
    }

    public String toString(){
        String str = identifier + ": " + name + "\nCredits: " + credits + "\nDescription: " + description;
        return str;
    }

    public String toStringDetailed(){
        String str = identifier + ": " + name + "\nCredits: " + credits + "\nDescription: " + description;
        if(!reqText.equals("")) {
            str += "\n" + reqText;
        }
        if(!attributes.isEmpty()) {
            for(String s: attributes) {
                str += "\n" + s;
            }
        }
        return str;
    }
}