// Don't place your source in a package

import java.util.*;
import java.lang.*;
import java.io.*;

// Please name your class Main
class Main {
    public static void main (String[] args) throws java.lang.Exception {
        char[]  prefix;
        String infix_line, prefix_line;
        Scanner in = new Scanner(System.in);


        do {
            BinaryTree bt = new BinaryTree();

            infix_line = in.nextLine();
            prefix_line = in.nextLine();

            prefix = prefix_line.toCharArray();


            for (int i=0; i<prefix.length; i++) {
              bt.add(infix_line.indexOf(prefix[i]));
            }

            bt.traverseLevelOrder(infix_line);

        } while((in.hasNextLine()));
    }
}

class Node {
    int value;
    int level;
    Node left;
    Node right;

    Node(int value, int level) {
        this.value = value;
        this.level = level;
        right = null;
        left = null;
    }


}

class BinaryTree {

    Node root;

    private Node addRecursive(Node current, int value, int level) {
        if (current == null) {
            return new Node(value,level);
        }

        if (value < current.value) {
            current.left = addRecursive(current.left, value, level+1);
        } else if (value > current.value) {
            current.right = addRecursive(current.right, value,level+1);
        } else {
            // value already exists
            return current;
        }

        return current;
    }


    public void add(int value) {
        root = addRecursive(root, value, 0);
    }

    public void traverseLevelOrder(String infix) {
        if (root == null) {
            return;
        }

        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);
        int current_level = 0;
        int offset = 0;

        while (!nodes.isEmpty()) {

            Node node = nodes.remove();
            if (current_level != node.level) {
                System.out.println();
                current_level = node.level;
                offset = 0;
            }

            System.out.print(new String(new char[node.value - offset]).replace("\0", " ")
                    + infix.charAt(node.value));
            offset = node.value+1;

            if (node.left != null) {
                nodes.add(node.left);
            }

            if (node.right!= null) {
                nodes.add(node.right);
            }
        }
    }
}