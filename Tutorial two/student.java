// Attributes
class Student   {
String name;
int age;
double gpa;



//constructor

Student(String studentName, int studentAge, double studentGpa ) {
    name = studentName;
    age = studentAge;
    gpa = studentGpa;
}




//Methods
public void displayInfo() {
    System.out.println("Name:" + name);
    System.out.println("Age:" + age);
    System.out.println("GPA:" + gpa);
}





//Another methods
public void study() {
    System.out.println(name + "is studying.");
}







// yet Another methods
public void takeExam() {
    System.out.println(name + "is taking an taking an exam.");
}
}
