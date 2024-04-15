package model;

import java.util.ArrayList;

public class DegreeTracker {
    private ArrayList<CourseProgress> studentCourses;
    private int completedCredits;
    private int requiredCredits;

    public DegreeTracker(ArrayList<CourseProgress> studentCourses) {
        this.studentCourses = studentCourses;
        CalculateGPA();
        CalculateMajorGPA();
    }

    public String getGradeLevel() {
        int credits = getCompletedCredits();
        if(credits < 30) {
            return "Freshman";
        } else if (credits < 60) {
            return "Sophomore";
        } else if (credits < 90) {
            return "Junior";
        } else {
            return "Senior";
        }
    }

    public int getCompletedCredits() {
        completedCredits = 0;
        for(CourseProgress courseProgress : studentCourses) {
            String grade = courseProgress.getCourseGrade();
            if(grade.equals("IN_PROGRESS") || grade.equals("F")) {
                continue;
            }
            completedCredits += courseProgress.getCourse().getCredits();
        }
        return completedCredits;
    }

    public double CalculateGPA() {
        double total = 0.0;
        double gpa = 0.0;
        completedCredits = 0;
        int totalCredits = 0;
        for (CourseProgress courseProgress : studentCourses) {
            String grade = courseProgress.getCourseGrade();
            if(grade.equals("IN_PROGRESS")) {
                continue;
            }
            int credits = courseProgress.getCourse().getCredits();
            completedCredits += credits;
            totalCredits += credits;
            if(grade.equals("A")) 
                total += (4.00 * credits);

            else if(grade.equals("B+"))
                total += (3.5 * credits);
            
            else if(grade.equals("B"))
                total += (3.0 * credits);

            else if(grade.equals("C+"))
                total += (2.5 * credits);
            
            else if(grade.equals("C"))
                total += (2.0 * credits);

            else if(grade.equals("D+"))
                total += (1.5 *credits);

            else if(grade.equals("D"))
                total += (1.0 * credits);

            else if(grade.equals("F")) {
                total += (0.0 *credits);
                completedCredits -= credits;
            }
        }
        
        gpa = total/totalCredits;
        
        return gpa;
    }

    public double CalculateMajorGPA() {
        double total = 0.0;
        double gpa = 0.0;
        int totalCredits = 0;
        for (CourseProgress courseProgress : studentCourses) {
            String grade = courseProgress.getCourseGrade();
            if(grade.equals("IN_PROGRESS") || !courseProgress.getCourseID().startsWith("CSCE")) {
                continue;
            }
            int credits = courseProgress.getCourse().getCredits();
            totalCredits += credits;
            if(grade.equals("A")) 
                total += (4.00 * credits);

            else if(grade.equals("B+"))
                total += (3.5 * credits);
            
            else if(grade.equals("B"))
                total += (3.0 * credits);

            else if(grade.equals("C+"))
                total += (2.5 * credits);
            
            else if(grade.equals("C"))
                total += (2.0 * credits);

            else if(grade.equals("D+"))
                total += (1.5 *credits);

            else if(grade.equals("D"))
                total += (1.0 * credits);

            else if(grade.equals("F")) {
                total += (0.0 *credits);
                completedCredits -= credits;
            }
        }
        
        gpa = total/totalCredits;
        
        return gpa;
    }

    public double CalculateProgress(Degree degree) {
        if(degree == null) {
            return 0;
        }
        requiredCredits = degree.getCredits();
        completedCredits = getCompletedCredits();
        double out = (double) completedCredits/requiredCredits;
        System.out.println(out);
        return (out);
    }

    public String getCourseGrade(String identifer){
        CourseProgress holder = getCourseProgress(identifer);
        if (holder == null) {
            return "Not Taken";
        }
        return holder.getCourseGrade();
    }

    public boolean addGrade(String course, Grade grade){
        CourseProgress holder = getCourseProgress(course);
        if(holder == null) {
            System.out.println("User has not taken this course, adding before setting grade.");
            addCourse(course);
            holder = getCourseProgress(course);
        }
        return holder.editCourseGrade(grade);
    }

    private CourseProgress getCourseProgress(String name){
        for (CourseProgress courseProgress : studentCourses) {
            if(courseProgress.getCourseID().equals(name))
                return courseProgress;
        }
        return null;
    }

    public ArrayList<String> GetIncompleteCourses() {
        ArrayList<String> incompletedCourses = new ArrayList<String>();
        for (CourseProgress courseProgress : studentCourses) {
            if(!courseProgress.getCompletionStatus())
                incompletedCourses.add(courseProgress.getCourseID());
        }
        
        return incompletedCourses;
    }

    public ArrayList<String> GetCompleteCourses() {
        ArrayList<String> completedCourses = new ArrayList<String>();
        for (CourseProgress courseProgress : studentCourses) {
            if(courseProgress.getCompletionStatus())
                completedCourses.add(courseProgress.getCourseID());
        }
        return completedCourses;
    }

    public ArrayList<String> getCourses() {
        ArrayList<String> courses = new ArrayList<String>();
        for (CourseProgress courseProgress : studentCourses) {
            courses.add(courseProgress.getCourseID());
        }
        return courses;
    }

    public ArrayList<Course> getCourseObjects() {
        ArrayList<Course> courses = new ArrayList<Course>();
        for (CourseProgress courseProgress : studentCourses) {
            courses.add(courseProgress.getCourse());
        }
        return courses;
    }

    public boolean addCourse(String course){
        CourseProgress newCourse = new CourseProgress(course, Grade.IN_PROGRESS, false);
        if(getCourseProgress(course) != null) {
            System.out.println("Student already has this course added!");
            return false;
        }
        studentCourses.add(newCourse);
        if(newCourse != null) {
            System.out.println("Course successfully added: " + newCourse.toString());
            return true;
        }
        return false;
    }

    public boolean removeCourse(Course c){
        for (CourseProgress courseProgress : studentCourses) {
            if(courseProgress.getCourseID().equalsIgnoreCase(c.getIdentifier())){
                studentCourses.remove(courseProgress);
                CalculateGPA();
                CalculateMajorGPA();
                return true;
            }
        }
        return false;
    }

    public String generateEightSememsterPlan() {
        return " ";
    }

    public boolean checkIfDegreeRequirementsMet(Degree degree) {
        if(CalculateProgress(degree) != 100)
            return false;

        return true;
    }

    public int requirementCreditsCompleted(String category) {
        for (CourseProgress courseProgress : studentCourses) {
            String id = courseProgress.getCourse().getIdentifier().substring(0,3);
            if(id == category) {
                if(courseProgress.getCompletionStatus())
                    completedCredits += courseProgress.getCourse().getCredits();
            }
        }
        return completedCredits;
    }

    public int requirementCreditsRemaining(String category, String major) {
        Degree d = DegreeList.getInstance().getMajor(major);
        requiredCredits = d.getCredits();
        return requiredCredits - completedCredits;
    }

    public String getAllCourses(){
        String str = "";
        for (CourseProgress courseProgress : studentCourses) {
            str += courseProgress.getCourseName() + "\n";
        }
        return str;
    }

    public String createTranscipt(){
        String str = completedCredits + " credits completed.\n";
        for (CourseProgress courseProgress : studentCourses) {
            str += courseProgress.getCourseName() + " - " + courseProgress.getCourseGrade() + "\n";
        }
        return str;
    }
    //for data saving
    public ArrayList<CourseProgress> getCourseProgress() {
        return studentCourses;
    }
}
