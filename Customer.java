public class Customer {
    String phone;
    String firstName;
    String lastName;

    public Customer(String phone, String firstName, String lastName) {
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Customer: " + firstName + " " + lastName + ", Phone Number: " + phone;
    }
}

