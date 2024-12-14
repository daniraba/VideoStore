public class RentalList {
    private Customer customer;
    private Movie movie;

    public RentalList(Customer customer, Movie movie) {
        this.customer = customer;
        this.movie = movie;
    }
    public Movie getMovie() {
        return movie;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public String toString() {
        return customer.getFirstName() + " is currently renting this movie. Their phone number is " + customer.getPhone() + ".";
    }    
}