import java.util.ArrayList;

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
        for( int i=0; i<courses.size(); i++ ){
            if(courses.get(i).getIdentifier().equals(identifier))
                return courses.get(i);
        }     
        return null;
    }

    public Course getCourseByName(String name) {
        for( int i=0; i<courses.size(); i++ ){
            if(courses.get(i).getName().equals(name))
                return courses.get(i);
        }  
        return null;
    }

    public String getCourseDescriptionByName(String name){
        Course course = getCourseByName(name);
        return course.getDescription();
    }

    public String getCourseDescriptionByIdentifer(String identfier){
        Course course = getCourseByIdentifer(identfier);
        return course.getDescription();
    }

    public String getReqsByName(String name){
        Course course = getCourseByName(name);
        ArrayList<Requisite> holder = course.getRequisites();
        String str = "";
        for (Requisite requisite : holder) {
            str += requisite + "\n";
        }
        return str;
    }

    public String getReqsByIdentifer(String identifer){
        Course course = getCourseByIdentifer(identifer);
        ArrayList<Requisite> holder = course.getRequisites();
        String str = "";
        for (Requisite requisite : holder) {
            str += requisite + "\n";
        }
        return str;
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
}
