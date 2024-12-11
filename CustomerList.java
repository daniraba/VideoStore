public class CustomerList {
    public Node head;

    public CustomerList() {
        head = null;
    }

    public static class Node {      
        Customer data; 
        Node next;

        Node(Customer data) {
            this.data = data;
            this.next = null;
        }
    }

    public void insert(Customer customer) {  //runtime: O(n)
        if (customer == null) {
            System.out.println("Cannot insert null customer");
            return;                                                         
        }
        Node newNode = new Node(customer);

        if (head == null) {
            head = newNode;
            return;
        }

        if (customer.getLastName().compareTo(head.data.getLastName()) < 0) {
            newNode.next = head;
            head = newNode;
            return;
        }

        Node current = head;
        while (current.next != null) {
            if (customer.getLastName().compareTo(current.next.data.getLastName()) < 0) {
                newNode.next = current.next;
                current.next = newNode;
                return;
            }
            current = current.next;
        }
        current.next = newNode;
    }

    public void print() { //runtime: O(n)
        Node current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    public boolean contains(String phoneNumber) { //runtime: O(n)
        if(head == null) return false;
        Node current = head;
        while (current != null) {
            if (current.data.getPhone().equals(phoneNumber)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public int count() { //runtime: O(n)
        int total = 0;
        Node current = head;
        while (current != null) {
            total++;
            current = current.next;
        }
        return total;
    }

    public boolean delete(String phone) { //runtime: O(n)
        if (head == null) return false;

        if (head.data.getPhone().equals(phone)) {
            head = head.next;
            return true;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.data.getPhone().equals(phone)) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }
        return false; 
    }
}

