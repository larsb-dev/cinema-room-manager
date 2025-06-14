package cinema;

import java.util.ArrayList;
import java.util.List;

public class Cinema {

    private int rows;
    private int seatsPerRow;
    private int seats;
    private final List<Ticket> ticketsSold = new ArrayList<>();
    private int income;

    public Cinema(int rows, int seatsPerRow) {
        this.rows = rows;
        this.seatsPerRow = seatsPerRow;
        this.seats = rows * seatsPerRow;
    }

    public void printCinema() {
        String[][] seatingPlan = new String[rows][seatsPerRow];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                seatingPlan[i][j] = "S";
            }
        }

        for (Ticket ticket : ticketsSold) {
            seatingPlan[ticket.getRowNumber() - 1][ticket.getSeatNumber() - 1] = "B";
        }

        System.out.println("Cinema:");
        System.out.print("  ");

        for (int j = 1; j <= seatsPerRow; j++) {
            System.out.print(j + " ");
        }

        System.out.println();

        for (int i = 0; i < rows; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < seatsPerRow; j++) {
                System.out.print(seatingPlan[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int calculateTotalIncome() {
        int totalIncome;
        int front = rows / 2;
        int back = rows - (rows / 2);

        if (seats <= 60) {
            totalIncome = seats * 10;
        } else {
            totalIncome = (front * seatsPerRow * 10) + (back * seatsPerRow * 8);
        }

        return totalIncome;
    }

    public Ticket buyTicket(int rowNumber, int seatNumber) {
        int price;

        if (seats <= 60) {
            price = 10;
        } else {
            price = (rowNumber <= rows / 2) ? 10 : 8;
        }

        Ticket ticket = new Ticket(rowNumber, seatNumber, price);
        addTicket(ticket);

        income += ticket.getPrice();

        return ticket;
    }

    public boolean isSeatTaken(int rowNumber, int seatNumber) {
        boolean seatTaken = false;
        for (Ticket ticket : ticketsSold) {
            if (ticket.getRowNumber() == rowNumber && ticket.getSeatNumber() == seatNumber) {
                seatTaken = true;
                break;
            }
        }
        return seatTaken;
    }

    public int getTicketsSold() {
        return ticketsSold.size();
    }

    public void addTicket(Ticket ticket) {
        ticketsSold.add(ticket);
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getSeatsPerRow() {
        return seatsPerRow;
    }

    public void setSeatsPerRow(int seatsPerRow) {
        this.seatsPerRow = seatsPerRow;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }
}