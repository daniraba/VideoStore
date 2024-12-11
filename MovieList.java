public class MovieList {
    Node head;

    public MovieList() {
        head = null;
    }

    public static class Node {
        Movie data;
        Node next;

        Node(Movie data) {
            this.data = data;
            this.next = null;
        }
    }

    public void insert(Movie movie) { //runtime: O(n)
        if (movie == null) {
            System.out.println("Cannot insert null movie");
            return;
        }
        
        Node newNode = new Node(movie);

        if (head == null) {
            head = newNode;
            return;
        }

        if (movie.movieName.compareTo(head.data.movieName) < 0) {
            newNode.next = head;
            head = newNode;
            return;
        }

        Node current = head;
        while (current.next != null) {
            if (movie.movieName.compareTo(current.next.data.movieName) < 0) {
                newNode.next = current.next;
                current.next = newNode;
                return;
            }
            current = current.next;
        }

        current.next = newNode;
    }

    public boolean delete(String barcode) { //runtime: O(n)
        if (head == null) return false;

        if (head.data.barcode.equals(barcode)) {
            head = head.next;
            return true;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.data.barcode.equals(barcode)) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }
        return false; 
    }

    public void print() { //runtime: O(n)
        Node current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    public Movie searchTitle(String title) { //runtime: O(n)
        Node current = head;
        while (current != null) {
            if (current.data.movieName.equalsIgnoreCase(title)) {
                return current.data;
            }
            current = current.next;
        }
        return null; 
    }

    public Movie searchBarcode(String barcode) { //runtime: O(n)
        Node current = head;
        while (current != null) {
            if (current.data.barcode.equals(barcode)) {
                return current.data;
            }
            current = current.next;
        }
        return null; 
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
}
