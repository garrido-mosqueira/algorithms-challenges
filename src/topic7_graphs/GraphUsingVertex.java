package topic7_graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class GraphUsingVertex {

    /**
     * SOLUTION USING STACK
     */
    static class Vertex {
        int value;
        boolean isVisited;
        List<Vertex> neighbours;

        Vertex(int value) {
            this.value = value;
            this.isVisited = false;
            this.neighbours = new ArrayList<>();
        }

        void addNeighbours(Vertex vertex) {
            this.neighbours.add(vertex);
        }
    }

    public static void main(String[] args) {
        Vertex v9 = new Vertex(9);
        Vertex v2 = new Vertex(2);
        Vertex v15 = new Vertex(15);
        Vertex v1 = new Vertex(1);
        Vertex v4 = new Vertex(4);
        Vertex v6 = new Vertex(6);
        Vertex v18 = new Vertex(18);

        v9.addNeighbours(v2);
        v9.addNeighbours(v15);
        v2.addNeighbours(v1);
        v2.addNeighbours(v4);
        v15.addNeighbours(v6);
        v15.addNeighbours(v18);

        List<Vertex> vertices = Arrays.asList(v9, v2, v15, v1, v4, v6, v18);

        System.out.println("DFS - Stack");
        depthFirstSearch(v9);
        System.out.println();
        System.out.println(" | ");

        vertices.forEach(vertex -> vertex.isVisited = false);

        System.out.println("BFS - Queue");
        breadthFirstSearch(v9);
        System.out.println();


    }

    public static void depthFirstSearch(Vertex root) {
        Stack<Vertex> stack = new Stack<>();
        stack.push(root);
        System.out.println("root "+ root.value + " -> ");
        while (!stack.isEmpty()) {
            Vertex current = stack.pop();
            current.isVisited = true;
            for (Vertex neighbour : current.neighbours) {
                if (!neighbour.isVisited) {
                    neighbour.isVisited = true;
                    System.out.print(neighbour.value + " -> ");
                    stack.push(neighbour);
                }
            }
        }
    }

    private static void breadthFirstSearch(Vertex root) {
        Queue<Vertex> queue = new LinkedList<>();
        queue.offer(root);
        System.out.println("root "+ root.value + " -> ");
        while (!queue.isEmpty()) {
            Vertex current = queue.remove();
            current.isVisited = true;
            for (Vertex neighbour : current.neighbours) {
                if (!neighbour.isVisited) {
                    neighbour.isVisited = true;
                    System.out.print(neighbour.value + " -> ");
                    queue.offer(neighbour);
                }
            }
        }
    }
}
