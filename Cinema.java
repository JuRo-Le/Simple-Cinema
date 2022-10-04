/**
 * 
 */
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Le
 *
 */
class Cinema {
	
    static final String S = "S";
    static final String B = "B";

    static Scanner sc = new Scanner(System.in);
    static String[][] cinema = null;
    static String headLine = "";
    static int rows = 0;
    static int seats = 0;
    static int selectRows = 0;
    static int selectSeats = 0;
    static int cinemaType = 0;
    static int seatNumber = 0;
    static int ticketPrice = 0;
    static int totalSeats = 0;
    static boolean availableSeat = true;
    static float percentage = 0F;
    static int purchased = 0;
    static int currentIncome = 0;
    static int totalIncome = 0;
    
    private static void createCinema() {
        cinema = new String[rows][seats];
        headLine = "Cinema:\n ";
		if (rows >= 10) {
			headLine += " ";
		}
        for (int i = 1; i <= seats; i++) {
            headLine += " " + i;
        }
        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[i].length; j++) {
                    cinema[i][j] = S;
            }
        }
        totalSeats = rows * seats;
        cinemaType = totalSeats <= 60 ? 1 : 2;
    }
    
    private static void showCinema(String[][] arraySeats) {
        System.out.println(headLine);
        for (int i = 0; i < arraySeats.length; i++) {
			if (rows >= 10 && i < 9) {
				System.out.print(" ");
			}
			System.out.print(i + 1);
            for (int j = 0; j < arraySeats[i].length; j++) {
				if (j >= 10) {
					System.out.print(" ");
				}
                System.out.print(" " + arraySeats[i][j]);
            }
            System.out.println();
        }
    }

    private static void defineSeatsNumber() {
        System.out.println("Enter the number of rows:");
        rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seats = sc.nextInt();
    }

    private static void chooseSeats() {
        System.out.println("Enter a row number:");
        selectRows = sc.nextInt();
        System.out.println("Enter a seat number in that row:");
        selectSeats = sc.nextInt();
    }

    private static void defineProfit() {
        totalIncome = 0;
        if (cinemaType == 1) {
            totalIncome = totalSeats * 10;
        } else {
            totalIncome = seats * (rows / 2) * 10 + (totalSeats - (seats * (rows / 2))) * 8;
        }
    }

    private static void showTicketPrice() {
        if (cinemaType == 1 || cinemaType == 2 && selectRows <= rows / 2) {
            ticketPrice = 10;
        } else {
            ticketPrice = 8;
        }
        System.out.println("Ticket price: $"+ ticketPrice);
    }

    private static void updateSeat() {
        if (cinema[selectRows - 1][selectSeats - 1].equals(S)) {
            cinema[selectRows - 1][selectSeats - 1] = B;
            purchased++;
            currentIncome += ticketPrice;
        } 
    }

    private static void checkAvaiableSeat() {
        if (selectRows <= 0 || selectRows > rows || selectSeats <= 0 || selectSeats > seats) {
            availableSeat = false;
            System.out.println("Wrong input!");
        } else if (cinema[selectRows - 1][selectSeats - 1].equals(B)) {
            availableSeat = false;
            System.out.println("That ticket has already been purchased!");
        } else {
            availableSeat = true;
        }
    }

    private static void calculateStatistics() {
        percentage = purchased * 100 / (float)totalSeats;
        System.out.printf("Number of purchased tickets: %1$d" 
                          + "\nPercentage: %2$.2f%%" 
                          + "\nCurrent income: $%3$d" 
                          + "\nTotal income: $%4$d", purchased, percentage, currentIncome, totalIncome);
		showEmptyLine();
    }

    private static void showEmptyLine() {
        System.out.println();
    }

    private static void showMenu() {
        System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
        int selectMenu = sc.nextInt();
        while (selectMenu != 0) {
            switch (selectMenu) {
                case 1:
                    showCinema(cinema);
                    break;
                case 2:
                    do {
                        chooseSeats();
                        checkAvaiableSeat();
                    } while (!availableSeat);
                    
                    if (availableSeat) {
                        showEmptyLine();
                        showTicketPrice();
                        updateSeat();
                    }
                    break;
                case 3:
                    calculateStatistics();
                    break;
                case 0:
                    break;
                default:
                    break;
            }
			showEmptyLine();
            System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
            selectMenu = sc.nextInt();
			showEmptyLine();
        }
    }
    
    public static void main(String[] args) {
        // Write your code here
        defineSeatsNumber();
        createCinema();
        defineProfit();
        showEmptyLine();
        showMenu();
    }
}