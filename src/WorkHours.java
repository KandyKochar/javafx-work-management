package workManagement;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

// WorkHours GUI
// Kandy Kochar - Add and Update buttons
// Julian De Pasqua - Load and Delete buttons + GUI integration

public class WorkHours extends Application {

    // DAO instance to handle database operations
    WorkHourDA0 dao = new WorkHourDA0();

    @Override
    public void start(Stage primaryStage) {

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        // Labels
        Label lblId = new Label("ID:");
        Label lblName = new Label("Name:");
        Label lblRole = new Label("Role:");
        Label lblHours = new Label("Hours:");

        // Text Fields
        TextField txtId = new TextField();
        txtId.setPromptText("e.g., 4");

        TextField txtName = new TextField();
        txtName.setPromptText("e.g., Kandy Kochar");

        TextField txtRole = new TextField();
        txtRole.setPromptText("e.g., Coordinator");

        TextField txtHours = new TextField();
        txtHours.setPromptText("e.g., 40");

        // Output area
        TextArea txtOutput = new TextArea();
        txtOutput.setPrefHeight(200);
        txtOutput.setEditable(false);
        txtOutput.setWrapText(true);

        // Buttons - Kandy
        Button btAdd = new Button("Add to Database");
        Button btUpdate = new Button("Update Entry");

        // Buttons - Julian
        Button btLoad = new Button("Load from Database");
        Button btDelete = new Button("Delete Entry");

        // Layout
        pane.add(lblId, 0, 0);
        pane.add(txtId, 1, 0);

        pane.add(lblName, 0, 1);
        pane.add(txtName, 1, 1);

        pane.add(lblRole, 0, 2);
        pane.add(txtRole, 1, 2);

        pane.add(lblHours, 0, 3);
        pane.add(txtHours, 1, 3);

        pane.add(btAdd, 0, 4);
        pane.add(btUpdate, 1, 4);
        pane.add(btLoad, 0, 5);
        pane.add(btDelete, 1, 5);

        pane.add(txtOutput, 0, 6, 2, 1);

        // ADD button - Kandy
        btAdd.setOnAction(e -> {

            String idText = txtId.getText().trim();
            String name = txtName.getText().trim();
            String role = txtRole.getText().trim();
            String hoursText = txtHours.getText().trim();

            if (idText.isEmpty() || name.isEmpty() || role.isEmpty() || hoursText.isEmpty()) {
                txtOutput.appendText("Error: All fields must be filled.\n");
                return;
            }

            int id;
            double hours;

            try {
                id = Integer.parseInt(idText);
            } catch (NumberFormatException ex) {
                txtOutput.appendText("Error: ID must be a valid number.\n");
                return;
            }

            try {
                hours = Double.parseDouble(hoursText);
            } catch (NumberFormatException ex) {
                txtOutput.appendText("Error: Hours must be a valid number.\n");
                return;
            }

            String result = dao.createEntry(id, name, role, hours);
            txtOutput.appendText(result + "\n");

            txtId.clear();
            txtName.clear();
            txtRole.clear();
            txtHours.clear();
        });

        // UPDATE button - Kandy
        btUpdate.setOnAction(e -> {

            String idText = txtId.getText().trim();
            String name = txtName.getText().trim();
            String role = txtRole.getText().trim();
            String hoursText = txtHours.getText().trim();

            if (idText.isEmpty() || name.isEmpty() || role.isEmpty() || hoursText.isEmpty()) {
                txtOutput.appendText("Error: All fields must be filled.\n");
                return;
            }

            int id;
            double hours;

            try {
                id = Integer.parseInt(idText);
            } catch (NumberFormatException ex) {
                txtOutput.appendText("Error: ID must be a valid number.\n");
                return;
            }

            try {
                hours = Double.parseDouble(hoursText);
            } catch (NumberFormatException ex) {
                txtOutput.appendText("Error: Hours must be a valid number.\n");
                return;
            }

            String result = dao.updateEntry(id, name, role, hours);
            txtOutput.appendText(result + "\n");

            txtId.clear();
            txtName.clear();
            txtRole.clear();
            txtHours.clear();
        });

        // LOAD button - Julian
        btLoad.setOnAction(e -> {
            String result = dao.readAllEntries();
            txtOutput.clear();
            txtOutput.appendText(result + "\n");
        });

        // DELETE button - Julian
        btDelete.setOnAction(e -> {

            String idText = txtId.getText().trim();

            if (idText.isEmpty()) {
                txtOutput.appendText("Error: Please enter an ID to delete.\n");
                return;
            }

            int id;

            try {
                id = Integer.parseInt(idText);
            } catch (NumberFormatException ex) {
                txtOutput.appendText("Error: ID must be a valid number.\n");
                return;
            }

            String result = dao.deleteEntry(id);
            txtOutput.appendText(result + "\n");

            txtId.clear();
            txtName.clear();
            txtRole.clear();
            txtHours.clear();
        });

        // Scene and Stage
        Scene scene = new Scene(pane, 520, 550);
        primaryStage.setTitle("Work Hours Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}