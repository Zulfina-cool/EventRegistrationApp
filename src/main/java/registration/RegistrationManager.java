
package registration;

import java.util.ArrayList;
import java.util.List;

public class RegistrationManager {
    private final ArrayList<Registration> registrations = new ArrayList<>();
    private int nextId = 1;

    private final String[] events = {
            "Web Design Workshop",
            "JavaScript Quiz",
            "IT Career Talk"
    };

    public String[] getEvents() {
        return events.clone();
    }

    public void addRegistration(String fullName, String email,
                                String studentGroup, String eventTitle) {
        if (fullName == null || fullName.trim().length() < 3) {
            throw new IllegalArgumentException(
                    "Full name must contain at least 3 characters.");
        }

        if (email == null || !email.matches("^[^@\\s]+@[^@\\s]+$")) {
            throw new IllegalArgumentException(
                    "Email must contain @ with text on both sides.");
        }

        if (studentGroup == null || studentGroup.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Student group cannot be empty.");
        }

        boolean validEvent = false;
        for (String event : events) {
            if (event.equals(eventTitle)) {
                validEvent = true;
                break;
            }
        }

        if (!validEvent) {
            throw new IllegalArgumentException("Invalid event.");
        }

        Registration registration = new Registration(
                nextId, fullName.trim(), email.trim(),
                studentGroup.trim(), eventTitle
        );

        registrations.add(registration);
        System.out.println("Registration created. ID: " + nextId);
        nextId++;
    }

    public void displayAll() {
        if (registrations.isEmpty()) {
            System.out.println("No registrations found.");
            return;
        }

        for (Registration registration : registrations) {
            System.out.println(registration);
        }
    }

    public Registration findById(int id) {
        for (Registration registration : registrations) {
            if (registration.getId() == id) {
                return registration;
            }
        }
        return null;
    }

    public boolean deleteById(int id) {
        for (int i = 0; i < registrations.size(); i++) {
            if (registrations.get(i).getId() == id) {
                registrations.remove(i);
                return true;
            }
        }
        return false;
    }

    public List<Registration> findByEvent(String eventTitle) {
        List<Registration> result = new ArrayList<>();

        for (Registration registration : registrations) {
            if (registration.getEventTitle().equals(eventTitle)) {
                result.add(registration);
            }
        }

        return result;
    }
}
