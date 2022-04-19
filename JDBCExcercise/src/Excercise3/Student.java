package Excercise3;

public class Student {
    private String ID,name,address;
    private int priority;

    public Student(String ID, String name, String address, int priority) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.priority = priority;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String toString()
    {
        return(String.format("ID %s, Name %s, Address %s, Priority %d",ID,name,address,priority));
    }
}
