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
public class Node {
    String name;
    double heuristic;
    double cost = 0;
    Node parent = null;
    ArrayList<Edge> edge = new ArrayList<>();

    public Node(String name, double heuristic) {
        this.name = name;
        this.heuristic = heuristic;
    }
    
    public double getFunctionCost(){
        return heuristic + cost;
    }
    
    public void updateParent(Node n, ArrayList<Node> open){
        parent = n;
        cost = n.cost + n.getCostEdge(this);
        for (Edge e : edge) {
            if (open.contains(e.tujuan)){
                e.tujuan.updateParent(this, open);
            }
        }
    }
    
    public double getCostEdge(Node n){
        for (Edge e : edge) {
            if (e.tujuan.equals(n)) {
                return  e.cost;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return name + "(" + getFunctionCost() + ")";
    }
    
    
}
