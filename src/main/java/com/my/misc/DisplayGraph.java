package com.my.misc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DisplayGraph {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new FileReader("/Users/abyk/Documents/graph_1.txt"));
        String line;
        int count = 0;
        while ((line = bf.readLine()) != null) {
            if (count > 0) {
                String[] arr = line.split("\\s");
                Integer node1 = Integer.parseInt(arr[0]);
                Integer node2 = Integer.parseInt(arr[1]);
            }
            count++;
        }

        /*A = random_matrix(ZZ,6, density=0.5)
        G = DiGraph(A, format='weighted_adjacency_matrix')  # graph from matrix
                H = G.plot(edge_labels=True, graph_border=True)
        H.show()             # display on screen
        H.save('graph.pdf')  # save plot to vector pdf for inclusion in a paper*/
    }
}
