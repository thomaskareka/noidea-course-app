//#20

import java.util.ArrayList;

public class DegreeList {
    private static DegreeList degreeList;
    private ArrayList<Degree> degrees;

    private DegreeList(){

    }
    public DegreeList getInstance(){
        return degreeList;
    }
    public Degree getMajor(String major){
        return null;
    }
    public Degree getMinor(String minor){
        return null;
    }
    public Degree getTitle(String title) {
        return null;
    }
    public Degree getRequiredCredits(int requiredCredits) {
        return null;
    }
}
