/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TugasAI2;

/**
 *
 * @author adliih
 */
public class Edge {
    double cost;
    Node tujuan;

    public Edge(Node tujuan, int cost) {
        this.cost = cost;
        this.tujuan = tujuan;
    }
    
}
