import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;;

public class DataLoader extends DataConstants {
    public ArrayList<Student> getStudents() {
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

                String major = (String)studentJSON.get(STUDENT_MAJOR);
                String minor = (String)studentJSON.get(STUDENT_MINOR);
                double majorGPA = (double)studentJSON.get(STUDENT_MAJOR_GPA);
                double overallGPA = (double)studentJSON.get(STUDENT_GPA);
                String classLevel = (String)studentJSON.get(STUDENT_CLASS);

                UUID advisor = UUID.fromString((String)studentJSON.get(STUDENT_ADVISOR_ID));

                boolean failureRisk = (boolean)studentJSON.get(STUDENT_AT_RISK);
                ArrayList<String> notes = new ArrayList<String>((JSONArray) studentJSON.get(STUDENT_NOTES));
                boolean hasScholarship = (boolean)studentJSON.get(STUDENT_SCHOLARSHIP);

                JSONObject degreeJSON = (JSONObject) studentJSON.get(STUDENT_COURSE_LIST);
                //TODO: add degree tracker inilization from student info
                DegreeTracker degreeProgress = new DegreeTracker();
                Student s = new Student(firstName, lastName, email, id, major, minor, majorGPA, overallGPA, classLevel, advisor, failureRisk, notes, hasScholarship, degreeProgress);
                students.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }

    public ArrayList<Advisor> getAdvisors(){
        return new ArrayList<Advisor>();
    }

    public ArrayList<Degree> getDegrees(){
        return new ArrayList<Degree>();
    }

    public ArrayList<Course> getCourses(){
        return new ArrayList<Course>();
    }


}