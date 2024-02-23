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
    public Major getMajor(String major){
        return null;
    }
}
