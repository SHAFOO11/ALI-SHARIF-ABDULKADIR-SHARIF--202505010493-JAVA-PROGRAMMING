public class HelloWorld {

    public static void main(String[] args) {
        System.out.println("Hello, World!");
        System.out.println("Welcome to BIT1123 Object Oriented Programming.");

        String name = "Ali Sharif AbdulKadir Sharif";
        String studentId = "202505010493";
        int semester = 1;
        double cgpa = 3.75;
        boolean enrolled = true;
        char section = 'A';

        System.out.println();
        System.out.println("Name      : " + name);
        System.out.println("Student ID: " + studentId);
        System.out.println("Semester  : " + semester);
        System.out.println("CGPA      : " + cgpa);
        System.out.println("Section   : " + section);
        System.out.println("Enrolled  : " + enrolled);

        int creditHours = 3;
        int totalSubjects = 5;
        int totalCredits = creditHours * totalSubjects;

        System.out.println();
        System.out.println("Total credit hours this semester: " + totalCredits);
    }
}
