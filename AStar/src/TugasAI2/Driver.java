/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TugasAI2;

import java.util.ArrayList;

/**
 *
 * @author adliih
 */
public class Driver {

    public static void main(String[] args) {
        Node ravenna = new Node("ravenna", 0),
                riminni = new Node("riminni", 0.5),
                ferrara = new Node("ferrara", 5),
                forli = new Node("forli", 2),
                cesena = new Node("cesena", 4.5),
                faenza = new Node("faenza", 4),
                imola = new Node("imola", 5),
                emilia = new Node("emilia", 6),
                terme = new Node("terme", 7),
                carpi = new Node("carpi", 8),
                piacenza = new Node("piacenza", 10),
                bobbia = new Node("bobbia", 10);

        bobbia.edge.add(new Edge(piacenza, 5));
        bobbia.edge.add(new Edge(terme, 3));
        bobbia.edge.add(new Edge(cesena, 15));

        piacenza.edge.add(new Edge(carpi, 3));
        piacenza.edge.add(new Edge(terme, 3));

        carpi.edge.add(new Edge(ferrara, 8));
        carpi.edge.add(new Edge(emilia, 2));

        terme.edge.add(new Edge(emilia, 2));
        terme.edge.add(new Edge(faenza, 3));

        emilia.edge.add(new Edge(imola, 2));

        imola.edge.add(new Edge(faenza, 1));
        imola.edge.add(new Edge(forli, 3));

        faenza.edge.add(new Edge(forli, 2));
        faenza.edge.add(new Edge(cesena, 6));

        cesena.edge.add(new Edge(riminni, 6));

        forli.edge.add(new Edge(ravenna, 3));
        forli.edge.add(new Edge(cesena, 2));

        ferrara.edge.add(new Edge(ravenna, 6));

        riminni.edge.add(new Edge(ravenna, 1));

        aStarAlgorithm(bobbia, ravenna);

        System.out.println("Solusi: " + printSolution(ravenna));
        System.out.println("Total Cost: " + ravenna.cost);
    }

    public static String printSolution(Node goal) {
        String s = "";
        while (goal != null) {
            s = goal.name + " " + s;
            goal = goal.parent;
        }
        return s;
    }

    public static void aStarAlgorithm(Node start, Node goal) {
//        ArrayList<Node> result = new ArrayList<>();
        ArrayList<Node> open = new ArrayList<>();
        ArrayList<Node> closed = new ArrayList<>();

        open.add(start);

        System.out.println("==Starting A*==");
        while (!open.isEmpty()) {
            System.out.println("Before Best Node Processed");
            System.out.println("\tOpen\t: " + open);
            System.out.println("\tClosed\t: " + closed);
            Node current = getBestNode(open);
            System.out.println("BestNode: " + current);
            if (current.equals(goal)) {
                System.out.println("==Finished A*==\n");
                break;
            }

            // Masukkan anak current ke open
            for (Edge e : current.edge) {
                Node currentChild = e.tujuan;
                if ((!closed.contains(currentChild)) || ((open.contains(currentChild)) && (currentChild.cost > current.cost + e.cost + currentChild.heuristic))) {
                    // Not found child in closed list, not explored yet
                    currentChild.updateParent(current, open);
                    open.add(currentChild);
                }
            }
            closed.add(current);
            System.out.println("After Best Node Processed");
            System.out.println("\tOpen\t: " + open);
            System.out.println("\tClosed\t: " + closed);
            System.out.println();
        }

//        if (current == null) {
//            return null;
//        } else {
//            while (current != null) {
//                result.add(0, current);
//                current = current.parent;
//            }
//        }
//        
//        return result;
    }

    public static Node getBestNode(ArrayList<Node> open) {
        if (open.isEmpty()) {
            return null;
        }
        Node result = open.get(0);
        for (Node node : open) {
            if (node.getFunctionCost() <= result.getFunctionCost()) {
                result = node;
            }
        }
        open.remove(result);
        return result;
    }
}
