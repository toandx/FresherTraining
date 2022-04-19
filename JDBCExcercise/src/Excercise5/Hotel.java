package Excercise5;


import Excercise2.Book;
import Excercise2.Document;
import Excercise2.Journal;
import Excercise2.Newspaper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private static String DB_URL = "jdbc:mysql://localhost:3306/mydb";
    private static String USER_NAME = "root";
    private static String PASSWORD = "";
    private Connection con;
    private Statement st;
    private ResultSet resultSet;
    private People res;
    public void init() {
        try
        {
            con= DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD);
            st=con.createStatement();
            st.execute("drop table if exists People");
            st.execute("create table People(name VARCHAR(20), age INT, id VARCHAR(20), numRentalDay INT, room enum('A','B','C'), PRIMARY KEY (id))");
        } catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }
    public Hotel()
    {
        init();
    }

    public void addCustomer(People people)
    {
        String roomType;
        if (people.getRoom() instanceof RoomA)
            roomType="A";
        else if (people.getRoom() instanceof RoomB)
            roomType="B";
        else
            roomType="C";
        try
        {
            st.execute(String.format("INSERT INTO People(name,age,id,numRentalDay,room) VALUES('%s',%d,'%s',%d,'%s')",
                    people.getName(),people.getAge(),people.getID(),people.getNumRentalDay(),roomType));
        } catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }
    public void deleteCustomer(String ID)
    {
        try
        {
            st.execute(String.format("DELETE FROM People WHERE id='%s'", ID));
        } catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }
    public void exportBill(String ID)
    {
        Room room;
        try
        {
            resultSet = st.executeQuery(String.format("SELECT name,age,id,numRentalDay, room from People WHERE id='%s'",ID));
            while(resultSet.next())
            {
                String roomType=resultSet.getString(5);
                if (roomType.equals("A"))
                    room=new RoomA();
                else if (roomType.equals("B"))
                    room=new RoomB();
                else
                    room=new RoomC();
                res=new People(resultSet.getString(1),resultSet.getInt(2),resultSet.getString(3),
                        resultSet.getInt(4),room);
                res.exportBill();
                return;
            }
        } catch (Exception e)
        {
            System.out.println(e.toString());
        }
        System.out.println("Can't found customer with ID="+ID);
    }
}
