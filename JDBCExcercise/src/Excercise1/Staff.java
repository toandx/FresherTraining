package Excercise1;

public class Staff extends Officer{
    private String job;
    public Staff(String name, int age, String gender, String address, String job) {
        super(name, age, gender, address);
        this.job=job;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job=job;
    }
    public String toString()
    {
        return(String.format("Staff(%s, job %s)", super.toString(),job));
    }


}
