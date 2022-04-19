package Excercise2;

public class Document {
    protected String id;
    protected String publisher;
    protected int amount;

    public Document(String id, String publisher, int amount) {
        this.id = id;
        this.publisher=publisher;
        this.amount=amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount=amount;
    }

    public String toString()
    {
        return(String.format("ID %s,Publisher %s,Amount %d",id,publisher,amount));
    }

}
