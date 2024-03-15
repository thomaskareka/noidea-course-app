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

    public String getCategory() {
        return category;
    }

    public int getRequirementsCredits() {
        return requirementCredits;
    }

    public String toString() {
        String creditText = (requirementCredits == -1)? "all courses required": requirementCredits + " credits required";
        String out = category + ", " + creditText;
        // for(String i: courses) {
        //     out += i + ", ";
        // }
        return out;
    }
    public String calculateRequirement(ArrayList<Course> inCourses) {
        String out = "";
        int completedCredits = 0;
        boolean allSatisified = true;
        if(courses.size() == 1 && courses.get(0).startsWith( "CC-")) {
            for(Course c : inCourses) {
                if(c.getAttributes().contains(courses.get(0))) {
                    completedCredits += c.getCredits();
                    out += "    " + c.getIdentifier() + " - " + c.getCredits() + "\n";
                }
            }
            if(completedCredits >= requirementCredits) {
                out = "COMPLETE: " + this.toString() + ", " + completedCredits + " credits completed\n" + out;
            } else {
                out = this.toString() + ", " + completedCredits + " credits completed\n" + out;
            }
        } else if (requirementCredits == -1) {
            ArrayList<String> courseIDList = new ArrayList<String>();
            for(Course c: inCourses) {
                String courseID = c.getIdentifier();
                courseIDList.add(courseID);
            }
            for(String req: courses) {
                if(calculateCourse(courseIDList, req)) {
                    out += "   " + req + "\n";
                } else {
                    out += " X " + req + "\n";
                    allSatisified = false;
                }
            }
            out = (allSatisified? "COMPLETE: " : "") + this.toString() + "\n" + out;
        } else { // X credits required
            ArrayList<String> courseIDList = new ArrayList<String>();
            for(Course c: inCourses) {
                String courseID = c.getIdentifier();
                courseIDList.add(courseID);
            }
            for(String req: courses) {
                if(calculateCourse(courseIDList, req)) {
                    int credits = calculateCourseCredits(req);
                    completedCredits += credits;
                    out += "    " + req + " - " + credits + "\n";
                }
            }
            if(completedCredits >= requirementCredits) {
                out = "COMPLETE: " + this.toString() + ", " + completedCredits + " credits completed\n" + out;
            } else {
                out = this.toString() + ", " + completedCredits + " credits completed\n" + out;
            }
        }
        if(out.endsWith("\n"))
            out = out.substring(0, out.length() -1);
        return out;
    }
    private int calculateCourseCredits(String req) {
        CourseList courseList = CourseList.getInstance();
        if(req.contains("+")) {
            String[] sp = req.split("\\+");
            return courseList.getCourseByIdentifer(sp[0]).getCredits() + courseList.getCourseByIdentifer(sp[1]).getCredits();
        } else if(req.contains("/")) {
            String[] sp = req.split("\\+");
            return courseList.getCourseByIdentifer(sp[0]).getCredits(); //both should have the same amount of credits
        } else {
            return courseList.getCourseByIdentifer(req).getCredits();
        }
    }
    private boolean calculateCourse(ArrayList<String> courseList, String req) {
        if(req.contains("+")) {
            String[] sp = req.split("\\+");
            if(courseList.contains(sp[0]) && courseList.contains(sp[1])) {
                return true;
            }
        } else if (req.contains("/")) {
            String[] sp = req.split("\\/");
            if(courseList.contains(sp[0]) && courseList.contains(sp[1])) {
                return true;
            }
        } else {
            if(courseList.contains(req)) {
                return true;
            }
        }
        return false;
    }
}