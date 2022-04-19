package Excercise2;

public class Journal extends Document {
    private int issueNumber;
    private int releaseMonth;

    public Journal(String id, String publisher, int amount, int issueNumber, int releaseMonth) {
        super(id, publisher, amount);
        this.issueNumber = issueNumber;
        this.releaseMonth = releaseMonth;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    public int getReleaseMonth() {
        return releaseMonth;
    }

    public void setReleaseMonth(int releaseMonth) {
        this.releaseMonth = releaseMonth;
    }
    public String toString()
    {
        return(String.format("Journal(%s, Issue Number %d, Release Month %d)", super.toString(),issueNumber,releaseMonth));
    }
}
