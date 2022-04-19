package Excercise3;

public class Main {
    public static void main(String args[])
    {
        Manager manager=new Manager();
        manager.addStudent(new StudentA("123","Dong Toan","Cau Giay",1));
        manager.addStudent(new StudentB("456","Nguyen Trang","Bach Mai",2));
        manager.addStudent(new StudentC("789","Nguyen Quan","Yen Hoa",3));
        manager.showListStudent();
        Student student = manager.searchStudentByID("23");
        if (student != null)
        {
            System.out.println(student.toString());
        }
        else
            System.out.println("Student not found");
    }
}
