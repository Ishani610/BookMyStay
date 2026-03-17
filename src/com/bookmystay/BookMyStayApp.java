package com.bookmystay.app;

import java.util.*;

/**
 * UC5: Booking Request (FIFO Queue)
 * Demonstrates fair handling of booking requests using Queue
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

    // Inventory (same as UC3/UC4)
    static class RoomInventory {
        private Map<String, Integer> inventory = new HashMap<>();

        public void addRoom(String type, int count) {
            inventory.put(type, count);
        }

        public int getAvailability(String type) {
            return inventory.getOrDefault(type, 0);
        }
    }

    // Reservation (NEW in UC5)
    static class Reservation {
        private String guestName;
        private String roomType;

        public Reservation(String guestName, String roomType) {
            this.guestName = guestName;
            this.roomType = roomType;
        }

        public String getGuestName() {
            return guestName;
        }

        public String getRoomType() {
            return roomType;
        }

        public void display() {
            System.out.println("Guest: " + guestName + " requested " + roomType);
        }
    }

    // Booking Queue (FIFO)
    static class BookingQueue {
        private Queue<Reservation> queue = new LinkedList<>();

        // Add request
        public void addRequest(Reservation r) {
            queue.add(r);
        }

        // Display all requests
        public void showRequests() {
            System.out.println("\n--- Booking Requests (FIFO Order) ---");
            for (Reservation r : queue) {
                r.display();
            }
        }
    }

    // Main method
    public static void main(String[] args) {

        System.out.println("===== Book My Stay App =====");

        // Room setup (same as before)
        Room r1 = new SingleRoom();
        Room r2 = new DoubleRoom();
        Room r3 = new SuiteRoom();

        // Inventory setup (no updates here)
        RoomInventory inventory = new RoomInventory();
        inventory.addRoom("Single Room", 5);
        inventory.addRoom("Double Room", 3);
        inventory.addRoom("Suite Room", 2);

        // UC5: Booking requests
        BookingQueue bookingQueue = new BookingQueue();

        bookingQueue.addRequest(new Reservation("Alice", "Single Room"));
        bookingQueue.addRequest(new Reservation("Bob", "Suite Room"));
        bookingQueue.addRequest(new Reservation("Charlie", "Double Room"));

        // Display queue (FIFO)
        bookingQueue.showRequests();
    }
}