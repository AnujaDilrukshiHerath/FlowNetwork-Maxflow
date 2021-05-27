package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static int [][] graph;
    public static void main(String[] args){
        // Main method (Primary method) -The most important approach
        graph = readingFromatFile();
        mainMenu();
    }
    private static void mainMenu() {
        // The menu can be seen on the main menu, and the stopwatch begins and calculates the maximum flow.

        System.out.println();
        if (graph != null) {
            // Displays reuslts
            System.out.println("* * * * * Adjacent Matrix for a given Graph * * * * * \n");
            displayTheGraph(graph);
            System.out.println();
            System.out.println("Maximum generated flow = " + MaxFlow.fordFulkersonAlgorithm(graph, 0, graph.length - 1));
//       System.out.println("Time Taken = " + stopwatch.elapsedTime());
        }
    }
    private static int[][] readingFromatFile() {
        // Reading the data from the text file
        //Note that when reading data from a text file, the path to the text file must be entered correctly.
        ArrayList<String> dataInput = new ArrayList<>();
        System.out.println("" + "^ * ^ Data from the file is being collected... ^ * ^ ");

        //try catch method for reading the input files
        try {
            File myObj = new File("benchmarks/bridge_9.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                dataInput.add(data);
            }
            myReader.close();

            //Catch method for if i have put an wrong file that not available in the folder then an error
            // message will shown as, something went wrong file not found!....(make sure all the time adding the correct file names
        } catch (FileNotFoundException e) {
            System.out.println("- - - - - -Something went wrong, File not found...! - - - - -");
        }
        //method for inputting the file data's
        if (dataInput.size() != 0) {
            return generatingGraph(dataInput);
        } else {
            return null;
        }
    }
    public static int[][] generatingGraph(ArrayList<String> inputData){
        // Generating the graph matrix
        int graph_size = Integer.parseInt(inputData.get(0).trim());
        int[][] graph = new int[graph_size][graph_size];

        System.out.println("- - - - - - - Generating an Adjacent Matrix . . . - - - - - - ");
        //Displaying Generating an adjacent matrix
        //for method for getting the graph size with row and column
        for (int row = 0; row < graph_size; row++) {
            for (int column = 0; column < graph_size; column++) {
                graph[row][column] = 0;
            }
        }
        // using the for method to generating the input data to graph
        for (int item = 1; item < inputData.size(); item++) {
            String[] split_item = inputData.get(item).split(" ");
            int u = Integer.parseInt(split_item[0].trim());
            int w = Integer.parseInt(split_item[1].trim());
            int value = Integer.parseInt(split_item[2].trim());
            addingEdge(u, w, value, graph);
        }
        return graph;
    }
    private static void addingEdge(int j, int w, int capacity, int[][] graph) {
        // adding edge to the method
        graph[j][w] = capacity;
    }

    public static void displayTheGraph(int[][] graph){
        // Displaying the graph
        int number_of_edges = 0;
        for (int[] data_row : graph) {
            for (int index = 0; index< graph.length; index++) {
                if(data_row[index] > 0){
                    number_of_edges++;
                }
                System.out.print(data_row[index] + " ");
            }
            System.out.println();
        }
        System.out.println("\nTotal of " + graph.length + " nodes present");
        // Displaying the total number of nodes present

        System.out.println("Total of " + number_of_edges + " edges present");
        //Displaying the Total numbers of edges present

        System.out.println("The Source Node = 0");
        //Displaying the source nodes

        System.out.println("The Target Node = " + (graph.length - 1));
        //Displaying the target node
    }
}
