package model;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;;

public class DataLoader extends DataConstants {
    public static ArrayList<Student> getStudents() {
        ArrayList<Student> students = new ArrayList<Student>();

        try {
            FileReader reader = new FileReader(STUDENT_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray studentList = (JSONArray) new JSONParser().parse(reader);

            for(int i = 0; i < studentList.size(); i++) {
                JSONObject studentJSON = (JSONObject)studentList.get(i);

                UUID id = UUID.fromString((String)studentJSON.get(USER_ID));
                String firstName = (String)studentJSON.get(USER_FIRST_NAME);
                String lastName = (String)studentJSON.get(USER_LAST_NAME);
                String email = (String)studentJSON.get(USER_EMAIL);
                String password = (String)studentJSON.get(USER_PASSWORD);

                String major = (String)studentJSON.get(STUDENT_MAJOR);
                String minor = (String)studentJSON.get(STUDENT_MINOR);
                String applicationArea = (String)studentJSON.get(STUDENT_APP_AREA);
                double majorGPA = (double)studentJSON.get(STUDENT_MAJOR_GPA);
                double overallGPA = (double)studentJSON.get(STUDENT_GPA);
                String classLevel = (String)studentJSON.get(STUDENT_CLASS);
                String studentID = (String)studentJSON.get(STUDENT_ID);

                UUID advisor = UUID.fromString((String)studentJSON.get(STUDENT_ADVISOR_ID));

                boolean failureRisk = (boolean)studentJSON.get(STUDENT_AT_RISK);
                ArrayList<String> notes = new ArrayList<String>();
                JSONArray noteArray = ((JSONArray) studentJSON.get(STUDENT_NOTES));
                for(int j = 0; j < noteArray.size(); j++) {
                    notes.add((String) noteArray.get(j));
                }
                boolean hasScholarship = (boolean)studentJSON.get(STUDENT_SCHOLARSHIP);

                JSONArray degreeJSON = (JSONArray) studentJSON.get(STUDENT_COURSE_LIST);
                ArrayList<CourseProgress> progress = new ArrayList<CourseProgress>();
                for(int j = 0; j < degreeJSON.size(); j++) {
                    JSONObject courseJSON = (JSONObject)degreeJSON.get(j);
                    String courseID = (String)courseJSON.get(STUDENT_COURSE_ID);
                    String gradeString = ((String)courseJSON.get(STUDENT_COURSE_GRADE)).replace("+", "_PLUS");
                    Grade courseGrade = Grade.valueOf(gradeString);
                    boolean complete = (courseGrade == Grade.IN_PROGRESS)? false : true;
                    CourseProgress c = new CourseProgress(courseID, courseGrade, complete);
                    progress.add(c);
                }

                DegreeTracker degreeProgress = new DegreeTracker(progress);
                Student s = new Student(firstName, lastName, email, id, major, minor, majorGPA, overallGPA, classLevel, advisor, failureRisk, notes, hasScholarship, degreeProgress, password, applicationArea, studentID);
                students.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }

    public static ArrayList<Advisor> getAdvisors() {
        ArrayList<Advisor> advisors = new ArrayList<Advisor>();

        try {
            FileReader reader = new FileReader(ADVISOR_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray advisorList = (JSONArray) new JSONParser().parse(reader);

            for(int i = 0; i < advisorList.size(); i++) {
                JSONObject advisorJSON = (JSONObject)advisorList.get(i);
                
                UUID id = UUID.fromString((String)advisorJSON.get(USER_ID));
                String firstName = (String)advisorJSON.get(USER_FIRST_NAME);
                String lastName = (String)advisorJSON.get(USER_LAST_NAME);
                String email = (String)advisorJSON.get(USER_EMAIL);
                String password = (String)advisorJSON.get(USER_PASSWORD);


                boolean isAdmin = (boolean)advisorJSON.get(ADVISOR_IS_ADMIN);

                ArrayList<UUID> students = new ArrayList<UUID>();
                JSONArray studentList = (JSONArray) advisorJSON.get(ADVISOR_STUDENT_LIST);

                for(Object j : studentList) {
                    UUID studentID = UUID.fromString(j.toString());
                    students.add(studentID);
                }
                Advisor a = new Advisor(firstName, lastName, email, id, students, isAdmin, password);
                advisors.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return advisors;
    }

    public static ArrayList<Degree> getDegrees(){
        ArrayList<Degree> degrees = new ArrayList<Degree>();

        try {
            FileReader reader = new FileReader(DEGREE_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray degreeList = (JSONArray) new JSONParser().parse(reader);

            for(int i = 0; i < degreeList.size(); i++) {
                JSONObject degreeJSON = (JSONObject)degreeList.get(i);
                
                String type = (String)degreeJSON.get(DEGREE_TYPE);
                String name = (String)degreeJSON.get(DEGREE_NAME);
                int credits = Math.toIntExact((long)degreeJSON.get(DEGREE_CREDITS));
                ArrayList<DegreeRequirement> requirements = getDegreeRequirement((JSONArray)degreeJSON.get(DEGREE_REQUIREMENTS));
        
                
                Degree d = new Degree(type, name, requirements, credits);
                degrees.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return degrees;
    }

    private static ArrayList<DegreeRequirement> getDegreeRequirement(JSONArray a) {
        ArrayList<DegreeRequirement> out = new ArrayList<DegreeRequirement>();
        for(int i  = 0; i < a.size(); i++) {
            JSONObject reqJSON = (JSONObject)a.get(i);
            
            String category = (String)reqJSON.get(DEGREE_REQ_CATEGORY);
            int credits = Math.toIntExact((long)reqJSON.get(DEGREE_REQ_CREDITS));

            JSONArray courseList = (JSONArray) reqJSON.get(DEGREE_REQ_COURSES);
            ArrayList<String> courses = new ArrayList<String>();
            for(int j = 0; j < courseList.size(); j++) {
                courses.add((String)courseList.get(j));
            }

            DegreeRequirement req = new DegreeRequirement(category, courses, credits);
            out.add(req);
        }
        return out;
    }

    public static ArrayList<Course> getCourses(){
        ArrayList<Course> courses = new ArrayList<Course>();

        try {
            FileReader reader = new FileReader(COURSE_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray courseList = (JSONArray) new JSONParser().parse(reader);

            for(int i = 0; i < courseList.size(); i++) {
                JSONObject courseJSON = (JSONObject)courseList.get(i);
                
                String name = (String)courseJSON.get(COURSE_NAME);
                String identifier = (String)courseJSON.get(COURSE_ID);
                int credits = Math.toIntExact((long) courseJSON.get(COURSE_CREDITS));
                String description = (String)courseJSON.get(COURSE_DESCRIPTION);
                ArrayList<Requisite> requisites = getRequisites((JSONArray)courseJSON.get(COURSE_REQUISITES));
                String reqText = (String)courseJSON.get(COURSE_REQUISITES_TEXT);

                ArrayList<String> attributes = new ArrayList<String>();
                JSONArray attributeJSON = (JSONArray)courseJSON.get(COURSE_ATTRIBUTES);
                for (int j = 0; j < attributeJSON.size(); j++) {
                    attributes.add((String) attributeJSON.get(j));
                }
            

                Course c = new Course(name, identifier, credits, description, attributes, requisites, reqText);
                courses.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return courses;
    }

    private static ArrayList<Requisite> getRequisites(JSONArray a) {
        ArrayList<Requisite> out = new ArrayList<Requisite>();

        for(int i = 0; i < a.size(); i++) {
            JSONObject reqJSON = (JSONObject)a.get(i);

            if(reqJSON.toJSONString().length() < 3) { 
                return out;
            }

            String courses = (String)reqJSON.get(REQ_COURSES);
            Grade minGrade = Grade.valueOf(((String)reqJSON.get(REQ_GRADE)).replace("+", "_PLUS"));
            RequisiteType type = RequisiteType.valueOf((String)reqJSON.get(REQ_TYPE));


            out.add(new Requisite(type, courses, minGrade));
        }        

        return out;
    }


}