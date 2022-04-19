package Excercise4;

import Excercise2.Book;
import Excercise2.Journal;
import Excercise2.Newspaper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Person {
    private String name,job,id;
    private int age;
    private static Boolean dbActive=false;
    private static Connection con;
    private static Statement st;

    public Person(String name, int age, String job, String id) {
        this.name = name;
        this.job = job;
        this.id = id;
        this.age = age;
    }

    public void initDB()
    {
        try
        {
            JDBCHandle jdbcHandle=JDBCHandle.getInstance();
            con=jdbcHandle.getConnection();
            st= jdbcHandle.getStatement();
            st.execute("drop table if exists Person");
            st.execute("create table Person(name VARCHAR(50), age INT, job VARCHAR(50), id VARCHAR(20), familyID INT, PRIMARY KEY (id), " +
                    "FOREIGN KEY (familyID) REFERENCES Family(ID))");
            dbActive=true;
        } catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }
    public Person(int familyID)
    {
        if (!dbActive) initDB();
        InputHandle inputHandle = InputHandle.getInstance();
        System.out.println("Please input person info");
        System.out.print("Please input name:"); this.name = inputHandle.getString();
        System.out.print("Please input age:"); this.age = inputHandle.getInt();
        System.out.print("Please input job:"); this.job = inputHandle.getString();
        System.out.print("Please input id:"); this.id = inputHandle.getString();
        try
        {
            st.execute(String.format("INSERT INTO Person(name,age,job,id,familyID) VALUES('%s',%d,'%s','%s',%d)",
                    name,age,job,id,familyID));
        } catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }

    public void showPersonInfo()
    {
        System.out.println(String.format("  Person name:%s Age:%d Job:%s ID:%s",name,age,job,id));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
