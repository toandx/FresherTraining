package Excercise3;

public class StudentC extends Student{
    public StudentC(String ID, String name, String address, int priority) {
        super(ID, name, address, priority);
    }

    public String toString()
    {
        return(String.format("Student Major C(%s, Learn math, physics, chemistry)",super.toString()));
    }
}
