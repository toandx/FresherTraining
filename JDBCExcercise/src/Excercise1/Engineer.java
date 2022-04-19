package Excercise1;

public class Engineer extends Officer{
    private String specialized;
    public Engineer(String name, int age, String gender, String address, String specialized) {
        super(name, age, gender, address);
        this.specialized = specialized;
    }

    public String getSpecialized() {
        return specialized;
    }

    public void setSpecialized(String specialized) {
        this.specialized=specialized;
    }
    public String toString()
    {
        return(String.format("Engineer(%s, specialized %s)", super.toString(),specialized));
    }

}
