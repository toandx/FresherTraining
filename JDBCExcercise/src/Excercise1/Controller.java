package Excercise1;

import java.util.ArrayList;
import java.sql.Connection;

public class Controller {
    public static void main(String args[])
    {
        Manager manager=new Manager();
        manager.addOfficer(new Worker("Dong Xuan Toan", 23, "Male", "Cau Giay", 6));
        manager.addOfficer(new Engineer("Do Manh Quang", 23, "Male", "Le Duc Tho", "Touchpad driver"));
        manager.addOfficer(new Staff("Dao Manh Quang", 23, "Male", "Ha Dong", "Driver engineer"));
        manager.addOfficer(new Staff("Dong Xuan Toan", 24, "Female", "Ha Dong", "Driver engineer"));
        manager.searchOfficerByName("Dong Xuan Toan");
        manager.showListOfficer();

    }

}

