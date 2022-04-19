package Excercise1;
import java.sql.*;
import java.util.ArrayList;

public class Manager {
    private static String DB_URL = "jdbc:mysql://localhost:3306/mydb";
    private static String USER_NAME = "root";
    private static String PASSWORD = "";
    private int lastID;
    private Connection con;
    private Statement st;
    private String objType;
    private ArrayList<Officer> listOfficer;
    private ResultSet resultSet;
    public void init() {
        try
        {
            con=DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD);
            st=con.createStatement();
            st.execute("drop table if exists Officer");
            st.execute("create table Officer(id INT, name VARCHAR(100), age INT, gender enum('Male','Female'),address VARCHAR(100),class enum('Engineer','Staff','Worker'))");
            st.execute("drop table if exists Engineer");
            st.execute("create table Engineer(id INT,specialized VARCHAR(100))");
            st.execute("drop table if exists Staff");
            st.execute("create table Staff(id INT,job VARCHAR(100))");
            st.execute("drop table if exists Worker");
            st.execute("create table Worker(id INT,level INT)");
        } catch (Exception e)
        {
            System.out.println(e.toString());
        }
        lastID=0;
    }
    public Manager() {
        init();
        this.listOfficer = new ArrayList<Officer>();
    }
    public void addEngineer(Engineer engineer) throws SQLException
    {
        st.execute(String.format("INSERT INTO Engineer(id,specialized) VALUES(%d,'%s')",
                lastID,engineer.getSpecialized()));
    }
    public void addStaff(Staff staff) throws SQLException
    {
        st.execute(String.format("INSERT INTO Staff(id,job) VALUES(%d,'%s')",
                lastID,staff.getJob()));
    }
    public void addWorker(Worker worker) throws SQLException
    {
        st.execute(String.format("INSERT INTO Worker(id,level) VALUES(%d,%d)",
                lastID,worker.getLevel()));
    }

    public void addOfficer(Officer officer) {
        try
        {
            lastID++;
            if (officer instanceof Engineer) {
                objType = "Engineer";
                addEngineer((Engineer) officer);
            }
            else if (officer instanceof Staff) {
                objType="Staff";
                addStaff((Staff) officer);
            }
            else if (officer instanceof Worker) {
                objType="Worker";
                addWorker((Worker) officer);
            }
            st.execute(String.format("INSERT INTO Officer(id,name,age,gender,address,class) VALUES(%d,'%s',%d,'%s','%s','%s')",
                    lastID,officer.getName(),officer.getAge(),officer.getGender(),officer.getAddress(),objType));
        } catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }

    public ArrayList<Officer> searchOfficerByName(String name) {
        listOfficer.clear();
        try
        {
            resultSet = st.executeQuery("SELECT Officer.name,Officer.age,Officer.gender,officer.address,worker.level FROM Officer " +
                    "INNER JOIN Worker ON Officer.id=Worker.id WHERE Officer.name='"+name+"'");
            while(resultSet.next())
            {
                listOfficer.add(new Worker(resultSet.getString(1),resultSet.getInt(2),resultSet.getString(3),
                        resultSet.getString(4),resultSet.getInt(5)));
            }
            resultSet = st.executeQuery("SELECT Officer.name,Officer.age,Officer.gender,officer.address,Engineer.specialized FROM Officer " +
                    "INNER JOIN Engineer ON Officer.id=Engineer.id WHERE Officer.name='"+name+"'");
            while(resultSet.next())
            {
                listOfficer.add(new Engineer(resultSet.getString(1),resultSet.getInt(2),resultSet.getString(3),
                        resultSet.getString(4),resultSet.getString(5)));
            }
            resultSet = st.executeQuery("SELECT Officer.name,Officer.age,Officer.gender,officer.address,Staff.job FROM Officer " +
                    "INNER JOIN Staff ON Officer.id=Staff.id WHERE Officer.name='"+name+"'");
            while(resultSet.next())
            {
                listOfficer.add(new Staff(resultSet.getString(1),resultSet.getInt(2),resultSet.getString(3),
                        resultSet.getString(4),resultSet.getString(5)));
            }
            listOfficer.forEach(officer -> System.out.println(officer.toString()));
        } catch(Exception e)
        {
            System.out.println(e.toString());
        }
        return listOfficer;
    }

    public void showListOfficer() {
        listOfficer.clear();
        try
        {
            resultSet = st.executeQuery("SELECT Officer.name,Officer.age,Officer.gender,officer.address,worker.level FROM Officer " +
                    "INNER JOIN Worker ON Officer.id=Worker.id");
            while(resultSet.next())
            {
                listOfficer.add(new Worker(resultSet.getString(1),resultSet.getInt(2),resultSet.getString(3),
                        resultSet.getString(4),resultSet.getInt(5)));
            }
            resultSet = st.executeQuery("SELECT Officer.name,Officer.age,Officer.gender,officer.address,Engineer.specialized FROM Officer " +
                    "INNER JOIN Engineer ON Officer.id=Engineer.id");
            while(resultSet.next())
            {
                listOfficer.add(new Engineer(resultSet.getString(1),resultSet.getInt(2),resultSet.getString(3),
                        resultSet.getString(4),resultSet.getString(5)));
            }
            resultSet = st.executeQuery("SELECT Officer.name,Officer.age,Officer.gender,officer.address,Staff.job FROM Officer " +
                    "INNER JOIN Staff ON Officer.id=Staff.id");
            while(resultSet.next())
            {
                listOfficer.add(new Staff(resultSet.getString(1),resultSet.getInt(2),resultSet.getString(3),
                        resultSet.getString(4),resultSet.getString(5)));
            }
            listOfficer.forEach(officer -> System.out.println(officer.toString()));
        } catch(Exception e)
        {
            System.out.println(e.toString());
        }
    }
}
