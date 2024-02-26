import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;;

public class DataLoader {
    public ArrayList<Student> getStudents() {
        ArrayList<Student> students = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try {
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("students.json"))
            for (Object obj : jsonArray) {
                JSONObject studentJson = (JSONObject) obj;

                String firstName = (String) studentJson.get("firstName");
                String lastName = (String) studentJson.get("lastName");
                String email = (String) studentJson.get("email");
                UUID id = UUID.fromString((String) studentJson.get("id"));
                String major = (String) studentJson.get("major");
                String minor = (String) studentJson.get("minor");
                double majorGPA = (double) studentJson.get("majorGPA");
                double overallGPA = (double) studentJson.get("overallGPA");
                String classLevel = (String) studentJson.get("classLevel");
                
                UUID advisorID = UUID.fromString((String) studentJson.get("id"));
                
                boolean failureRisk = (boolean) studentJson.get("failureRisk");
                ArrayList<String> notes = new ArrayList<>((JSONArray) studentJson.get("notes"));
                boolean hasScholarship = (boolean) studentJson.get("hasScholarship");
                
                JSONObject degreeProgressJson = (JSONObject) studentJson.get("degreeProgress");
                DegreeTracker degreeProgress = new DegreeTracker();
                
                Student student = new Student(firstName, lastName, email, id, major, minor, majorGPA, overallGPA, classLevel, advisorID, failureRisk, notes, hasScholarship, degreeProgress);
                students.add(student);
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