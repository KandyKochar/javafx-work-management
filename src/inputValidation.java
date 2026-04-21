package workManagement;

public class inputValidation {

    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public static boolean isValidRole(String role) {
        return role != null && !role.trim().isEmpty();
    }

    public static boolean isValidHours(double hours) {
        return hours >= 0 && hours <= 24 * 7; // is the amount of hours in a week 24*7
    }

    public static boolean isValidId(int id) {
        return id > 0;
    }
}