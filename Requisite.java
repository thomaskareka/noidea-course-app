//#16

import java.util.ArrayList;

public class Requisite {
    private RequisiteType type;
    private ArrayList<String> courses;
    private String courseString;
    private Grade minGrade;

    public Requisite(RequisiteType type, String courseString, Grade minGrade) {
        this.type = type;
        this.courseString = courseString;
        this.minGrade = minGrade;
    }
    /* copy construtor
    public Requisite (Requisite r){
        this.type = r.getType();    
   }
   */

    // getters
    public RequisiteType getType(){
        return type;
    }
    public ArrayList<String> getCourses(){
        return courses;
    }
    public Grade getMinGrade(){
        return minGrade;
    }

    public String toString() {
        String out = String.format("%s: %s (%s)", type.toString(), courseString, minGrade.toString());
        return out;
    }
}
