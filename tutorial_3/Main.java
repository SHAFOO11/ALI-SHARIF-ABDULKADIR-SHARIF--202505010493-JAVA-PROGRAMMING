package tutorial_3;

public class Main {

    public static void main(String[] args) {

        Person p1 = new Person("Ali", "2222");
        Person p2 = new Student("Ahmad", "3333");
        Person p3 = new Lecturer("Dr. Omar", "1111");

        p1.introduce();
        p2.introduce();
        p3.introduce();
    }
}