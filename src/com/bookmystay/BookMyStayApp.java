package com.bookmystay.app;

import java.util.HashMap;
import java.util.Map;

/**
 * UC3: Centralized Room Inventory Management
 * Demonstrates use of HashMap for managing room availability
 */
public class BookMyStayApp {

    // Abstract Room class (same as UC2)
    static abstract class Room {
        protected String type;
        protected int beds;
        protected double price;

        public Room(String type, int beds, double price) {
            this.type = type;
            this.beds = beds;
            this.price = price;
        }

        public void displayDetails() {
            System.out.println("Type: " + type + ", Beds: " + beds + ", Price: " + price);
        }
    }

    static class SingleRoom extends Room {
        public SingleRoom() {
            super("Single Room", 1, 1000);
        }
    }

    static class DoubleRoom extends Room {
        public DoubleRoom() {
            super("Double Room", 2, 2000);
        }
    }

    static class SuiteRoom extends Room {
        public SuiteRoom() {
            super("Suite Room", 3, 5000);
        }
    }

    // Inventory Class (NEW in UC3)
    static class RoomInventory {

        private Map<String, Integer> inventory;

        // Constructor → initialize inventory
        public RoomInventory() {
            inventory = new HashMap<>();
        }

        // Add room type with count
        public void addRoom(String type, int count) {
            inventory.put(type, count);
        }

        // Get availability
        public int getAvailability(String type) {
            return inventory.getOrDefault(type, 0);
        }

        // Update availability
        public void updateAvailability(String type, int count) {
            inventory.put(type, count);
        }

        // Display inventory
        public void displayInventory() {
            System.out.println("\n--- Room Inventory ---");
            for (String type : inventory.keySet()) {
                System.out.println(type + " -> " + inventory.get(type));
            }
        }
    }

    // Main method
    public static void main(String[] args) {

        System.out.println("===== Book My Stay App =====");

        // Create room objects (same as UC2)
        Room r1 = new SingleRoom();
        Room r2 = new DoubleRoom();
        Room r3 = new SuiteRoom();

        r1.displayDetails();
        r2.displayDetails();
        r3.displayDetails();

        // UC3 logic: centralized inventory
        RoomInventory inventory = new RoomInventory();

        // Initialize inventory
        inventory.addRoom("Single Room", 5);
        inventory.addRoom("Double Room", 3);
        inventory.addRoom("Suite Room", 2);

        // Display inventory
        inventory.displayInventory();

        // Check availability
        System.out.println("\nAvailable Single Rooms: " +
                inventory.getAvailability("Single Room"));

        // Update availability
        inventory.updateAvailability("Single Room", 4);

        System.out.println("Updated Single Rooms: " +
                inventory.getAvailability("Single Room"));
    }
}