package Excercise1;

public class Worker extends Officer{
    private int level;

    public Worker(String name, int age, String gender, String address, int level) {
        super(name, age, gender, address);
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    public String toString()
    {
        return(String.format("Worker(%s, level %d)", super.toString(),level));
    }
}
