import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws java.lang.Exception {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        BitSet bits = new BitSet(N);
        int correctBits = 0, correctBitsTry;
        System.out.println("Q " + Main.getBitString(bits, N).replace("", " ").trim());
        correctBits = in.nextInt();
        //System.out.flush();
        for (int i=0; i<N && correctBits < N; i++) {
            bits.set(i);
            System.out.println("Q " + Main.getBitString(bits, N).replace("", " ").trim());
            correctBitsTry = in.nextInt();
            if(correctBitsTry <= correctBits){
                bits.clear(i);
            } else {
                correctBits = correctBitsTry;
            }
            //  System.out.flush();
        }
        System.out.println("A " + Main.getBitString(bits, N).replace("", " ").trim());

    }
    public static String getBitString(BitSet b,int N) {
        String bitString = "";
        for (int i = 0; i < N; i++) {
            bitString += (b.get(i) ? "1" : "0");
        }
        return bitString;
    }
}

