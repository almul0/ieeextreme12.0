import java.util.*;
import java.lang.*;
import java.io.*;

// Please name your class Main
class Main {
    public static void main (String[] args) throws java.lang.Exception {
        Scanner in = new Scanner(System.in);

        int typeQuery;
        int positionQuery;
        long upperBound = 0;
        long lowerBound = 0;

        String line;
        String nameQuery;
        String[] parseN = new String[4];
        String[] parseQ = new String[2];
        String delims = "[ ]+";
        HashMap<Integer, String> cmap = new HashMap<Integer, String>();

        int n = in.nextInt();
        int q = in.nextInt();
        in.nextLine();

        Node[] nodeList = new Node[n];
        Node possibleParent = null;

        for (int i=0; i<n; i++) {
            line = in.nextLine();
            parseN = line.split(delims);
            if (parseN[1].equals("NONE")){
                nodeList[0] = new Node(Long.parseLong(parseN[2]), Long.parseLong(parseN[3]), parseN[0]);
                possibleParent = nodeList[0];
                cmap.put(parseN[0].hashCode(),String.format("%d",i));
            } else if (parseN[1].equals(nodeList[i-1].getName())) {
                possibleParent = nodeList[i-1];
                nodeList[i] = new Node(Long.parseLong(parseN[2]), Long.parseLong(parseN[3]), parseN[0], possibleParent);
                possibleParent.addChild(nodeList[i]);
                cmap.put(parseN[0].hashCode(),String.format("%d",i));
            } else if(parseN[1].equals(nodeList[i-1].getParent().getName())) {
                possibleParent = nodeList[i-1].getParent();
                nodeList[i] = new Node(Long.parseLong(parseN[2]), Long.parseLong(parseN[3]), parseN[0], possibleParent);
                possibleParent.addChild(nodeList[i]);
                cmap.put(parseN[0].hashCode(),String.format("%d",i));
            } else {
                possibleParent = nodeList[0];
                nodeList[i] = new Node(Long.parseLong(parseN[2]), Long.parseLong(parseN[3]), parseN[0], possibleParent);
                cmap.put(parseN[0].hashCode(),String.format("%d",i));
                possibleParent.addChild(nodeList[i]);
            }
        }
        for (int j=0; j<q; j++) {
            line = in.nextLine();
            parseQ = line.split(delims);
            typeQuery = Integer.parseInt(parseQ[1]);
            positionQuery = Integer.parseInt(cmap.get(parseQ[0].hashCode()));
            switch (typeQuery){
                case 1:
                    if ( nodeList[positionQuery].getDivisionLower() != 0 ){
                        upperBound = nodeList[positionQuery].getDivisionUpper();
                        lowerBound = nodeList[positionQuery].getDivisionLower();
                    } else {
                        nodeList[positionQuery].solvemeDaddy(typeQuery);
                        upperBound = nodeList[positionQuery].getDivisionUpper();
                        lowerBound = nodeList[positionQuery].getDivisionLower();
                    }
                    break;
                case 2:
                    if ( nodeList[positionQuery].getAllLower() != 0 ){
                        upperBound = nodeList[positionQuery].getAllUpper();
                        lowerBound = nodeList[positionQuery].getAllLower();
                    } else {
                        nodeList[positionQuery].solvemeDaddy(typeQuery);
                        upperBound = nodeList[positionQuery].getAllUpper();
                        lowerBound = nodeList[positionQuery].getAllLower();
                    }
                    break;
            }
            System.out.println(String.format("%d %d", lowerBound, upperBound));
        }

    }
}

class Node {
    private List<Node> children = new ArrayList<Node>();
    private Node parent = null;
    private long[] division = new long[2];
    private long[] all = new long[2];
    private String name= "";
    private long difference = 0;

    public Node(long division, long all, String name) {
        this.division[0] = division;
        this.division[1] = division;
        this.all[0] = all;
        this.all[1] = all;
        this.name = name;
        if ( all != 0 && division != 0 ) {
            this.difference = all - division;
        }
    }

    public Node(long division, long all, String name, Node parent) {
        this.division[0] = division;
        this.division[1] = division;
        this.all[0] = all;
        this.all[1] = all;
        this.name = name;
        this.parent = parent;
        if ( all != 0 && division != 0 ) {
            this.difference = all - division;
        }
    }

    public List<Node> getChildren() {
        return children;
    }

    public long getDivisionLower() {
        return this.division[0];
    }
    public long getDivisionUpper() {
        return this.division[1];
    }

    public long getAllLower() {
        return this.all[0];
    }

    public long getAllUpper() {
        return this.all[1];
    }

    public String getName() {
        return this.name;
    }

    public Node getParent() {
        return this.parent;
    }

    public long getDifference() {
        return this.difference;
    }

//    public void setDivision(long division) {
//         this.division = division;
//    }
//
//    public void setAll(long all) {
//        this.all = all;
//    }
//
//    public void  setName(String name) {
//        this.name = name;
//    }

    public void calcDifference() {
        if ( all[1] != 0 && division[1] != 0 ) {
            this.difference = all[1] - division[1];
        }
    }

    public boolean isRoot() {
        return (this.parent == null);
    }

    public boolean isLeaf() {
        return this.children.size() == 0;
    }

    public void removeParent() {
        this.parent = null;
    }

    public void solvemeDaddy(int type) {
//        if (this.children.size() == 0){
//            if (this.division[1] == 0 ){
//                if (this.all[1] == 0){
//                    this.all[1] = 1;
//                    this.division[1] = 1;
//                } else{
//                    this.division[1] = this.all[1];
//                }
//            } else{
//                this.all[1] = this.division[1];
//            }
//        }
        if (parent.getDifference() != 0) {
            switch (type) {
                case 1:
                    this.division = parent.childDivision(this.name);

                    break;
                case 2:
                    this.all = parent.childAll(this.name);
                    break;
            }
        } else{
            parent.solvemeDaddy(type);
            switch (type) {
                case 1:
                    this.division = parent.childDivision(this.name);
                    break;
                case 2:
                    this.all = parent.childAll(this.name);
                    break;
            }
        }
        this.calcDifference();
    }

    public long[] childAll(String childName) {
        long childAll[] = new long[2];
        childAll[0] = this.difference;
        childAll[1] = this.difference;
        for  (Node node:children){
            if (node.getName() != childName){
                if (node.isLeaf() && node.getAllLower() == 0){
                    childAll[0] = childAll[0] - 1;
                } else {
                    childAll[0] = childAll[0] - node.getAllLower();
                }
                childAll[1] = childAll[1] - node.getAllUpper();
            }
        }
        return childAll;
    }

    public long[] childDivision(String childName) {
        long childDivision[] = new long[2];
        childDivision[0] = this.difference;
        childDivision[1] = this.difference;
        for  (Node node:children){
            if (node.getName() != childName){
                if (node.isLeaf() && node.getAllLower() == 0){
                    childDivision[0] = childDivision[0] - 1;
                } else if (node.isLeaf() && node.getDivisionLower() == 0){
                    childDivision[0] = childDivision[0] - node.getAllLower();
                } else {
                        childDivision[0] = childDivision[0] - node.getDivisionLower();
                }

                childDivision[1] = childDivision[1] - node.getDivisionUpper();
            }
        }
        return childDivision;
    }
    public void addChild(Node child){
     this.children.add(child);
    }
}