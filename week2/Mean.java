public class Mean {

    public static void main(String[] args) {
        Student s1 = new Student("SHARIF", 23, 3.5);

        s1.displayInfo();
        s1.study();
        s1.takeExam();

        Student s2 = new Student("AHMAD", 21, 3.8);

        System.out.println();
        s2.displayInfo();
        s2.study();
    }
}
