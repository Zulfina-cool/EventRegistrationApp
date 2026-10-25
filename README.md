# Event Registration Console Application

## Description
This is a Java console application for managing event registrations. Users can add, view, search, delete, and filter registrations by event.

## Technologies
- Java 17
- Gradle
- ArrayList for in-memory storage
- Scanner for console input

## Available Events
1. Web Design Workshop
2. JavaScript Quiz
3. IT Career Talk

## Project Structure
- `Main.java` — displays the menu and handles user input.
- `Registration.java` — represents a registration with its ID, full name, email, student group, and event.
- `RegistrationManager.java` — stores registrations and implements validation, searching, filtering, and deletion.

## How to Run
1. Install JDK 17 or later.
2. Open the project in IntelliJ IDEA.
3. Run the `registration.Main` class, or run the `run` task from Gradle.

## Validation
The application validates full names, email addresses, student groups, event selections, and numeric input. Invalid registrations are not stored.

## Storage
Registrations are stored in an in-memory `ArrayList`. The data is cleared when the application terminates.