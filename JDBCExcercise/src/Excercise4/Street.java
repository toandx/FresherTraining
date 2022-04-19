package Excercise4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Street {
    private List<Family> familyList;
    private int numFamily;
    private static Boolean dbActive=false;
    private static Connection con;
    private static Statement st;
    private static ResultSet resultSet;
    public void initDB()
    {
        try
        {
            JDBCHandle jdbcHandle=JDBCHandle.getInstance();
            con=jdbcHandle.getConnection();
            st= jdbcHandle.getStatement();
            dbActive=true;
        } catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }

    public Street()
    {
        if (!dbActive) initDB();
        this.familyList = new ArrayList<Family>();
        InputHandle inputHandle=InputHandle.getInstance();
        System.out.println("Please input street info");
        System.out.print("Please input num family:");
        numFamily=inputHandle.getInt();
        for(int i=1;i<=numFamily;++i)
            familyList.add(new Family());
    }
    public void showStreetInfo()
    {
        System.out.println(String.format("This street has %d families:",numFamily));
        familyList.clear();
        try
        {
            resultSet = st.executeQuery("SELECT id,numPerson,address FROM Family");
            while(resultSet.next())
            {
                familyList.add(new Family(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3)));
            }
        } catch(Exception e)
        {
            System.out.println(e.toString());
        }
        familyList.forEach(family -> family.showFamilyInfo());
    }

    public List<Family> getFamilyList() {
        return familyList;
    }

    public void setFamilyList(List<Family> familyList) {
        this.familyList = familyList;
    }
}
