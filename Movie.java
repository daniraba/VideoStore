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
}
