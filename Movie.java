public class Movie {
    String barcode;
    String movieName;

    public Movie(String barcode, String movieName) {
        this.barcode = barcode;
        this.movieName = movieName;
    }
    public String getTitle() {
        return movieName;
    }

    public String getBarcode() {
        return barcode;
    }

    @Override
    public String toString() {
        return "Movie: " + movieName + ", Barcode: " + barcode;
    }

}
