public class RentalList {
    private Customer customer;
    private Movie movie; 
    private String rentalDate;
    private String returnDate;

    public RentalList(Customer customer, Movie movie, String rentalDate, String returnDate) {
        this.customer = customer;
        this.movie = movie;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }

    public Movie getMovie() {
        return movie;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public String toString() {
        return customer.getFirstName() + " rented " + movie.getTitle() + " from " + rentalDate + " to " + returnDate;
    }
}
