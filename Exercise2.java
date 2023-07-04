/**
 * CSCI 2110
 * @author - Rahul Kumar
 * @description: This program reads a file as an input that represent a directed, unweighted,
 * acyclic graph and performs topological sorting and display the result
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Exercise2 {
    public static void main(String[] args) throws FileNotFoundException {

        //input file name
        Scanner in = new Scanner(System.in);
        System.out.print("Enter file name: ");
        String fileName = in.nextLine();

        //create a file
        File file = new File(fileName);
        Scanner fileReader = new Scanner(file);
        int size = fileReader.nextInt();

        int[][] adj = new int[size][size];
        int v1 = 0;
        int v2 = 0;

        // read the elements of file and store them in the 2D array
        while (fileReader.hasNext()) {
            StringTokenizer token = new StringTokenizer(fileReader.nextLine(), "\t");
            while (token.hasMoreTokens()) {
                v1 = token.nextToken().charAt(0) - 65;
                v2 = token.nextToken().charAt(0) - 65;
                adj[v1][v2] = 1;
            }
        }

        // storing element with zero predecessor in the queue
        Queue<Integer> sorting = new PriorityQueue<>();
        for (int i = 0; i < adj.length; i++) {
            if (predecessor(i, adj) == 0) {
                sorting.add(i);
            }
        }

        int removed = 0;
        int topNum = 1;

        /*
        1. loop removes first removes the element with zero predecessor and display it with its
        topNum value.
        2. It then decreases the removed element's neighbours' predecessor by one and then add it
        to the queue, if its predecessor count is 0.
         */
        while (!sorting.isEmpty()) {
            removed = sorting.remove();
            System.out.println(topNum + " --> " + (char) (removed + 65));
            topNum = topNum + 1;
            for (int i = 0; i < adj[removed].length; i++) {
                if (adj[removed][i] != 0) {
                    adj[removed][i] = 0;
                    if (predecessor(i, adj) == 0) {
                        sorting.add(i);
                    }
                }
            }
        }
    }

    /**
     * this method calculates the number of predecessor for a given vertex in the adjacent matrix
     * @param vertex int whose predecessor has to be calculated
     * @param graph 2D array, the matrix from which predecessor has to be calculated
     * @return int, the number of predecessor
     */
    public static int predecessor(int vertex, int[][] graph) {
        int count = 0;
        for (int[] ints : graph) {
            if (ints[vertex] == 1)
                count = count + ints[vertex];
        }
        return count;
    }
}
