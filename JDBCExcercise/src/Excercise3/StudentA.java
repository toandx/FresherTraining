package Excercise3;

public class StudentA extends Student{
    public StudentA(String ID, String name, String address, int priority) {
        super(ID, name, address, priority);
    }

    public String toString()
    {
        return(String.format("Student Major A(%s, Learn math, physics, chemistry)",super.toString()));
    }
}
