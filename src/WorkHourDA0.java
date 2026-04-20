package workManagement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// WorkHourDAO class
// Kandy Kochar - Create, Update operations + List management
// Julian De Pasqua - Read, Delete operations + GUI integration

public class WorkHourDA0 {

    // List to store WorkHourEntry objects in memory - Kandy
    private List<WorkHourEntry> entryList = new ArrayList<>();

    // CREATE - Kandy
    public String createEntry(int id, String name, String role, double hours) {

        if (!inputValidation.isValidId(id)) {
            return "Error: ID must be greater than 0.";
        }
        if (!inputValidation.isValidName(name)) {
            return "Error: Name cannot be empty.";
        }
        if (!inputValidation.isValidRole(role)) {
            return "Error: Role cannot be empty.";
        }
        if (!inputValidation.isValidHours(hours)) {
            return "Error: Hours must be between 0 and 168.";
        }

        Connection conn = DBconnection.getConnection();

        if (conn == null) {
            return "Error: Could not connect to database.";
        }

        try {
            String sql = "INSERT INTO WorkHours VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.setString(3, role);
            stmt.setDouble(4, hours);
            stmt.executeUpdate();

            // Also add to in-memory list
            WorkHourEntry entry = new WorkHourEntry(id, name, role, hours);
            entryList.add(entry);

            return "Entry added successfully!";

        } catch (SQLException e) {
            return "Error: Could not add entry. " + e.getMessage();
        } finally {
            DBconnection.closeConnection(conn);
        }
    }

    // UPDATE - Kandy
    public String updateEntry(int id, String name, String role, double hours) {

        if (!inputValidation.isValidId(id)) {
            return "Error: ID must be greater than 0.";
        }
        if (!inputValidation.isValidName(name)) {
            return "Error: Name cannot be empty.";
        }
        if (!inputValidation.isValidRole(role)) {
            return "Error: Role cannot be empty.";
        }
        if (!inputValidation.isValidHours(hours)) {
            return "Error: Hours must be between 0 and 168.";
        }

        Connection conn = DBconnection.getConnection();

        if (conn == null) {
            return "Error: Could not connect to database.";
        }

        try {
            String sql = "UPDATE WorkHours SET name=?, role=?, hours=? WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, role);
            stmt.setDouble(3, hours);
            stmt.setInt(4, id);

            int rows = stmt.executeUpdate();

            if (rows == 0) {
                return "Error: No entry found with ID " + id;
            }

            // Also update in-memory list
            for (WorkHourEntry entry : entryList) {
                if (entry.getId() == id) {
                    entry.setName(name);
                    entry.setRole(role);
                    entry.setHours(hours);
                    break;
                }
            }

            return "Entry updated successfully!";

        } catch (SQLException e) {
            return "Error: Could not update entry. " + e.getMessage();
        } finally {
            DBconnection.closeConnection(conn);
        }
    }

    // READ ALL - Julian
    public String readAllEntries() {

        Connection conn = DBconnection.getConnection();

        if (conn == null) {
            return "Error: Could not connect to database.";
        }

        try {
            String sql = "SELECT * FROM WorkHours";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // Clear in-memory list and reload
            entryList.clear();

            StringBuilder result = new StringBuilder();
            result.append("------ ALL ENTRIES ------\n");

            boolean hasData = false;

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String role = rs.getString("role");
                double hours = rs.getDouble("hours");

                // Add to in-memory list
                WorkHourEntry entry = new WorkHourEntry(id, name, role, hours);
                entryList.add(entry);

                result.append(entry.toString() + "\n");
                hasData = true;
            }

            if (!hasData) {
                return "No entries found in database.";
            }

            return result.toString();

        } catch (SQLException e) {
            return "Error: Could not read entries. " + e.getMessage();
        } finally {
            DBconnection.closeConnection(conn);
        }
    }

    // DELETE - Julian
    public String deleteEntry(int id) {

        if (!inputValidation.isValidId(id)) {
            return "Error: ID must be greater than 0.";
        }

        Connection conn = DBconnection.getConnection();

        if (conn == null) {
            return "Error: Could not connect to database.";
        }

        try {
            String sql = "DELETE FROM WorkHours WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            int rows = stmt.executeUpdate();

            if (rows == 0) {
                return "Error: No entry found with ID " + id;
            }

            // Also remove from in-memory list
            entryList.removeIf(entry -> entry.getId() == id);

            return "Entry deleted successfully!";

        } catch (SQLException e) {
            return "Error: Could not delete entry. " + e.getMessage();
        } finally {
            DBconnection.closeConnection(conn);
        }
    }

    // Get in-memory list - Kandy
    public List<WorkHourEntry> getEntryList() {
        return entryList;
    }
}