// Don't place your source in a package
import java.util.*;
import java.lang.*;
import java.io.*;

// Please name your class Main
class Main {
    public static void main (String[] args) throws java.lang.Exception {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        int S,E;
        int n;
        int next, sumMinusElement;
        int found = 0;
        HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();
        for (int t=0; t<T; t++) {
            S = in.nextInt();
            E = in.nextInt();
            hmap.clear();
            found =0;
            for (n=0;n<E; n++) {
                next = in.nextInt();
                sumMinusElement = S - next;
                if (hmap.get(sumMinusElement) != null) {
                    if ( next < sumMinusElement) {
                        System.out.println(next + " " + sumMinusElement);
                    } else {
                        System.out.println(sumMinusElement + " " + next);
                    }
                    found = 1;
                    break;
                }
                hmap.put(next,next);
            }
            if (found == 0) {
                System.out.println("!OK");
            }
            if (n<E) {
                in.nextLine();
            }

        }

    }
}