package com.company;

// Implementation of Ford Fulkerson using Java

import java.lang.*;
import java.util.LinkedList;

class MaxFlow {
    /* If there is a path from source 'a' to sink 't' in the residual graph,
       this function returns valid. Additionally,
       it fills parent[] to save the road. */
    static boolean BreadthFirstSearch(int[][] resGraph, int a, int t, int[] parent, int Vertices)
    {
        /* Create a visited array and mark all vertices as
           not visited */
        boolean visited[] = new boolean[Vertices];
        for (int i = 0; i < Vertices; ++i)
            visited[i] = false;
        // Create a queue, enqueue source vertex and mark
        // source vertex as visited
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(a);
        visited[a] = true;
        parent[a] = -1;
        // Standard BFS Loop
        while (queue.size() != 0) {
            int u = queue.poll();
            for (int v = 0; v < Vertices; v++) {
                if (visited[v] == false && resGraph[u][v] > 0) {
                    /* If we find a relation to the sink node,
                       BFS is no longer necessary; we can
                       simply set its parent and return real. */
                    if (v == t) {
                        parent[v] = u;
                        return true;
                    }
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }
        /* We didn't reach sink in BFS starting from source,
           so return false */
        return false;
    }
    // Returns tne maximum flow from s to t in the given
    // graph
    static int fordFulkersonAlgorithm(int graph[][], int s, int t)
    {
        int Vertices;
        Vertices = t + 1;
        int u, v;
        /* Make a residual graph and fill it with the capacities
           from the original graph as residual capacities in the
           residual graph.
           Residual graph, where resGraph[i][j] denotes the residual
           capacity of an edge from I to j (if one exists).
           If resGraph[i][j] is 0, then there is no such thing as a graph. */
        int resGraph[][] = new int[Vertices][Vertices];
        for (u = 0; u < Vertices; u++)
            for (v = 0; v < Vertices; v++)
                resGraph[u][v] = graph[u][v];
        // This array is filled by BFS and to store path
        int parent[] = new int[Vertices];
        int max_flow = 0; // There is no flow initially
        long start = System.currentTimeMillis();
        // Augment the flow while there is a path from source
        // to sink
        while (BreadthFirstSearch(resGraph, s, t, parent,Vertices)) {
            /* Determine the edges' minimum residual capacity along
               the BFS-filled course.
               Or, to put it another way, find the optimal
               flow through the direction you've discovered.*/
            int path_flow = Integer.MAX_VALUE;
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                path_flow = Math.min(path_flow, resGraph[u][v]);
            }
            /* Reverse edges along the path and
               update residual capacities of the edges.h */
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                resGraph[u][v] -= path_flow;
                resGraph[v][u] += path_flow;
            }
            // Add path flow to overall flow
            System.out.print("\nAugment path : " + s);
            for (int i=0; i < parent.length; i++){
                if (parent[i]!=-1 && parent[i]!=0){
                    System.out.print("-->"+parent[i]);
                }
            }
            System.out.print("-->"+t);//printing t value
            System.out.println("\nFlow units that can be transported through this path : " + path_flow);
            System.out.println("Therefore, max fow is now : "+max_flow + " + " + path_flow + " = " + (max_flow+path_flow));
            max_flow += path_flow;


        }
        long now = System.currentTimeMillis();
        double elapsed = (now - start) / 1000.0;
        System.out.println("Elapsed time = " + elapsed + " seconds");

        // Return the overall flow
        return max_flow;

    }


}
