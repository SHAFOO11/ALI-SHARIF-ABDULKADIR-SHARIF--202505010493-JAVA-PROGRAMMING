import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Questions {

    private String question;
    private String[] options;
    private int correctIndex;
    private int difficulty;

    public Questions(String question, String[] options, int correctIndex, int difficulty) {
        this.question = question;
        this.options = options;
        this.correctIndex = correctIndex;
        this.difficulty = difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectIndex() {
        return correctIndex;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public static List<Questions> loadQuestions() {
        List<Questions> bank = new ArrayList<>();

        bank.add(new Questions("Which keyword is used to create a class in Java?",
                new String[]{"class", "struct", "define", "object"}, 0, 1));

        bank.add(new Questions("Which of these is NOT a Java primitive type?",
                new String[]{"int", "boolean", "String", "double"}, 2, 1));

        bank.add(new Questions("What does OOP stand for?",
                new String[]{"Object Oriented Programming", "Open Output Protocol",
                        "Ordered Object Path", "Object Only Pattern"}, 0, 1));

        bank.add(new Questions("Which keyword lets a class use another class's fields and methods?",
                new String[]{"implements", "extends", "inherits", "uses"}, 1, 2));

        bank.add(new Questions("What is the default value of a boolean field in Java?",
                new String[]{"true", "false", "0", "null"}, 1, 2));

        bank.add(new Questions("Which method is the entry point of a Java program?",
                new String[]{"start()", "run()", "main()", "init()"}, 2, 1));

        bank.add(new Questions("What symbol is used for a single line comment in Java?",
                new String[]{"//", "#", "<!--", "**"}, 0, 1));

        bank.add(new Questions("Which collection does NOT allow duplicate elements?",
                new String[]{"ArrayList", "LinkedList", "Set", "Vector"}, 2, 2));

        bank.add(new Questions("What is used to handle exceptions in Java?",
                new String[]{"if-else", "try-catch", "switch-case", "for-loop"}, 1, 2));

        bank.add(new Questions("Which keyword prevents a class from being inherited?",
                new String[]{"static", "private", "final", "abstract"}, 2, 3));

        bank.add(new Questions("What does the 'this' keyword refer to?",
                new String[]{"the parent class", "the current object", "a static field", "a new object"}, 1, 2));

        bank.add(new Questions("Which access modifier makes a member visible only inside its own class?",
                new String[]{"public", "protected", "private", "default"}, 2, 2));

        bank.add(new Questions("Which of these creates an object in Java?",
                new String[]{"new", "make", "create", "object"}, 0, 1));

        bank.add(new Questions("What is method overriding?",
                new String[]{"Using the same method name with different parameters in one class",
                        "Redefining a parent class method in a child class",
                        "Calling a method twice", "Deleting a method"}, 1, 3));

        bank.add(new Questions("Which loop guarantees the code runs at least once?",
                new String[]{"for", "while", "do-while", "foreach"}, 2, 2));

        Collections.shuffle(bank);
        return bank;
    }
}