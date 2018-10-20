import java.util.*;
import java.lang.*;
import java.io.*;

// Please name your class Main
class Main {
    public static void main(String[] args) {
        /* let n.length == 3 and Nr[0] = 2, Nr[1] = 3, Nr[2] = 3 */
        Scanner in = new Scanner(System.in);
        int length = in.nextInt();
        int[] n = new int[length];
        int[] Nr = new int[length];
        for (int i = 0; i < length; i++){
            Nr[i] = in.nextInt();
        }
        Counter counter = new Counter();
        counter.printPermutations(n, Nr, 0);
        counter.printCount();
    }
}
 class Counter{
    private long count = 0;

    public Counter ()
    {}

     public  void printPermutations(int[] n, int[] Nr, int idx) {
         if (idx < n[1] ) {  //stop condition for the recursion [base clause]

             this.count++;
             return;
         }
         for (int i = 0; i <= Nr[idx]; i++) {
             n[idx] = i;
             printPermutations(n, Nr, idx+1); //recursive invokation, for next elements
         }
     }

     public void printCount(){
        System.out.println(count);
     }

}