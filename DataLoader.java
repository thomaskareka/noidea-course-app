import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;;

public class DataLoader {
    public ArrayList<Student> getStudents() {
        return new ArrayList<Student>();
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