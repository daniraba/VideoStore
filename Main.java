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

            switch (choice) {
                case 1: 
                    System.out.print("Enter phone number: ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter first name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Enter last name: ");
                    String lastName = scanner.nextLine();
                    customers.insert(new Customer(phone, firstName, lastName));
                    System.out.println("Customer added!");
                    break;

                case 2: 
                    System.out.print("Enter 12-digit barcode: ");
                    String barcode = scanner.nextLine();
                    if (barcode.length() != 12) {
                        System.out.println("Barcode must be 12 digits.");
                        break;
                    }
                    System.out.print("Enter movie title: ");
                    String title = scanner.nextLine();
                    movies.insert(new Movie(barcode, title));
                    System.out.println("Movie added!");
                    break;

                case 3: 
                    System.out.print("Enter customer phone number: ");
                    String rentPhone = scanner.nextLine();
                    if (!customers.contains(rentPhone)) {
                        System.out.println("Customer does not exist!");
                        break;
                    }
                    Customer customer = null;
                    CustomerList.Node current = customers.head;
                    while (current != null) {
                        if (current.data.getPhone().equals(rentPhone)) {
                            customer = current.data;  //
                            break;
                        }
                        current = current.next;
                    }
                    System.out.print("Enter movie barcode: ");
                    String rentBarcode = scanner.nextLine();
                    Movie movieToRent = movies.searchBarcode(rentBarcode);
                    if (movieToRent == null) {
                        System.out.println("Movie not found!");
                        break;
                    }

                    boolean isRented = false; 
                    for (int i = 0; i < rentalCount; i++) {
                        if (rentals[i] != null && rentals[i].getMovie().barcode.equals(movieToRent.barcode)) { // check if the rental is not null and if movie is available to rent
                            isRented = true;
                            break;
                        }
                    }

                    if (isRented) {
                        System.out.println("This movie has already been rented. Try a different movie."); // if the movie has been rented
                        break;
                    }

                    int movieCount = 0;
                    for (RentalList rental : rentals) {
                        if (rental != null && rental.getCustomer().getPhone().equals(rentPhone)) {
                            movieCount++; // adding 1 to the amount of movies customer has rented
                        }
                    }
                    if (movieCount >= 3) {
                        System.out.println("Customer already has 3 movies rented!"); // if customer has more than 3 movies rented
                        break;
                    }
                    
                    rentals[rentalCount++] = new RentalList(customer, movieToRent);
                    System.out.println("Movie rented!");
                    movies.delete(movieToRent.getBarcode()); // remove the rented movie from movielist 
                    break;

                case 4: 
                    System.out.print("Enter customer phone number: ");
                    String returnPhone = scanner.nextLine();
                    System.out.print("Enter movie barcode: ");
                    String returnBarcode = scanner.nextLine();

                    boolean returned = false;
                    for (int i = 0; i < rentalCount; i++) {
                        if (rentals[i] != null && rentals[i].getCustomer().getPhone().equals(returnPhone) && rentals[i].getMovie().barcode.equals(returnBarcode)) {
                            Movie movieToReturn = rentals[i].getMovie();
                            rentals[i] = null; 
                            System.out.println("Movie returned!");
                            returned = true;
                            movies.insert(movieToReturn); // add the returned movie back into movielist
                            break;
                        }
                    }
                    if (!returned) {
                        System.out.println("Rental record not found!");
                    }
                    break;

                case 5: 
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
                    break;

                case 6: 
                    System.out.println("All Customers:");
                    customers.print();
                    break;

                case 7: 
                    System.out.println("All Movies:");
                    movies.print();
                    break;

                case 8: 
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 8);

        scanner.close();
    }
}

