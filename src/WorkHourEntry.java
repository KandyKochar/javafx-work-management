package workManagement;

// WorkHourEntry class - Kandy Kochar
// Represents a single work hour record with id, name, role, and hours fields

public class WorkHourEntry {

    // Fields
    private int id;
    private String name;
    private String role;
    private double hours;

    // Constructor
    public WorkHourEntry(int id, String name, String role, double hours) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.hours = hours;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public double getHours() {
        return hours;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    // toString for easy display
    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Role: %s | Hours: %.2f", id, name, role, hours);
    }
}
