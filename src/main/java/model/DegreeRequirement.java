package model;
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
        String creditText = (requirementCredits == -1)? "all courses required": requirementCredits + " credits required:";
        String out = category + ", " + creditText;
        // for(String i: courses) {
        //     out += i + ", ";
        // }
        return out;
    }

    public String toStringDetailed() {
        String creditText = (requirementCredits == -1)? "all courses required": requirementCredits + " credits required:";
        String out = category + ", " + creditText;
        for(String i: courses) {
            out += "  " + i;
        }
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
                out = "  COMPLETE: " + this.toString() + ", " + completedCredits + " credits completed\n" + out;
            } else {
                out = "  " + this.toString() + ", " + completedCredits + " credits completed\n" + out;
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
            out = (allSatisified? "  COMPLETE: " : "  ") + this.toString() + "\n" + out;
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
                out = "  COMPLETE: " + this.toString() + ", " + completedCredits + " credits completed\n" + out;
            } else {
                out = "  " + this.toString() + ", " + completedCredits + " credits completed\n" + out;
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
            String[] sp = req.split("\\/");
            return courseList.getCourseByIdentifer(sp[0]).getCredits(); //both should have the same amount of credits
        } else {
            return courseList.getCourseByIdentifer(req).getCredits();
        }
    }
    private boolean calculateCourse(ArrayList<String> courseList, String req) {
        if(req.contains("+")) {
            String[] sp = req.split("\\+");
            if(courseList.contains(sp[0]) && courseList.contains(sp[1])) {
                courseList.remove(sp[0]);
                courseList.remove(sp[1]);
                return true;
            }
        } else if (req.contains("/")) {
            String[] sp = req.split("\\/");
            if(courseList.contains(sp[0])) {
                courseList.remove(sp[0]);
                return true;
            } else if (courseList.contains(sp[1])) {
                courseList.remove(sp[1]);
                return true;
            }
        } else {
            if(courseList.contains(req)) {
                courseList.remove(req);
                return true;
            }
        }
        return false;
    }

    public String calculateMajorMapSemester(ArrayList<DegreeRequirement> majorReqs, ArrayList<Course> inCourses, ArrayList<String> courseIDs, String major, String applicationArea) {
        String out = String.format("  %s (%d credits)\n", category, requirementCredits);
        for(String course : courses) {
            if(course.startsWith("CC-")) {
                boolean found = false;
                for(Course c : inCourses) {
                    if(c.getAttributes().contains(course)) {
                        out += "☑   " + course + "(" + c.getIdentifier() + ")\n" ;
                        inCourses.remove(c);
                        found = true;
                        break;
                    }
                }
                if(!found) {
                    out += "☒   " + course + "\n";
                }
            } else if (course.equals("BIMELEC")) {
                //TODO
                out += "☒ BIM Minor Elective\n";
            } else if (course.equals("MAJORELEC")) {
                out += calculateFromCategory(major, courseIDs, course);
            } else if (course.equals("CSCEELEC")) {
                out += calculateFromCategory(major, courseIDs, course);
            } else if (course.equals("LIBELEC")) {
                //TODO
                out += "☒ Liberal Arts Elective\n";
            } else if (course.equals("APP")) {
                out += calculateFromCategory(applicationArea, courseIDs, course);
            } else if(calculateCourse(courseIDs, course)) {
                    out += "☑   " + course + "\n";
                } else {
                    out += "☒   " + course + "\n";
                }
            }
        return out;
    }

    public String calculateFromCategory(String major, ArrayList<String> courseIDs, String type) {
        String out = "";
        Degree d = DegreeList.getInstance().getMajor(major);
        ArrayList<DegreeRequirement> drList = d.getRequirements();
        String searchString = "";
        if(type.equals("CSCEELEC")) {
            searchString = "CSCE Electives";
        } else if (type.equals("MAJORELEC")) {
            searchString = "Major Elective";
        } else if (type.equals("LAB")) {
            searchString = "Laboratory Science Elective";
        } else if (type.equals("APP")) {
            searchString = major;
        }

        if(major.equals("None")) {
            return "☒    APP";
        }

        for(DegreeRequirement dr : drList) {
            if(dr.getCategory().equals(searchString)) {
                for(String req : dr.getRequirements()) {
                    if(calculateCourse(courseIDs, req)) {
                        return String.format("☑   %s (%s)\n", searchString, req);
                    }
                }
                // for(String id : courseIDs) {
                //     if(dr.getRequirements().contains(id)) {
                //         courseIDs.remove(id);
                //         return String.format("+   %s (%s)\n", searchString, id);
                //     }
                // }
            }
        }
        return "☒   " + searchString + "\n";
    }
}