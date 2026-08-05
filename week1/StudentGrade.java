public class StudentGrade {

    public static void main(String[] args) {
        String[] subjects = {"Object Oriented Programming", "Database Systems", "Web Development",
                             "Discrete Mathematics", "Computer Networks"};
        int[] marks = {88, 74, 91, 65, 57};

        System.out.println("===== STUDENT GRADE REPORT =====");
        System.out.println("Name      : Ali Sharif AbdulKadir Sharif");
        System.out.println("Student ID: 202505010493");
        System.out.println();

        int total = 0;

        for (int i = 0; i < subjects.length; i++) {
            System.out.printf("%-30s %3d  %s%n", subjects[i], marks[i], calculateGrade(marks[i]));
            total += marks[i];
        }

        double average = (double) total / marks.length;

        System.out.println();
        System.out.printf("Total   : %d%n", total);
        System.out.printf("Average : %.2f%n", average);
        System.out.printf("Grade   : %s%n", calculateGrade(average));
        System.out.println("Status  : " + (average >= 50 ? "PASS" : "FAIL"));
    }

    public static String calculateGrade(double mark) {
        if (mark >= 80) {
            return "A";
        } else if (mark >= 70) {
            return "B";
        } else if (mark >= 60) {
            return "C";
        } else if (mark >= 50) {
            return "D";
        } else {
            return "F";
        }
    }
}
