public class Main {
    public static void main(String[] args) {

        Student s1 = new Student();

        s1.setStudentID("202505010493");
        s1.setName("Ali Sharif AbdulKadir Sharif");
        s1.setCGPA(3.75);
        s1.setProgram("Bachelor of Computer Science (Software Engineering)");

        System.out.println("Name: " + s1.getName());
        System.out.println("Student ID: " + s1.getStudentID());
        System.out.println("CGPA: " + s1.getCGPA());
        System.out.println("Program: " + s1.getProgram());
    }
}