package cinema;

import java.text.NumberFormat;
import java.util.Scanner;

public class CinemaRoom {
    Scanner scanner = new Scanner(System.in);
    int income;
    int currentIncome;
    int rows;
    int frontRows;
    int backRows;
    int seats;
    int frontSeats;
    int backSeats;
    int rowCord;
    int seatCord;
    double tickets;
    double purchasedTickets;
    boolean valid = false;
    boolean validNum = false;
    char[][] cinema;

    public void startCinema() {
        fillCinema();
        printMenu();
    }

    public void printMenu(){
        while (!validNum) {
            System.out.println(
                    "1. Show the seats\n" +
                            "2. Buy a ticket\n" +
                            "3. Statistics\n" +
                            "0. Exit");
            int num = scanner.nextInt();
            switch (num) {
                case 1:
                    printCinema();
                    break;
                case 2:
                    selectSeat();
                    getTicketPrice();
                    updateCinema();
                    break;
                case 3:
                    printStatistics();
                    break;
                case 0:
                    validNum = true;
                    break;
            }
        }
    }

    private void printStatistics() {
        System.out.println("Number of purchased tickets: " + (int)purchasedTickets);

        NumberFormat defaultFormat = NumberFormat.getPercentInstance();
        defaultFormat.setMinimumFractionDigits(2);
        System.out.println("Percentage: " + defaultFormat.format((purchasedTickets / tickets)));

        System.out.println("Current income: $" + currentIncome);

        getIncome();
    }

    public void fillCinema() {
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seats = scanner.nextInt();
        cinema = new char[rows][seats];
        tickets = seats * rows;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                cinema[i][j] = 'S';
            }
        }
    }

    public void getIncome() {
        frontRows = rows / 2;
        backRows = rows / 2;
        frontSeats = frontRows * seats;
        backSeats = backRows * seats;


        if (seats * rows <= 60) {
            income = rows * seats * 10;
        } else if (rows % 2 == 1) {
            backRows += 1;
            backSeats = backRows * seats;
            income = (frontSeats * 10) + (backSeats * 8);
        } else {
            income = (frontSeats * 10) + (backSeats * 8);
        }
        System.out.println("Total income: $" + income);
    }


    public void printCinema() {
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 0; i < seats; i++) {
            System.out.print(" " + (i + 1));
        }
        System.out.println();
        for (int i = 0; i < rows; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < seats; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void selectSeat() {
        valid = false;
        while (!valid) {
            System.out.println("Enter a row number:");
            rowCord = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatCord = scanner.nextInt();
            System.out.println();
            if (rowCord > rows || seatCord > seats) {
                System.out.println("Wrong input!");
                valid = false;
            } else if (cinema[rowCord -1][seatCord -1] == 'B') {
                System.out.println("That ticket has already been purchased!");
                valid = false;
            } else {
                valid = true;
            }
        }
    }

    public void updateCinema() {
        if (cinema[rowCord - 1][seatCord - 1] == 'S') {
            cinema[rowCord -1][seatCord -1] = 'B';
            purchasedTickets++;
            printCinema();
        }
    }

    public void getTicketPrice() {
        int ticketPrice;

        if (seats * rows <= 60) {
            ticketPrice = 10;
        } else {
            if (rowCord > (rows / 2)) {
                ticketPrice = 8;
            } else {
                ticketPrice = 10;
            }
        }
        currentIncome += ticketPrice;
        System.out.println("Ticket price: $" + ticketPrice);
        System.out.println();
    }

}
