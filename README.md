# JavaFX Work Management System

An enhanced JavaFX application for managing employee work hours with database persistence using JDBC.

## Overview
Extends the work hours tracker with a full database-backed management system. Employees can log hours, the application validates input, persists records via JDBC, and displays historical data.

## Features
- JavaFX form-based work hour entry
- Input validation with error feedback
- JDBC database integration for persistence
- Work hour history retrieval and display
- Clean MVC-inspired architecture

## Technologies Used
- **Language:** Java (JDK 11+)
- **UI Framework:** JavaFX
- **Database:** JDBC (Oracle/SQL)

## Project Structure
```
src/
+-- WorkHours.java          # Main JavaFX application and UI
+-- WorkHourEntry.java      # Work hour data model
+-- WorkHourDA0.java        # Data Access Object for JDBC operations
+-- DBconnection.java       # Database connection management
+-- inputValidation.java    # Input validation helpers
```

## Usage
```bash
javac --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls -cp ojdbc8.jar src/*.java -d out/
java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls -cp out/:ojdbc8.jar WorkHours
```

## Author
Kandy Kochar
