package Excercise5;

public class Main {
    public static void main(String args[])
    {
        Hotel hotel=new Hotel();
        hotel.addCustomer(new People("Nguyen Van A",23,"23",2,new RoomA()));
        hotel.addCustomer(new People("Dang Van A",30,"113",3,new RoomB()));
        hotel.addCustomer(new People("To Xuan C",50,"474",10,new RoomC()));
        hotel.deleteCustomer("113");
        hotel.exportBill("23");
        hotel.exportBill("113");
        hotel.exportBill("474");
    }
}
