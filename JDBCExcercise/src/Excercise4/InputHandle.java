package Excercise4;

import java.util.Scanner;

public class InputHandle {
    private static InputHandle instance=null;
    private Scanner scanner;
    private int n;
    private InputHandle()
    {
        scanner=new Scanner(System.in);
    }
    public static InputHandle getInstance()
    {
        if (instance==null)
            instance=new InputHandle();
        return(instance);
    }
    public int getInt()
    {
        n=scanner.nextInt();
        scanner.nextLine();
        return(n);
    }
    public String getString()
    {
        return(scanner.nextLine());
    }
}
