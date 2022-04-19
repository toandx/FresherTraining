package Excercise2;

public class Newspaper extends Document{
    private int releaseDate;
    public Newspaper(String id, String publisher, int amount, int releaseDate) {
        super(id, publisher, amount);
        this.releaseDate = releaseDate;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }
    public String toString()
    {
        return(String.format("Newspaper(%s, ReleaseDate %d)", super.toString(),releaseDate));
    }
}
