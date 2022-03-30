package com.newTry;

/*
 * Author: Hui Xin
 * 17 May 2021
 */

import org.jgrapht.graph.DefaultWeightedEdge;

/**Customised edge class to build connections between students*/

public class Relationships extends DefaultWeightedEdge {

    private String label;   // type of relationships between students
    private double rep;     // relative rep points

    /**
     * Default constructor
     * Assume all students are friends
     */
    public Relationships() {
    }

    /**
     * @param label relationship type
     * Unweighted graph
     */
    public Relationships(String label) {
        this.label = label;
    }

    /**
     * @param rep rep point
     * Weighted graph
     * All students have same relationship
     */
    public Relationships(double rep) {
        this.rep = rep;
    }

    /**
     * @param label type of relationship
     * @param rep rep points
     * Weighted graph
     */
    public Relationships(String label, double rep) {
        this.label = label;
        this.rep = rep;
    }

   /* public int hashCode()
    {
        return label.hashCode();
    }

    public boolean equals(Object obj)
    {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Relationships)) {
            return false;
        }

        Relationships edge = (Relationships) obj;
        return label.equals(edge.label);
    }*/

    /**
     * @return label (relationship type)
     */
    public String getLabel() {
        return label;
    }

    /**
     * Set label for this edge
     * @param label relationship type
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return rep point
     */
    public double getRep() {
        return rep;
    }

    /**
     * Set rep point for this edge
     * @param rep rep point for this edge
     */
    public void setRep(double rep) {
        this.rep = rep;
    }

    /**
     * to print relationship of two vertices
     * @return String
     */
    @Override
    public String toString() {
        return String.format("%s -> %s : %s", getSource(), getTarget(), label);
    }

}
