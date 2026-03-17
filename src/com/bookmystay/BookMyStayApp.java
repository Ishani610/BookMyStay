package com.bookmystay.app;

/**
 * UC2: Basic Room Types & Static Availability
 * Demonstrates abstraction, inheritance, and polymorphism
 */
public class BookMyStayApp {

    // Abstract class
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

    // Single Room
    static class SingleRoom extends Room {
        public SingleRoom() {
            super("Single Room", 1, 1000);
        }
    }

    // Double Room
    static class DoubleRoom extends Room {
        public DoubleRoom() {
            super("Double Room", 2, 2000);
        }
    }

    // Suite Room
    static class SuiteRoom extends Room {
        public SuiteRoom() {
            super("Suite Room", 3, 5000);
        }
    }

    // Main method
    public static void main(String[] args) {

        System.out.println("===== Book My Stay App =====");

        // Polymorphism
        Room r1 = new SingleRoom();
        Room r2 = new DoubleRoom();
        Room r3 = new SuiteRoom();

        // Static availability
        boolean singleAvailable = true;
        boolean doubleAvailable = false;
        boolean suiteAvailable = true;

        // Display
        r1.displayDetails();
        System.out.println("Available: " + singleAvailable);

        r2.displayDetails();
        System.out.println("Available: " + doubleAvailable);

        r3.displayDetails();
        System.out.println("Available: " + suiteAvailable);
    }
}