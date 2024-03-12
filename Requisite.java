//#16

import java.util.ArrayList;

public class Requisite {
    private RequisiteType type;
    private ArrayList<String> courses;
    private ArrayList<Grade> courseGrades;

    public Requisite(RequisiteType type, ArrayList<String> courses, ArrayList<Grade> courseGrades){
        courses = new ArrayList<String>();
        courseGrades = new ArrayList<Grade>();
        this.type=type;
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
    public ArrayList<Grade> getcourseGrades(){
        return courseGrades;
    }

    public String toString() {
        String out = type.toString() + ": ";
        for(int i = 0; i < courses.size() && i < courseGrades.size(); i++) {
            out += String.format("%s (%s)", courses.get(i), courseGrades.get(i).toString());
        }
        return out;
    }
}
