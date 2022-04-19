package Excercise2;

public class Book extends Document{
    private String author;
    private int numPage;

    public Book(String id, String publisher, int amount, String author, int numPage) {
        super(id, publisher, amount);
        this.author = author;
        this.numPage = numPage;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getNumPage() {
        return numPage;
    }

    public void setNumPage(int numPage) {
        this.numPage = numPage;
    }
    public String toString()
    {
        return(String.format("Book(%s, Author %s, Numpage %d)", super.toString(),author,numPage));
    }
}
