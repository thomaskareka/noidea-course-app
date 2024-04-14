package model;
//#20

import java.util.ArrayList;

public class DegreeList {
    private static DegreeList degreeList;
    private ArrayList<Degree> degrees;

    private DegreeList() {
        degrees = DataLoader.getDegrees();
    }

    public static DegreeList getInstance() {
        if (degreeList == null) {
            degreeList = new DegreeList();
        }
        return degreeList;
    }

    public Degree getMajor(String major){
        for (Degree degree : degrees) {
            if(degree.getTitle().equals(major) && !degree.getType().equals("majorMap"))
                return degree;
        }
        return null;
    }

    public Degree getMajorMap(String majorMap) {
        for (Degree degree : degrees) {
            if(degree.getTitle().equals(majorMap) && degree.getType().equals("majorMap"))
                return degree;
        }
        return null;
    }


    public String getAllApplicationAreas() {
        String out = "Application Areas:\n";
        for (Degree degree : degrees) {
            if(degree.getType().equals("applicationArea"))
                out += degree.toString() + "\n";
        }
        return out;
    }

    public ArrayList<Degree> getDegrees() {
        return degrees;
    }

    public ArrayList<Degree> getCategory(String category) {
        ArrayList<Degree> out = new ArrayList<>();
        for(Degree degree : degrees) {
            if(degree.getType().equalsIgnoreCase(category)) {
                out.add(degree);
            }
        }
        return out;
    }
}
