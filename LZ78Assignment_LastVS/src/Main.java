// Multimedia Assignment 1
// LZ78 Compression and Decompression
// Alaa Farouk 20170058
// Lamya Raed  20170399
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String UserInput, Code;
        int index;
        LZ78 c =  new LZ78();
        int choice;
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome, If you want to Compress Enter 1");
        System.out.println("\tIf you want to Compress Enter 1");
        System.out.println("\tIf you want to Decompress Enter 2");
        choice = input.nextInt();
        switch (choice)
        {
            case 1:
                System.out.println("\nWrite the Sentence to Compress");
                Scanner n = new Scanner(System.in);
                UserInput = n.nextLine();
                System.out.println("\nYour Tags Are :");
                c.Compression(UserInput);
                break;
            case 2:
            	c.Decompression();
                break;

        }
    }
}
