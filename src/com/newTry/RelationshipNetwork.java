package com.newTry;

/*
 * Author: Hui Xin
 * 17 May 2021
 */

import org.jgrapht.graph.DirectedWeightedMultigraph;

/**
 * Graph class to form network among all students
 */

public class RelationshipNetwork {

    private DirectedWeightedMultigraph<Integer, Relationships> connection = new DirectedWeightedMultigraph<>(Relationships.class);
    private final String FRIEND = "friend";
    // can add more relationships

    /**
     * Default constructor
     * if want to build another self-defined graph
     */
    public RelationshipNetwork() {
    }

    /**
     * @param defaultGraph = true, then generate default graph
     * else can set up own graph
     */
    public RelationshipNetwork(boolean defaultGraph) {
        if (defaultGraph) {
            initializeGraph();
        }
    }

    /**
     * Add vertex into graph
     * @param studID as vertex
     */
    public void addVertex(int studID) {
        connection.addVertex(studID);
    }

    /**
     * Add edge between two vertices
     * @param stud source studID,
     * @param toStud destination studID
     * @param edge edge object which stores information about the relationship
     */
    public void addEdge(int stud, int toStud, Relationships edge) {
        connection.addEdge(stud, toStud, edge);
        //connection.setEdgeWeight(edge, rep);
    }

    /**
     * @param src source studID ,
     * @param dest destination studID
     * @return rep points
     */
    public double getRep(int src, int dest) {
        Relationships edge = connection.getEdge(src, dest);
        return edge.getRep();
    }

    /*public void setEdgeWeight(Relationships edge, double rep) {
        connection.setEdgeWeight(edge, rep);
    }*/

    /**
     * @return graph
     * to access built-in method of jgrapht
     */
    public DirectedWeightedMultigraph<Integer, Relationships> getConnection() {
        return connection;
    }

    /**
     * Get the edge between two students
     * can be null
     * @param src source studID
     * @param dest source studID
     * @return edge
     */
    public Relationships getEdge(int src, int dest) {
        Relationships edge = connection.getEdge(src, dest);

        if (edge == null) {
            System.out.println("No connection between " + src + " and " + dest);
            return null;
        }
        return edge;
    }

    /**
     * Set rep point for the edge of src and dest student
     *
     * Reset rep point
     *
     * @param src source studID
     * @param  dest destination studID
     * @param rep reputation points between the students
     */
    public void setRep(int src, int dest, double rep) {

        if (!connection.containsEdge(src, dest)) {

            // if there are no connections between each other
            createNewConnection(new Relationships(rep), new Relationships(0), src, dest);

        } else{

            Relationships edge = connection.getEdge(src, dest);
            edge.setRep(rep);
        }
    }

    /**
     * Print an edge in graph
     * Directed & Weighted
     * @param src source studID
     * @param dest destination studID
     */
    public void printEdge(int src, int dest) {

        if (!connection.containsEdge(src, dest)) {
            System.out.println("No connection between " + src + " and " + dest);
            return;
        }

        Relationships edge = getEdge(src, dest);
        System.out.printf("%d -> %d : %.0f\n", src, dest, edge.getRep());

        Relationships reverse = getEdge(dest, src);
        System.out.printf("%d -> %d : %.0f\n", dest, src, reverse.getRep());
    }

    /**
     * Print undirected edge
     * Without weight
     * @param src source student
     * @param dest destination student
     */
    public void printUndirectedUnweightedEdge(int src, int dest) {

        if (!connection.containsEdge(src, dest)) {
            System.out.println("No connection between " + src + " and " + dest);
            return;
        }

        System.out.println(src + " <-> " + dest);
    }

    /**
     * Print all edges in graph
     * Directed & weighted
     */
    public void printGraph() {

        for (Relationships edge : connection.edgeSet()) {
            int v1 = connection.getEdgeSource(edge);
            int v2 = connection.getEdgeTarget(edge);

            System.out.printf("%d -> %d : %.0f\n", v1, v2, edge.getRep());
        }
    }

    /**
     * Print all edges in a graph
     * Undirected & Unweighted
     */
    public void printUndirectedGraph() {

        int prevTarget = 2;

        for (Relationships edge : connection.edgeSet()) {

            int v1 = connection.getEdgeSource(edge);
            int v2 = connection.getEdgeTarget(edge);

            // if same edge, then prev target is the current head
            if (v1 == prevTarget) {
                continue;
            }

            prevTarget = v2;
            System.out.println(v1 + " <-> " + v2);
        }
    }

    @Override
    public String toString() {
        return "Relationships: " ;
    }

    /**
     * Create a graph with default group of students
     */
    public void initializeGraph() {

        for (int i = 1; i < 11; i++) {
            connection.addVertex(i);
        }

        createNewConnection(new Relationships(FRIEND, 5), new Relationships(FRIEND, 8), 1, 2);
        createNewConnection(new Relationships(FRIEND, 4), new Relationships(FRIEND, 3), 1, 7);
        createNewConnection(new Relationships(FRIEND, 5), new Relationships(FRIEND, 4), 2, 3);
        createNewConnection(new Relationships(FRIEND, 6), new Relationships(FRIEND, 2), 2, 5);
        createNewConnection(new Relationships(FRIEND, 9), new Relationships(FRIEND, 7), 2, 6);
        createNewConnection(new Relationships(FRIEND, 7), new Relationships(FRIEND, 10), 4, 8);
        createNewConnection(new Relationships(FRIEND, 7), new Relationships(FRIEND, 7), 4, 10);
        createNewConnection(new Relationships(FRIEND, 5), new Relationships(FRIEND, 6), 9, 10);

        printGraph();
    }

    /**
     * Create bidirectional connection (edge) between two students
     * @param r1 relationship object that stores information about the relationship of {@code src} to {@code dest}
     * @param r2 relationship object that stores information about the relationship of {@code dest} to {@code src}
     * @param src source studID
     * @param dest destination studID
     */
    public void createNewConnection(Relationships r1, Relationships r2, int src, int dest) {

        if (connection.containsEdge(r1) || connection.containsEdge(r2)) {
            System.out.println("Existed");
        }

        addEdge(src, dest, r1);
        addEdge(dest, src, r2);
    }
}
