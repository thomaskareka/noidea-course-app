package model;

public abstract class DataConstants {
    //protected static final String = "";

    protected static final String ADVISOR_FILE_NAME = "src/main/java/data/advisors.json";
    protected static final String COURSE_FILE_NAME = "src/main/java/data/courses.json";
    protected static final String DEGREE_FILE_NAME = "src/main/java/data/degrees.json";
    protected static final String STUDENT_FILE_NAME = "src/main/java/data/students.json";

    protected static final String COURSE_NAME = "name";
    protected static final String COURSE_ID = "identifier";
    protected static final String COURSE_CREDITS = "credits";
    protected static final String COURSE_DESCRIPTION = "description";
    protected static final String COURSE_ATTRIBUTES = "attributes";
    protected static final String COURSE_REQUISITES = "requisites";
    protected static final String COURSE_REQUISITES_TEXT = "requisiteText";

    protected static final String REQ_COURSES = "courses";
    protected static final String REQ_GRADE = "grade";
    protected static final String REQ_TYPE = "type";
     
    protected static final String USER_ID = "uuid";
    protected static final String USER_FIRST_NAME = "firstName";
    protected static final String USER_LAST_NAME = "lastName";
    protected static final String USER_EMAIL = "email";
    protected static final String USER_PASSWORD = "password";

    protected static final String STUDENT_MAJOR = "major";
    protected static final String STUDENT_MINOR = "minor";
    protected static final String STUDENT_MAJOR_GPA = "majorGPA";
    protected static final String STUDENT_GPA = "overallGPA";
    protected static final String STUDENT_CLASS = "classLevel";
    protected static final String STUDENT_ADVISOR_ID = "advisor";
    protected static final String STUDENT_AT_RISK = "failureRisk";
    protected static final String STUDENT_NOTES = "notes";
    protected static final String STUDENT_SCHOLARSHIP = "hasScholarship";
    protected static final String STUDENT_COURSE_LIST = "courses";
    protected static final String STUDENT_APP_AREA = "applicationArea";
    protected static final String STUDENT_ID = "studentID";

    protected static final String STUDENT_COURSE_ID = "id";
    protected static final String STUDENT_COURSE_GRADE = "grade";

    protected static final String ADVISOR_IS_ADMIN = "admin";
    protected static final String ADVISOR_STUDENT_LIST = "students";

    protected static final String DEGREE_TYPE = "type";
    protected static final String DEGREE_NAME = "name";
    protected static final String DEGREE_CREDITS = "requiredCredits";
    protected static final String DEGREE_REQUIREMENTS = "requirements";
    protected static final String DEGREE_REQ_CATEGORY = "category";
    protected static final String DEGREE_REQ_CREDITS = "credits";
    protected static final String DEGREE_REQ_COURSES = "courses";
}
