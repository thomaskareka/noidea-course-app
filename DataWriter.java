import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;;

public class DataWriter extends DataConstants {
    public static void saveStudents() {
        UserList users = UserList.getInstance();
        ArrayList<Student> studentList = users.getStudents();
        JSONArray jsonUsers = new JSONArray();

        for(int i = 0; i < studentList.size(); i++) {
            jsonUsers.add(getStudentJSON(studentList.get(i)));
        }

        try(FileWriter file = new FileWriter(STUDENT_FILE_NAME)) {
            file.write(jsonUsers.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void saveAdvisors() {
        UserList users = UserList.getInstance();
        ArrayList<Advisor> advisorList = users.getAdvisors();
        JSONArray jsonUsers = new JSONArray();

        for(int i = 0; i < advisorList.size(); i++) {
            jsonUsers.add(getAdvisorJSON(advisorList.get(i)));
        }

        try(FileWriter file = new FileWriter(ADVISOR_FILE_NAME)) {
            file.write(jsonUsers.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveCourses() {
        
    }

    public static void saveMajors() {
        DegreeList degreeList = DegreeList.getInstance();
        ArrayList<Degree> degrees = degreeList.getDegrees();
        JSONArray jsonDegrees = new JSONArray();

        for(Degree d : degrees) {
            JSONObject dJson = new JSONObject();
            dJson.put(DEGREE_TYPE, d.getType());
            dJson.put(DEGREE_NAME, d.getTitle());
            dJson.put(DEGREE_CREDITS, d.getCredits());
            dJson.put(DEGREE_REQUIREMENTS, getDegreeReqJSON(d.getRequirements()));
            jsonDegrees.add(dJson);
        }

        try(FileWriter file = new FileWriter(DEGREE_FILE_NAME)) {
            file.write(jsonDegrees.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONArray getDegreeReqJSON(ArrayList<DegreeRequirement> d) {
        JSONArray out = new JSONArray();
        for(DegreeRequirement dr : d) {
            JSONObject reqJSON = new JSONObject();
            reqJSON.put(DEGREE_REQ_CATEGORY, dr.getCategory());
            reqJSON.put(DEGREE_CREDITS, dr.getRequirementsCredits());

            ArrayList<String> reqCourses = dr.getRequirements();
            JSONArray courseJSON = new JSONArray();
            for (String s : reqCourses) {
                courseJSON.add(s);
            }
            reqJSON.put(DEGREE_REQ_COURSES, courseJSON);
            out.add(reqJSON);
        }

        return out;
    }

    public static JSONObject getStudentJSON(Student student) {
        JSONObject studentDetails = getUserJSON(student);
        studentDetails.put(STUDENT_MAJOR, student.getMajor());
        studentDetails.put(STUDENT_MINOR, student.getMinor());
        studentDetails.put(STUDENT_MAJOR_GPA, student.getMajorGPA());
        studentDetails.put(STUDENT_GPA, student.getOverallGPA());
        studentDetails.put(STUDENT_CLASS, student.getClassLevel());
        studentDetails.put(STUDENT_APP_AREA, student.getApplicationArea());
        studentDetails.put(STUDENT_ID, student.getStudentID());

        studentDetails.put(STUDENT_ADVISOR_ID, student.getAdvisorReference().toString());
        studentDetails.put(STUDENT_AT_RISK, student.checkIfAtRisk());
        
        JSONArray noteArray = new JSONArray();
        ArrayList<String> notes = student.getNotes();
        for(String i : notes) {
            noteArray.add(i);
        }
        studentDetails.put(STUDENT_NOTES, noteArray);
        studentDetails.put(STUDENT_SCHOLARSHIP, student.hasScholarship());
        studentDetails.put(STUDENT_COURSE_LIST, getStudentCourseJSON(student.getDegreeTracker()));

        return studentDetails;
    }

    private static JSONArray getStudentCourseJSON(DegreeTracker t) {
        JSONArray out = new JSONArray();
        for(CourseProgress c : t.getCourseProgress()) {
            JSONObject cpJSON = new JSONObject();
            cpJSON.put(STUDENT_COURSE_GRADE, c.getCourseGrade());
            cpJSON.put(STUDENT_COURSE_ID, c.getCourseID());
            out.add(cpJSON);
        }
        return out;
    }

    public static JSONObject getAdvisorJSON(Advisor advisor) {
        JSONObject advisorDetails = getUserJSON(advisor);
        advisorDetails.put(ADVISOR_IS_ADMIN, advisor.isAdmin());

        JSONArray studentArray = new JSONArray();
        ArrayList<UUID> students = advisor.getStudents();
        for(UUID i: students) {
            studentArray.add(i.toString());
        }
        advisorDetails.put(ADVISOR_STUDENT_LIST, studentArray);

        return advisorDetails;
    }

    private static JSONObject getUserJSON(User user) {
        JSONObject userDetails = new JSONObject();
        userDetails.put(USER_ID, user.getID().toString());
        userDetails.put(USER_FIRST_NAME, user.getFirstName());
        userDetails.put(USER_LAST_NAME, user.getLastName());
        userDetails.put(USER_EMAIL, user.getEmail());
        userDetails.put(USER_PASSWORD, user.getPassword());

        return userDetails;
    }
}