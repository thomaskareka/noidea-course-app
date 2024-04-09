package model;
import java.util.ArrayList;
import java.util.List;

public class CourseList {
    private static CourseList courseList;
    private ArrayList<Course> courses;

    private CourseList() {
        this.courses = DataLoader.getCourses();
    }

    public static CourseList getInstance() {
        if (courseList == null ) {
            courseList = new CourseList();
        }
        return courseList;
    }

    public Course getCourseByIdentifer(String identifier) {
        if(identifier == null || identifier.equals("")){
            return null;
        }
        for( int i=0; i<courses.size(); i++ ){
            if(courses.get(i).getIdentifier().toUpperCase().equals(identifier.toUpperCase()))
                return courses.get(i);
        }     
        return null;
    }

    public Course getCourseByName(String name) {
        for( int i=0; i<courses.size(); i++ ){
            if(courses.get(i).getName().toUpperCase().equals(name.toUpperCase()))
                return courses.get(i);
        }  
        return null;
    }

    public String getCourseDescriptionByName(String name){
        if(name.equals("") || name == null)
            return null;
        
        Course course = getCourseByName(name);
        if(course == null){
            return null;
        }
        return course.getDescription();
    }

    public String getCourseDescriptionByIdentifer(String identfier){
        if(identfier.equals("") || identfier == null)
            return null;
        
        Course course = getCourseByIdentifer(identfier);
        if(course == null){
            return null;
        }
        
        return course.getDescription();
    }

    public String getReqsByName(String name){
        if(name.equals("") || name == null)
            return null;
        
        Course course = getCourseByName(name);
        if(course != null){
            ArrayList<Requisite> holder = course.getRequisites();
            String str = "";
            for (Requisite requisite : holder) {
               str += requisite + "\n";
             }
            return str;
        }
        return null;
    }

    public String getReqsByIdentifer(String identifer){
        if(identifer.equals("") || identifer == null)
            return null;
        
        Course course = getCourseByIdentifer(identifer);
        if(course != null){
            ArrayList<Requisite> holder = course.getRequisites();
            String str = "";
            for (Requisite requisite : holder) {
                str += requisite + "\n";
            }
            return str;
        }
        return null;
    }

    public boolean addCourse(Advisor advisor, String name, String identifier, int credits, String description, ArrayList<String> attributes, ArrayList<Requisite> requisite, String reqText){
        if(advisor.isAdmin()){
            Course newCourse = new Course(name, identifier, credits, description, attributes, requisite, reqText);
            courses.add(newCourse);
        }
        return containsCourse(name);
    }

    private boolean containsCourse(String name){
        for (Course course : courses) {
            if(course.getName().equalsIgnoreCase(name))
                return true;
        }
        return false;
    }

    public String getAllCourses(){
        String str = "";
        for (Course course : courses) {
            str += course.toString() + "\n";
        }
        
        return str;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public String getCoursesWithAttribute(String attribute) {
        String out = "Valid courses with attribute " + attribute + ":\n";
        for(Course course : courses) {
            if(course.getAttributes().contains(attribute)) {
                out += course.getIdentifier() + " ";
            }
        }
        return out;
    }

    public ArrayList<String> getReqCourses(String category) {
        ArrayList<String> required = new ArrayList<>();
        DegreeRequirement degreeRequirement = new DegreeRequirement(category);
        required = degreeRequirement.getRequirements();
        return required;
    }

    //not sure what goes here
    public ArrayList<Course> getDegreeCourses(String degree) {
        return null;
    }

    public ArrayList<Course> searchCoursesByName(String search) {
        ArrayList<Course> valid = new ArrayList<>();
        for(Course c : courses) {
            if(c.getName().toLowerCase().contains(search.toLowerCase()) || c.getIdentifier().toLowerCase().contains(search.toLowerCase()))
                valid.add(c);
        }
        return valid;
    }

    public List<Course> getCoursesFromSearch(int page, String search) {
        if(search.equals("")) {
            return courses.subList(Math.min(page * 25, courses.size()), Math.min(page * 25 + 24, courses.size()));
        }
        ArrayList<Course> valid = searchCoursesByName(search);
        return valid.subList(Math.min(page * 25, valid.size()), Math.min(page * 25 + 24, valid.size()));
    }
}
