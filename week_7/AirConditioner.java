package week_7;

public class AirConditioner extends Appliance {
    public AirConditioner(String brand) { super(brand); }
    public void operate() { System.out.println("Cooling the room..."); }
}