import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EvenOddThreads {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int n = scanner.nextInt();
        scanner.close();

        List<Integer> sharedList = new ArrayList<>();
        Object lock = new Object();

        Thread evenThread = new Thread(new EvenNumbers(sharedList, n, lock));
        Thread oddThread = new Thread(new OddNumbers(sharedList, n, lock));

        evenThread.start();
        oddThread.start();

        try {
            evenThread.join();
            oddThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Combined List: " + sharedList);
    }
}
