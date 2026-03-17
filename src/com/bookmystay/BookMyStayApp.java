package com.bookmystay.app;

import java.util.*;

/**
 * UC4: Room Search & Availability Check
 * Demonstrates read-only search using inventory
 */
public class BookMyStayApp {

    // Abstract Room class
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

    // Inventory (same as UC3)
    static class RoomInventory {
        private Map<String, Integer> inventory;

        public RoomInventory() {
            inventory = new HashMap<>();
        }

        public void addRoom(String type, int count) {
            inventory.put(type, count);
        }

        public int getAvailability(String type) {
            return inventory.getOrDefault(type, 0);
        }

        public void displayInventory() {
            System.out.println("\n--- Room Inventory ---");
            for (String type : inventory.keySet()) {
                System.out.println(type + " -> " + inventory.get(type));
            }
        }
    }

    // NEW: Search Service (READ-ONLY)
    static class SearchService {

        public void searchAvailableRooms(RoomInventory inventory, List<Room> rooms) {

            System.out.println("\n--- Available Rooms ---");

            for (Room room : rooms) {

                int available = inventory.getAvailability(room.type);

                // Only show available rooms
                if (available > 0) {
                    room.displayDetails();
                    System.out.println("Available: " + available);
                }
            }
        }
    }

    // Main method
    public static void main(String[] args) {

        System.out.println("===== Book My Stay App =====");

        // Room objects
        Room r1 = new SingleRoom();
        Room r2 = new DoubleRoom();
        Room r3 = new SuiteRoom();

        List<Room> rooms = Arrays.asList(r1, r2, r3);

        // Inventory setup
        RoomInventory inventory = new RoomInventory();
        inventory.addRoom("Single Room", 5);
        inventory.addRoom("Double Room", 0); // unavailable
        inventory.addRoom("Suite Room", 2);

        // Search (READ ONLY)
        SearchService searchService = new SearchService();
        searchService.searchAvailableRooms(inventory, rooms);
    }
}