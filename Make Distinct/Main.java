// Don't place your source in a package
import java.util.*;
import java.lang.*;
import java.io.*;

// Please name your class Main
class Main {
    public static void main (String[] args) throws java.lang.Exception {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        HashMap<Integer,Integer> hmap = new HashMap<Integer, Integer>();
        HashSet<Integer> hset = new HashSet<>();
        int ops = 0;
        int next;
        for (int i=0; i<N; i++) {
            next = in.nextInt();
            if (!hset.add(next)) {
                if ( hmap.get(next) != null ) {
                    hmap.put(next, hmap.get(next) + 1);
                } else {
                    hmap.put(next,1);
                }
            }
        }
        for (Map.Entry<Integer, Integer> entry : hmap.entrySet()) {
            Integer num = entry.getKey();
            Integer reps = entry.getValue();
            for (int n=1; n<=reps; n++) {
                int cont = 1;
                int tryhard = num + cont;

                while(!hset.add(tryhard)) {
                    if (cont < 0) {
                        cont = (cont*-1)+1;
                    } else {
                        cont *= -1;
                    }

                    tryhard = num + cont;
                }

                if ( cont < 0 ){
                    ops -= cont;
                } else {
                    ops += cont;
                }
            }
        }
        System.out.println(ops-1);
    }
}