package Excercise4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBCHandle {
    private static String DB_URL = "jdbc:mysql://localhost:3306/mydb";
    private static String USER_NAME = "root";
    private static String PASSWORD = "";
    private Connection connection;
    private Statement statement;
    private static JDBCHandle instance=null;
    private JDBCHandle() throws Exception
    {
        connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        statement = connection.createStatement();
    }
    public static JDBCHandle getInstance() throws Exception
    {
        if (instance==null)
            instance=new JDBCHandle();
        return(instance);
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public void close() throws Exception
    {
        statement.close();
        connection.close();
    }
}
