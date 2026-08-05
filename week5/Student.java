public class Student {

    private String studentID;
    private String name;
    private double cgpa;
    private String program;

    // Setter for Student ID
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    // Setter for Name
    public void setName(String name) {
        this.name = name;
    }

    // Setter for CGPA
    public void setCGPA(double cgpa) {
        this.cgpa = cgpa;
    }

    // Setter for Program
    public void setProgram(String program) {
        this.program = program;
    }

    // Getter for Student ID
    public String getStudentID() {
        return studentID;
    }

    // Getter for Name
    public String getName() {
        return name;
    }

    // Getter for CGPA
    public double getCGPA() {
        return cgpa;
    }

    // Getter for Program
    public String getProgram() {
        return program;
    }
}