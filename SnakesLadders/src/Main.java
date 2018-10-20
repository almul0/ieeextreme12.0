import java.util.*;
import java.lang.*;

class Main {
    public static void main(String[] args) throws java.lang.Exception {
        Scanner in = new Scanner(System.in);

        long tablero = in.nextLong();
        long tableroQuad = tablero*tablero;
        HashMap<Long, Long> tmap = new HashMap<Long, Long>();

        int playerNumber = in.nextInt();
        Player[] players = new Player[playerNumber];
        for (int h = 0; h < playerNumber; h++){
            players[h] = new Player(tableroQuad);
        }

        int snakeNumber = in.nextInt();

        int[] start, end;

        for (int i = 0; i < snakeNumber; i++){
            start = new int[2];
            start[0] = in.nextInt();
            start[1] = in.nextInt();

            end = new int[2];
            end[0] = in.nextInt();
            end[1] = in.nextInt();
            tmap.put(toLinear(start, tablero),toLinear(end, tablero));
        }

        int ladderNumber = in.nextInt();

        for (int j = 0; j < ladderNumber; j++){
            start = new int[2];
            start[0] = in.nextInt();
            start[1] = in.nextInt();

            end = new int[2];
            end[0] = in.nextInt();
            end[1] = in.nextInt();
            tmap.put(toLinear(start, tablero),toLinear(end, tablero));
        }

        int rolls = in.nextInt();
        long position;
        long currentPosition;
        long y;
        int player;
        int a,b;
        for (int k = 0; k < rolls; k++){
            a = in.nextInt();
            b = in.nextInt();
            player = k%playerNumber;
            if (!players[player].isWin()) {
                players[k % playerNumber].avanzar(a + b);
                y = players[player].getY();
                if(!players[player].isWin()){
                    position = checkCell(tmap, y);
                    currentPosition = y;
                    if ( position != currentPosition) {
                        y = position;
                        players[player].setY(y);
                    }
                }
            }
        }
        for (int l = 0; l < playerNumber; l++){
            if(players[l].isWin()){
                System.out.println(String.format("%d winner", l+1));
            }
            else {
                System.out.println(String.format("%d %d %d", l+1, toPoint(players[l].getY(), tablero)[0],toPoint(players[l].getY(), tablero)[1]));
            }


        }


    }
    public static long checkCell (HashMap<Long, Long> thing, long y){
        long newPosition = y;
        if(thing.containsKey(newPosition)){
                newPosition = thing.get(newPosition);
                return newPosition;
        }
        return newPosition;

    }
    public static long toLinear (int[] point, long tablero ){
        long linear;
        if (point[1] % 2 == 1){
            linear = point[0] + (point[1]-1)*tablero;
        } else {
            linear = tablero - point[0] + 1 + (point[1]-1)*tablero;
        }
        return linear;
    }
    public static long[] toPoint (long linear, long tablero){
        long[] point = new long [2];
        long modulo = linear % tablero;
        point[1] = linear / tablero + 1;
        if ( point[1] % 2 == 0 ) {
            point[0] = tablero - modulo + 1;
        } else {
            point[0] = modulo;
        }


        return point;
    }
}
class Player{
    private long y = 0;
    private boolean win = false;
    private long tablero;

    public Player (long tableroQuad){
        tablero = tableroQuad;
    }

    public void avanzar (int dados){
        y=y+dados;
        win = (y >= tablero);
    }


    public boolean isWin() {
        return win;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }
}
