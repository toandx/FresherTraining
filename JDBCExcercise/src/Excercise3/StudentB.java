package Excercise3;

public class StudentB extends Student{
    public StudentB(String ID, String name, String address, int priority) {
        super(ID, name, address, priority);
    }

    public String toString()
    {
        return(String.format("Student Major B(%s, Learn Literature, History, Geography)",super.toString()));
    }
}
