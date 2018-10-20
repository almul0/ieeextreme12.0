import java.util.*;
import java.lang.*;
import java.io.*;
class Main {
    public static void main (String[] args) throws java.lang.Exception {
        Scanner in = new Scanner(System.in);

        String[] parseN = new String[3];
        String[] parseQ = new String[2];
        ArrayList<String> material1=new ArrayList<String>();
        ArrayList<String> material2=new ArrayList<String>();
        ArrayList<Integer> rate=new ArrayList<Integer>();
        String delims = "[ ]+";
        String line;

        int n = in.nextInt();
        in.nextLine();
        for (int i=0; i<n; i++) {
            line = in.nextLine();
            parseN = line.split(delims);
            material1.add(parseN[0]);
            material2.add(parseN[1]);
            rate.add(Integer.parseInt(parseN[2]));
        }

        int q = in.nextInt();
        in.nextLine();
        for (int j=0; j<q; j++) {
            line = in.nextLine();
            parseQ = line.split(delims);
        }

    }

}
class Material{

}