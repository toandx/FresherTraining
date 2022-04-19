package Excercise5;

public class People {
    private String name,ID;
    private int age,numRentalDay;
    private Room room;

    public People(String name, int age,String ID, int numRentalDay, Room room) {
        this.name = name;
        this.ID = ID;
        this.age = age;
        this.numRentalDay = numRentalDay;
        this.room = room;
    }
    public void exportBill()
    {
        int res=numRentalDay*room.getPrice();
        System.out.println(String.format("Customer with ID %s need pay %d$",ID,res));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNumRentalDay() {
        return numRentalDay;
    }

    public void setNumRentalDay(int numRentalDay) {
        this.numRentalDay = numRentalDay;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
