
package registration;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final RegistrationManager manager = new RegistrationManager();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n=== Event Registration System ===");
            System.out.println("1. Add registration");
            System.out.println("2. Show all registrations");
            System.out.println("3. Find registration by ID");
            System.out.println("4. Delete registration");
            System.out.println("5. Filter registrations by event");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: enter a number.");
                continue;
            }

            switch (choice) {
                case 1 -> addRegistration();
                case 2 -> manager.displayAll();
                case 3 -> findRegistration();
                case 4 -> deleteRegistration();
                case 5 -> filterByEvent();
                case 0 -> {
                    running = false;
                    System.out.println("Goodbye!");
                }
                default -> System.out.println("Invalid menu option.");
            }
        }

        scanner.close();
    }

    private static void addRegistration() {
        System.out.print("Enter full name: ");
        String name = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter student group: ");
        String group = scanner.nextLine();

        String[] events = manager.getEvents();
        System.out.println("Choose an event:");

        for (int i = 0; i < events.length; i++) {
            System.out.println((i + 1) + ". " + events[i]);
        }

        System.out.print("Event number: ");

        try {
            int eventChoice = Integer.parseInt(scanner.nextLine().trim());

            if (eventChoice < 1 || eventChoice > events.length) {
                System.out.println("Error: choose an event from 1 to "
                        + events.length + ".");
                return;
            }

            manager.addRegistration(
                    name, email, group, events[eventChoice - 1]
            );
        } catch (NumberFormatException e) {
            System.out.println("Error: event choice must be a number.");
        } catch (IllegalArgumentException e) {
            System.out.println("Validation error: " + e.getMessage());
        }
    }

    private static void findRegistration() {
        System.out.print("Enter registration ID: ");

        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            Registration registration = manager.findById(id);

            if (registration == null) {
                System.out.println("Registration not found.");
            } else {
                System.out.println(registration);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: ID must be a number.");
        }
    }

    private static void deleteRegistration() {
        System.out.print("Enter registration ID to delete: ");

        try {
            int id = Integer.parseInt(scanner.nextLine().trim());

            if (manager.deleteById(id)) {
                System.out.println("Registration deleted successfully.");
            } else {
                System.out.println("Registration not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: ID must be a number.");
        }
    }

    private static void filterByEvent() {
        String[] events = manager.getEvents();
        System.out.println("Choose an event:");

        for (int i = 0; i < events.length; i++) {
            System.out.println((i + 1) + ". " + events[i]);
        }

        System.out.print("Event number: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());

            if (choice < 1 || choice > events.length) {
                System.out.println("Invalid event choice.");
                return;
            }

            List<Registration> results =
                    manager.findByEvent(events[choice - 1]);

            if (results.isEmpty()) {
                System.out.println("No registrations for this event.");
            } else {
                for (Registration registration : results) {
                    System.out.println(registration);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: enter a number.");
        }
    }
}
