package com.bookmystay.app;

import java.util.*;

/**
 * UC6: Reservation Confirmation & Room Allocation
 * Demonstrates FIFO processing + Set for uniqueness + inventory update
 */
public class BookMyStayApp {

    // Abstract Room
    static abstract class Room {
        protected String type;
        protected int beds;
        protected double price;

        public Room(String type, int beds, double price) {
            this.type = type;
            this.beds = beds;
            this.price = price;
        }
    }

    static class SingleRoom extends Room {
        public SingleRoom() { super("Single Room", 1, 1000); }
    }

    static class DoubleRoom extends Room {
        public DoubleRoom() { super("Double Room", 2, 2000); }
    }

    static class SuiteRoom extends Room {
        public SuiteRoom() { super("Suite Room", 3, 5000); }
    }

    // Inventory
    static class RoomInventory {
        private Map<String, Integer> inventory = new HashMap<>();

        public void addRoom(String type, int count) {
            inventory.put(type, count);
        }

        public int getAvailability(String type) {
            return inventory.getOrDefault(type, 0);
        }

        public void reduceRoom(String type) {
            inventory.put(type, inventory.get(type) - 1);
        }
    }

    // Reservation request
    static class Reservation {
        String guestName;
        String roomType;

        public Reservation(String guestName, String roomType) {
            this.guestName = guestName;
            this.roomType = roomType;
        }
    }

    // Booking Queue (FIFO)
    static class BookingQueue {
        Queue<Reservation> queue = new LinkedList<>();

        public void addRequest(Reservation r) {
            queue.add(r);
        }

        public Reservation getNext() {
            return queue.poll(); // FIFO
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    // Booking Service (CORE UC6)
    static class BookingService {

        // Prevent duplicate room IDs
        private Set<String> usedRoomIds = new HashSet<>();

        // Track allocated rooms by type
        private Map<String, Set<String>> allocatedRooms = new HashMap<>();

        private int idCounter = 1;

        public void processBookings(BookingQueue queue, RoomInventory inventory) {

            System.out.println("\n--- Processing Bookings ---");

            while (!queue.isEmpty()) {

                Reservation r = queue.getNext();

                int available = inventory.getAvailability(r.roomType);

                if (available > 0) {

                    // Generate unique room ID
                    String roomId = r.roomType.substring(0, 2).toUpperCase() + idCounter++;

                    // Ensure uniqueness
                    if (!usedRoomIds.contains(roomId)) {

                        usedRoomIds.add(roomId);

                        // Map room type → allocated IDs
                        allocatedRooms.putIfAbsent(r.roomType, new HashSet<>());
                        allocatedRooms.get(r.roomType).add(roomId);

                        // Update inventory immediately
                        inventory.reduceRoom(r.roomType);

                        System.out.println("Booking Confirmed for " + r.guestName +
                                " | Room ID: " + roomId);
                    }

                } else {
                    System.out.println("Booking Failed for " + r.guestName +
                            " (No availability)");
                }
            }
        }
    }

    // MAIN
    public static void main(String[] args) {

        System.out.println("===== Book My Stay App =====");

        // Inventory setup
        RoomInventory inventory = new RoomInventory();
        inventory.addRoom("Single Room", 2);
        inventory.addRoom("Double Room", 1);

        // Booking queue
        BookingQueue queue = new BookingQueue();

        queue.addRequest(new Reservation("Alice", "Single Room"));
        queue.addRequest(new Reservation("Bob", "Single Room"));
        queue.addRequest(new Reservation("Charlie", "Single Room")); // should fail
        queue.addRequest(new Reservation("David", "Double Room"));

        // Process bookings
        BookingService service = new BookingService();
        service.processBookings(queue, inventory);
    }
}