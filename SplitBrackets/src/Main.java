import java.util.*;
import java.lang.*;

class Main {
    public static void main(String[] args) throws java.lang.Exception {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        char[] chars = line.toCharArray();
        if (chars.length % 4 == 0) {
            Bracket s1 = new Bracket(1);
            Bracket s2 = new Bracket(2);
            char[] commonOut = new char[chars.length - 1];
            for (int i = 0; i < chars.length; i++) {
                switch ((int) chars[i]) {
                    case 40:


                        break;

                    case 41:


                        break;

                    case 91:


                        break;

                    case 93:


                        break;
                }
            }
        }




    }
}
class Bracket{
    private int depthBck = 0;
    private int depthSqr = 0;
    private int length = 0;
    private String estado = "REPOSO";
    private int id = 0;


    public Bracket (int id){
        this.id = id;
        if (id == 1){
            estado = "ACTIVO"
        }
    }

    System.out.println("impossible");
                System.exit(0);

}