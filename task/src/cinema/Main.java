package cinema;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int seatsPerRow = scanner.nextInt();

        Cinema cinema = new Cinema(rows, seatsPerRow);

        int choice;

        String menu =
                """
                1. Show the seats
                2. Buy a ticket
                3. Statistics
                0. Exit
                """;

        do {
            System.out.println(menu);
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> cinema.printCinema();
                case 2 -> {
                    while (true) {
                        System.out.println("Enter a row number:");
                        int rowNumber = scanner.nextInt();

                        if (rowNumber < 1 || rowNumber > cinema.getRows()) {
                            System.out.println("Wrong input");
                            break;
                        }

                        System.out.println("Enter a seat number in that row:");
                        int seatNumber = scanner.nextInt();

                        if (seatNumber < 1 || seatNumber > cinema.getSeatsPerRow()) {
                            System.out.println("Wrong input");
                            break;
                        }

                        if (!cinema.isSeatTaken(rowNumber, seatNumber)) {
                            Ticket ticket = cinema.buyTicket(rowNumber, seatNumber);
                            System.out.println("Ticket price: $" + ticket.getPrice());
                            break;
                        } else {
                            System.out.println("That ticket has already been purchased!");
                        }
                    }
                }
                case 3 -> {
                    System.out.println("Number of purchased tickets: " + cinema.getTicketsSold());
                    System.out.println("Percentage: " + cinema.getTicketsSold() / cinema.getSeats() * 100);
                    System.out.println("Current income: " + cinema.getIncome());
                    System.out.println("Total income: " + cinema.calculateTotalIncome());
                }
            }

        } while (!"0".equals(String.valueOf(choice)));
    }
}