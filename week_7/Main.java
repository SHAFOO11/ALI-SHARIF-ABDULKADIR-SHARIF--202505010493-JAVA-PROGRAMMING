package week_7;

public class Main {
    public static void main(String[] args) {
        Appliance a1 = new WashingMachine("LG");
        a1.displayBrand();
        a1.turnOn();
        a1.operate();
        a1.turnOff();

        System.out.println();

        Appliance a2 = new Refrigerator("Panasonic");
        a2.displayBrand();
        a2.turnOn();
        a2.operate();
        a2.turnOff();
    }
}