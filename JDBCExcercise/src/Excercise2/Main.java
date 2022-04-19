package Excercise2;


import java.util.List;

public class Main {
    public static void main(String args[])
    {
        BookManager manager=new BookManager();
        manager.addDoc(new Book("B1","Tuoi tre",20,"Nguyen Du",200));
        manager.addDoc(new Book("B2","Giao duc",20,"Nguyen Dinh Duc",200));
        manager.addDoc(new Newspaper("N1","Vnexpress",200,23));
        manager.deleteDoc("B2");
        manager.showListDocument();

        List<Document> documentList= manager.searchBookByID("B1");
        documentList.forEach(doc -> System.out.println(doc.toString()));

    }
}
