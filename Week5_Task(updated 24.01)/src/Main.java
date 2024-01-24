import Entities.Book;
import Entities.Library;
import Helpers.Helper;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Creating books
        //Automatic id given
        Book book1 = new Book("Kozetta", "Viktor Hugo", "French", 10, 5);
        Book book2 = new Book("Robinzon Kruzo", " Daniel Defoe", "English", 16, 8);
        Book book3 = new Book("Robinzon Kruzo", " Daniel Defoe", "English", 20, 8);
        Book book4 = new Book("Robinzon Kruzo", " Daniel Defoe", "English", 20, 8);
        //Manually id given
        Book book5 = new Book(10,"Cinayet ve Ceza", "Fyodr Dostoyevski", "Russian", 10, 15);
        Book book6 = new Book(11,"Bakı bulvarı", "Chingiz Abdullayev", "Azerbaijani", 10, 20);

        // Creating a library and adding books
        Library library = new Library();
        library.AddBook(book1);
        library.AddBook(book2);
        library.AddBook(book3);
        library.AddBook(book4);
        library.AddBook(book5);
        library.AddBook(book6);
        // Adding a new book with the same name to the library
        Book newBook = new Book(11,"Bakı bulvarı", "Chingiz Abdullayev", "Azerbaijani", 10, 10);
        library.AddBook(newBook);

        Scanner scan=new Scanner(System.in);
        // Showing all books in the library


        while(true)
        {
            System.out.println("Please enter the name of the library: ");
            String libname=scan.nextLine();
            library.setName(libname);

            System.out.println("Hi! Welcome to our library:");
            System.out.println("Here is what we have: ");
            library.ShowBooks();
            //Menu

            while(true) {

                System.out.println("\n1. Add book");
                System.out.println("2. Update book");
                System.out.println("3. Delete book");
                System.out.println("4. Find book");
                System.out.println("5. Show books");
                System.out.println("6. Kitabxananin adini deyish");
                System.out.println("Novbeti emeliyyati sechin(7 for exiting): ");
                String choice = scan.nextLine();
                scan.nextLine();  // Consume the newline character
                int input = Helper.tryParseInt(choice);

                switch (input) {
                    case 1:
                        System.out.println("Enter book details:");
                        System.out.print("Title: ");
                        String title = scan.nextLine();
                        System.out.print("Author: ");
                        String author = scan.nextLine();
                        System.out.print("Language: ");
                        String language = scan.nextLine();

                        int price;
                        while (true) {
                            System.out.print("Price: ");
                            String priceInput = scan.nextLine();
                            price = Helper.tryParseInt(priceInput);
                            if (price != 0) {
                                break;
                            } else {
                                System.out.println("Please enter a valid integer for price.");
                            }
                        }

                        // Input validation for count
                        int count;
                        while (true) {
                            System.out.print("Count: ");
                            String countInput = scan.nextLine();
                            count = Helper.tryParseInt(countInput);
                            if (count != 0) {
                                break;
                            } else {
                                System.out.println("Please enter a valid integer for count.");
                            }
                        }

                        //Automatically assigns new ID
                        Book newB = new Book(title, author, language, price, count);
                        library.AddBook(newB);

                        System.out.println("Book added successfully!");
                        break;
                    case 2:
                        System.out.println("Enter the book ID to update:");
                        int bookIdToUpdate = scan.nextInt();

                        Book bookToUpdate = library.FindBookById(bookIdToUpdate);

                        if (bookToUpdate != null) {
                            System.out.println("What do you want to update?");
                            System.out.println("1. Count");
                            System.out.println("2. Price");

                            int c = scan.nextInt();
                            scan.nextLine();

                            switch (c) {
                                case 1:
                                    System.out.println("Enter the new count (only Integer):");
                                    int newCount;

                                    // Input validation for count
                                    while (true) {
                                        System.out.print("Count: ");
                                        String countInput = scan.nextLine();
                                        newCount = Helper.tryParseInt(countInput);
                                        if (newCount != 0) {
                                            break;
                                        } else {
                                            System.out.println("Please enter a valid integer for count.");
                                        }
                                    }
                                    bookToUpdate.setCount(newCount);
                                    System.out.println("Count updated successfully!");
                                    break;

                                case 2:
                                    System.out.println("Enter the new price (only Integer):");
                                    int newPrice;
                                    while (true) {
                                        System.out.print("Price: ");
                                        String priceInput = scan.nextLine();
                                        newPrice = Helper.tryParseInt(priceInput);
                                        if (newPrice != 0) {
                                            break;
                                        } else {
                                            System.out.println("Please enter a valid integer for price.");
                                        }
                                    }
                                    bookToUpdate.setPrice(newPrice);
                                    System.out.println("Price updated successfully!");
                                    break;

                                default:
                                    System.out.println("Invalid choice.");
                                    break;
                            }
                        } else {
                            System.out.println("Book with ID " + bookIdToUpdate + " not found.");
                        }

                        break;



                    case 3:
                        // Delete book from library
                        System.out.println("Enter the book ID to delete:");
                        int bookIdToDelete = scan.nextInt();
                        scan.nextLine();  // Consume the newline character bunu yazmayanda islemir

                        library.DeleteBookById(bookIdToDelete);

                        break;

                    case 4:
                        //Find book and display info
                        //User can enter ID, Author, or name

                        System.out.println("Enter the details for searching(ID, Name or Author)");


                        while(true) {
                            System.out.println("1 for ID search.");
                            System.out.println("2 for Name search.");
                            System.out.println("3 for Author search.");

                            int select;
                            select=scan.nextInt();
                            scan.nextLine(); // Consume the newline character Bunu yazmayanda islemir

                            if (select==1 || select==2 || select==3)
                            {
                                System.out.println("Please enter the keyword: ");
                                String keyword = scan.nextLine();
                                Library foundBooks = library.FindBookByKeyword(keyword, select);

                                if (foundBooks != null) {
                                    System.out.println("Book found. Here are the details:");
                                    foundBooks.ShowBooks();
                                    break;
                                }
                                else {
                                    System.out.println("Book with " + keyword + " not found.");
                                    break;
                                }
                            }
                            else
                            {
                                System.out.println("Please enter 1,2 or 3!");
                            }

                        }


                        break;
                    case 5:
                        System.out.println("Books in Library:");
                        library.ShowBooks();
                        break;
                    case 6:
                        // Change library name
                        System.out.println("Enter the new library name:");
                        String newLibName = scan.nextLine();
                        library.setName(newLibName);
                        System.out.println("Library name updated successfully!");
                        break;
                    case 7:
                        System.out.println("Exiting the program. Goodbye!");
                        System.exit(0); // Exit the program
                        break;


                }
            }
            }
    }

}











