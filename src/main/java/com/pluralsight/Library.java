package com.pluralsight;

import java.util.Scanner;

public class Library {
    private static Book[] books = new Book[20];

    private static int bookCount = 0;

    public static void main(String[] args) {
        books[bookCount++] = new Book(101, "12345678A", "Zayn Wayne", true, "Zain");
        books[bookCount++] = new Book(102, "12345678B", "Harry Potter", true, "Imaan");
        books[bookCount++] = new Book(103, "12345678C", "Diary Of The Wimpy Kid", true, "Nomi");
        books[bookCount++] = new Book(104, "12345678D", "Harry", false, "");
        books[bookCount++] = new Book(105, "12345679A", "Malala", true, "Ray");

        Scanner myScanner = new Scanner(System.in);

        menuList(myScanner);

        myScanner.close();
    }

    public static void menuList(Scanner scanner){
        boolean isFinished = false;
        while(!isFinished){
            System.out.println("Choose your option:\n1. Show available books.\n2. Show checked out books\n3. Exit\nEnter your command: ");
            int command = scanner.nextInt();
            scanner.nextLine();
            switch(command){
                case 1:
                    showAvailableBooks(scanner);
                    break;
                case 2:
                    showCheckedOutBooks(scanner);
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    isFinished = true;
                    break;
                default:
                    System.out.println("Invalid Input");
                    break;
            }
        }
    }

    public static void showAvailableBooks(Scanner scanner){
        boolean available = false;
        for(int i = 0; i < bookCount; i++){
            if(!books[i].isItCheckedOut()) {
                System.out.println(books[i].availableBooks());
                available = true;
            }
        }
        if(!available){
            System.out.println("No Books Available");
            return;
        }
        checkOutBook(scanner);
    }

    public static void showCheckedOutBooks(Scanner scanner){
        boolean checked = false;
        for(int i = 0; i < bookCount; i++){
            if(books[i].isItCheckedOut()) {
                System.out.println(books[i].checkedOutBooks());
                checked = true;
            }
        }
        if(!checked){
            System.out.println("All Books Available!");
            return;
        }
        returnBook(scanner);
    }

    public static void checkOutBook(Scanner scanner){
        System.out.println("What is your name?");
        String name = scanner.nextLine();
        System.out.println("What book do you want to check out?(Enter \"B\" if you want to go back.");
        String title = scanner.nextLine();
        if(title.equalsIgnoreCase("B")) return;
        for(int i = 0; i < bookCount; i++){
            if(books[i].getTitle().equalsIgnoreCase(title)){
                if(books[i].isItCheckedOut()){
                    System.out.println("Sorry, selected book is already checked out.");
                    return;
                }
                books[i].checkOut(name);
                System.out.println("You Checked Out: " + books[i].getTitle());
                return;
            }
        }
        System.out.println("Not Found");
    }

    public static void checkInBook(Scanner scanner){
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Enter Book ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getId() == bookId && books[i].getCheckedOutTo().equalsIgnoreCase(name)) {
                books[i].checkIn();
                System.out.println("Thank you for returning : " + books[i].getTitle());
                return;
            }
        }
        System.out.println("Non Existing Book or Not Checked Out Book");
    }

    public static void returnBook(Scanner scanner){
        System.out.println("Do you want to check in a book?\nA - to check in a book\nB - to go back to the home screen");
        String prompt = scanner.nextLine();
        switch(prompt.toUpperCase()){
            case "A":
                checkInBook(scanner);
                break;
            case "B":
                System.out.println("Going back to home screen");
                return;
            default:
                System.out.println("Invalid input");
                break;
        }
    }
}