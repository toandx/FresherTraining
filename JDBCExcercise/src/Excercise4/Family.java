package Excercise4;

import Excercise2.Book;
import Excercise2.Journal;
import Excercise2.Newspaper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Family {
    private List<Person> listPerson;
    private int id,numPerson;
    private String address;
    private static Boolean dbActive=false;
    private static Connection con;
    private static Statement st;
    private static int lastID=0;
    private static ResultSet resultSet;
    public void initDB()
    {
        try
        {
            JDBCHandle jdbcHandle=JDBCHandle.getInstance();
            con=jdbcHandle.getConnection();
            st= jdbcHandle.getStatement();
            st.execute("drop table if exists Person");
            st.execute("drop table if exists Family");
            st.execute("create table Family(id INT, numPerson INT, address VARCHAR(50),PRIMARY KEY (id))");
            dbActive=true;
        } catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }

    public Family(int id, int numPerson, String address) {
        if (!dbActive) initDB();
        this.id = id;
        this.numPerson = numPerson;
        this.address = address;
        this.listPerson = new ArrayList<Person>();
    }

    public Family() // Input family from keyboard
    {
        if (!dbActive) initDB();
        this.listPerson = new ArrayList<Person>();
        InputHandle inputHandle = InputHandle.getInstance();
        System.out.println("Please input family info");
        System.out.print("Please input address:");
        this.address = inputHandle.getString();
        System.out.print("Please input num people:");
        numPerson=inputHandle.getInt();
        lastID++;
        try
        {
            st.execute(String.format("INSERT INTO Family(id,numPerson,address) VALUES(%d,%d,'%s')",
                    lastID,numPerson,address));
        } catch (Exception e)
        {
            System.out.println(e.toString());
        }
        for(int i=1;i<=numPerson;++i)
            this.listPerson.add(new Person(lastID));
    }
    public void showFamilyInfo()
    {
        listPerson.clear();
        try
        {
            resultSet = st.executeQuery("SELECT name,age,job,id FROM Person WHERE familyID='"+id+"'");
            while(resultSet.next())
            {
                listPerson.add(new Person(resultSet.getString(1),resultSet.getInt(2),resultSet.getString(3),
                        resultSet.getString(4)));
            }
        } catch(Exception e)
        {
            System.out.println(e.toString());
        }
        System.out.println(String.format("Family at %s has %d person:",address,numPerson));
        listPerson.forEach(person -> person.showPersonInfo());
    }

    public List<Person> getListPerson() {
        return listPerson;
    }

    public void setListPerson(List<Person> listPerson) {
        this.listPerson = listPerson;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
