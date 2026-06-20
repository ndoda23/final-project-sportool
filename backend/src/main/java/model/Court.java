package model;

public class Court {

    private int id;
    private String name;
    private String type; // FOOTBALL, TENNIS, PADEL...
    private String location;
    private double pricePerHour;

    public Court() {}

    public Court(int id, String name, String type, String location, double pricePerHour) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.location = location;
        this.pricePerHour = pricePerHour;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public double getPricePerHour() { return pricePerHour; }
    public void setPricePerHour(double pricePerHour) { this.pricePerHour = pricePerHour; }
}