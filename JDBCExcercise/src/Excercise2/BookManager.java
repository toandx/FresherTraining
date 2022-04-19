package Excercise2;

import Excercise1.Engineer;
import Excercise1.Officer;
import Excercise1.Staff;
import Excercise1.Worker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookManager {
    private static String DB_URL = "jdbc:mysql://localhost:3306/mydb";
    private static String USER_NAME = "root";
    private static String PASSWORD = "";
    private Connection con;
    private Statement st;
    private ArrayList<Document> listDoc;
    private ResultSet resultSet;
    public void init() {
        try
        {
            con=DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD);
            st=con.createStatement();
            st.execute("drop table if exists Doc");
            st.execute("create table Doc(id VARCHAR(20), publisher VARCHAR(100), amount INT, PRIMARY KEY (id))");
            st.execute("drop table if exists Book");
            st.execute("create table Book(id VARCHAR(20),author VARCHAR(100), numPage INT, PRIMARY KEY(id))");
            st.execute("drop table if exists Journal");
            st.execute("create table Journal(id VARCHAR(20),issueNumber INT, releaseMonth INT, PRIMARY KEY(id))");
            st.execute("drop table if exists Newspaper");
            st.execute("create table Newspaper(id VARCHAR(20),releaseDate INT, PRIMARY KEY(id))");
        } catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }
    public BookManager() {
        init();
        this.listDoc = new ArrayList<Document>();
    }
    public void addBook(Book book) throws SQLException
    {
        st.execute(String.format("INSERT INTO Book(id,author,numPage) VALUES('%s','%s',%d)",
                book.getId(),book.getAuthor(),book.getNumPage()));
    }
    public void addJournal(Journal journal) throws SQLException
    {
        st.execute(String.format("INSERT INTO Journal(id,issueNumber,releaseMonth) VALUES('%s',%d,%d)",
                journal.getId(),journal.getIssueNumber(),journal.getReleaseMonth()));
    }
    public void addNewspaper(Newspaper newspaper) throws SQLException
    {
        st.execute(String.format("INSERT INTO Newspaper(id,releaseDate) VALUES('%s',%d)",
                newspaper.getId(),newspaper.getReleaseDate()));
    }
    public void addDoc(Document doc) {
        try
        {
            st.execute(String.format("INSERT INTO Doc(id,publisher,amount) VALUES('%s','%s',%d)",
                    doc.getId(),doc.getPublisher(),doc.getAmount()));
            if (doc instanceof Book) {
                addBook((Book) doc);
            } else if (doc instanceof Journal) {
                addJournal((Journal) doc);
            } else if (doc instanceof Newspaper) {
                addNewspaper((Newspaper) doc);
            }
        } catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }
    public void deleteDoc(String docID) {
        try
        {
            st.execute(String.format("DELETE FROM Doc WHERE id='%s'",docID));
            st.execute(String.format("DELETE FROM Book WHERE id='%s'",docID));
            st.execute(String.format("DELETE FROM Journal WHERE id='%s'",docID));
            st.execute(String.format("DELETE FROM Newspaper WHERE id='%s'",docID));
        } catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }
    public List<Document> searchBookByID(String docID) {
        listDoc.clear();
        try
        {
            resultSet = st.executeQuery("SELECT Doc.id,Doc.publisher,Doc.amount,Book.author,Book.numPage FROM Book " +
                    "INNER JOIN Doc ON Book.id=Doc.id WHERE Book.id='"+docID+"'");
            while(resultSet.next())
            {
                listDoc.add(new Book(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3),
                        resultSet.getString(4),resultSet.getInt(5)));
            }
        } catch(Exception e)
        {
            System.out.println(e.toString());
        }
        return(listDoc);
    }
    public List<Document> searchJournalByID(String docID) {
        listDoc.clear();
        try
        {
            resultSet = st.executeQuery("SELECT Doc.id,Doc.publisher,Doc.amount,Journal.issueNumber,Journal.releaseMonth FROM Journal " +
                    "INNER JOIN Doc ON Journal.id=Doc.id WHERE Journal.id='"+docID+"'");
            while(resultSet.next())
            {
                listDoc.add(new Journal(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3),
                        resultSet.getInt(4),resultSet.getInt(5)));
            }
        } catch(Exception e)
        {
            System.out.println(e.toString());
        }
        return(listDoc);
    }
    public List<Document> searchNewspaperByID(String docID) {
        listDoc.clear();
        try
        {
            resultSet = st.executeQuery("SELECT Doc.id,Doc.publisher,Doc.amount,Newspaper.releaseDate FROM Newspaper " +
                    "INNER JOIN Doc ON Newspaper.id=Doc.id WHERE Newspaper.id='"+docID+"'");
            while(resultSet.next())
            {
                listDoc.add(new Newspaper(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3),
                        resultSet.getInt(4)));
            }
        } catch(Exception e)
        {
            System.out.println(e.toString());
        }
        return(listDoc);
    }

    public void showListDocument() {
        listDoc.clear();
        try
        {
            resultSet = st.executeQuery("SELECT Doc.id,Doc.publisher,Doc.amount,Book.author,Book.numPage FROM Book " +
                    "INNER JOIN Doc ON Book.id=Doc.id");
            while(resultSet.next())
            {
                listDoc.add(new Book(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3),
                        resultSet.getString(4),resultSet.getInt(5)));
            }
            resultSet = st.executeQuery("SELECT Doc.id,Doc.publisher,Doc.amount,Journal.issueNumber,Journal.releaseMonth FROM Journal " +
                    "INNER JOIN Doc ON Journal.id=Doc.id");
            while(resultSet.next())
            {
                listDoc.add(new Journal(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3),
                        resultSet.getInt(4),resultSet.getInt(5)));
            }
            resultSet = st.executeQuery("SELECT Doc.id,Doc.publisher,Doc.amount,Newspaper.releaseDate FROM Newspaper " +
                    "INNER JOIN Doc ON Newspaper.id=Doc.id");
            while(resultSet.next())
            {
                listDoc.add(new Newspaper(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3),
                        resultSet.getInt(4)));
            }
        } catch(Exception e)
        {
            System.out.println(e.toString());
        }
        listDoc.forEach(doc -> System.out.println(doc.toString()));
    }
}
