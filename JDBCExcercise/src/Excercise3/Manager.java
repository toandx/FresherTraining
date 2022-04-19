package Excercise3;

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
import java.util.stream.Collectors;

public class Manager {
    private static String DB_URL = "jdbc:mysql://localhost:3306/mydb";
    private static String USER_NAME = "root";
    private static String PASSWORD = "";
    private Connection con;
    private Statement st;
    private List<Student> listStudent;
    private String major;
    private ResultSet resultSet;
    public void init() {
        try
        {
            con= DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD);
            st=con.createStatement();
            st.execute("drop table if exists Student");
            st.execute("create table Student(id VARCHAR(20), name VARCHAR(100), address VARCHAR(100), priority INT, major enum('A','B','C'), PRIMARY KEY (id))");
        } catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }
    public Manager()
    {
        init();
        listStudent=new ArrayList<Student>();
    }
    public void addStudent(Student student)
    {
        if (student instanceof StudentA)
            major="A";
        else if (student instanceof StudentB)
            major="B";
        else if (student instanceof StudentC)
            major="C";
        try
        {
            st.execute(String.format("INSERT INTO Student(id, name, address, priority, major) VALUES('%s','%s','%s',%d,'%s')",
                    student.getID(),student.getName(),student.getAddress(),student.getPriority(),major));
        } catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }
    public void showListStudent()
    {
        listStudent.clear();
        try
        {
            resultSet = st.executeQuery("SELECT id,name,address,priority,major FROM Student");
            while(resultSet.next())
            {
                major=resultSet.getString(5);
                if (major.equals("A"))
                    listStudent.add(new StudentA(resultSet.getString(1),resultSet.getString(2),
                            resultSet.getString(3),resultSet.getInt(4)));
                else if (major.equals("B"))
                    listStudent.add(new StudentB(resultSet.getString(1),resultSet.getString(2),
                            resultSet.getString(3),resultSet.getInt(4)));
                else
                    listStudent.add(new StudentC(resultSet.getString(1),resultSet.getString(2),
                            resultSet.getString(3),resultSet.getInt(4)));
            }
        } catch(Exception e)
        {
            System.out.println(e.toString());
        }
        listStudent.forEach(student -> System.out.println(student.toString()));
    }
    public Student searchStudentByID(String ID)
    {
        try
        {
            resultSet = st.executeQuery("SELECT id,name,address,priority,major FROM Student WHERE id='"+ID+"'");
            while(resultSet.next())
            {
                major=resultSet.getString(5);
                if (major.equals("A"))
                    return(new StudentA(resultSet.getString(1),resultSet.getString(2),
                            resultSet.getString(3),resultSet.getInt(4)));
                else if (major.equals("B"))
                    return(new StudentB(resultSet.getString(1),resultSet.getString(2),
                            resultSet.getString(3),resultSet.getInt(4)));
                else
                    return(new StudentC(resultSet.getString(1),resultSet.getString(2),
                            resultSet.getString(3),resultSet.getInt(4)));
            }
        } catch(Exception e)
        {
            System.out.println(e.toString());
        }
        return null;
    }

}
