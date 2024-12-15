import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CustomerList customers = new CustomerList(); 
        MovieList movies = new MovieList(); 
        RentalList[] rentals = new RentalList[1000]; 
        int rentalCount = 0;

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nWelcome to Paul's Video Store! What would you like to do?");
            System.out.println("1. Add a Customer");
            System.out.println("2. Add a Movie");
            System.out.println("3. Rent a Movie");
            System.out.println("4. Return a Movie");
            System.out.println("5. Search Who Rented a Movie by Title");
            System.out.println("6. Display All Customers");
            System.out.println("7. Display All Movies");
            System.out.println("8. Exit");
            System.out.print("Please enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            if (choice < 1 || choice > 8) {
                System.out.println("Invalid choice! Please enter a number between 1 and 8.");
                continue; 
            }
            if (choice == 1) {
                System.out.print("Enter phone number: ");
                String phone = scanner.nextLine();
                if (phone.length() != 10) {
                    System.out.println("That is not a valid phone number. Please reenter.");
                    phone = scanner.nextLine();
                }
                System.out.print("Enter first name: ");
                String firstName = scanner.nextLine();
                System.out.print("Enter last name: ");
                String lastName = scanner.nextLine();
                customers.insert(new Customer(phone, firstName, lastName));
                System.out.println("Customer added!");
            }
            if (choice == 2) {
                System.out.print("Enter 12-digit barcode: ");
                String barcode = scanner.nextLine();
                if (barcode.length() != 12) {
                    System.out.println("Barcode must be 12 digits. Please enter a new barcode.");
                    barcode = scanner.nextLine();
                }
                System.out.print("Enter movie title: ");
                String title = scanner.nextLine();
                movies.insert(new Movie(barcode, title));
                System.out.println("Movie added!");
            }
            if (choice == 3) {
                System.out.print("Enter customer phone number: ");
                String rentPhone = scanner.nextLine();
                if (!customers.contains(rentPhone) || rentPhone.length() != 10) {
                    System.out.println("Customer does not exist or phone number is invalid. Please reenter.");
                    rentPhone = scanner.nextLine();
                }
                Customer customer = null;
                CustomerList.Node current = customers.head; // starting from the head, traverse customerlist
                while (current != null) {
                    if (current.data.getPhone().equals(rentPhone)) { // checking rentphone in each node
                        customer = current.data;  // get the customer data (full name) according to their phone number
                        break; // exit loop
                    }
                    current = current.next; 
                }

                System.out.print("Enter movie barcode: ");
                String rentBarcode = scanner.nextLine();
                Movie movieToRent = movies.searchBarcode(rentBarcode);
                
                while (movieToRent == null || rentBarcode.length() != 12) {
                    if (rentBarcode.length() != 12) {
                        System.out.println("Barcode must be 12 digits. Please reenter:");
                    } else {
                        System.out.println("Movie not found! Please reenter:");
                    }
                    rentBarcode = scanner.nextLine();
                    movieToRent = movies.searchBarcode(rentBarcode); 
                }
                int movieCount = 0;
                for (RentalList rental : rentals) {
                    if (rental != null && rental.getCustomer().getPhone().equals(rentPhone)) {
                        movieCount++; // adding 1 to the amount of movies customer has rented
                    }
                }
                if (movieCount >= 3) {
                    System.out.println("Customer already has 3 movies rented!"); // if customer has more than 3 movies rented
                }
                
                rentals[rentalCount++] = new RentalList(customer, movieToRent);
                System.out.println("Movie rented!");
                movies.delete(movieToRent.getBarcode()); // remove the rented movie from movielist 
            }
            if (choice == 4) {
                System.out.print("Enter customer phone number: ");
                String returnPhone = scanner.nextLine();
                if (!customers.contains(returnPhone) || returnPhone.length() != 10) {
                    System.out.println("Customer does not exist or phone number is invalid. Please reenter.");
                    returnPhone = scanner.nextLine();
                }
                System.out.print("Enter movie barcode: ");
                String returnBarcode = scanner.nextLine();

                boolean returned = false;
                for (int i = 0; i < rentalCount; i++) {
                    if (rentals[i] != null && rentals[i].getCustomer().getPhone().equals(returnPhone) && rentals[i].getMovie().barcode.equals(returnBarcode)) { // find and remove rental
                        Movie movieToReturn = rentals[i].getMovie();
                        rentals[i] = null; 
                        System.out.println("Movie returned!");
                        returned = true;
                        movies.insert(movieToReturn); // add the returned movie back into movielist
                        break;
                    }
                }
                if (!returned) {
                    System.out.println("Rental record not found! Try again.");
                    returnBarcode = scanner.nextLine();

                }
            }
            if (choice == 5) {
                System.out.print("Enter movie title: ");
                String searchTitle = scanner.nextLine();
                boolean found = false;
                for (RentalList rental : rentals) {
                    if (rental != null && rental.getMovie().getTitle().equalsIgnoreCase(searchTitle)) { // if rental is not null and the movie is in movielist, return true
                        System.out.println(rental);
                        found = true;
                    }
                }
                if (!found) {
                    System.out.println("No one has rented this movie.");
                }
            }
            if (choice == 6) {
                System.out.println("All Customers:");
                customers.print();
            }
            if (choice == 7) {
                System.out.println("All Movies:");
                movies.print();
            }
            if (choice == 8) {
                System.out.println("Exiting...");
            }
        } while (choice != 8);

        scanner.close();
    }
}

