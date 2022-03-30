package com.newTry;

/*
 * Author: Hui Xin
 * 17 May 2021
 */

import java.util.ArrayList;
import java.util.List;

public class Student {
    private int studID;
    private int dive;
    private int lunchStart;
    private int lunchPeriod;
    List<Student> friends;
    int numberOfDaysToKnow;
    int cluster;


    /**
     * Default constructor
     */
    public Student() {
        studID = 0;
        dive = 0;
        lunchStart = 0;
        lunchPeriod = 0;
        friends = new ArrayList<>();
        numberOfDaysToKnow = 0;
        cluster = 0;
    }

    /**
     * Known student ID only
     * @param studID student ID
     */
    public Student(int studID) {
        this.studID = studID;
        friends = new ArrayList<>();
        numberOfDaysToKnow = 0;
        cluster = 0;
    }

    /**
     * Known student dive rate
     * @param studID student ID
     * @param dive dive rate of student (%)
     */
    public Student(int studID, int dive) {
        this.studID = studID;
        this.dive = dive;
    }

    /**
     * Known lunchStart and lunchPeriod
     * @param studID student ID
     * @param lunchStart time to start lunch (1100 - 1400)
     * @param lunchPeriod period for lunch (5 - 60 mins)
     */
    public Student(int studID, int lunchStart, int lunchPeriod) {
        this.studID = studID;
        this.lunchStart = lunchStart;
        this.lunchPeriod = lunchPeriod;
    }

    /**
     * Known dive, lunchStart, lunchPeriod
     * @param studID student ID
     * @param dive student dive rate (%)
     * @param lunchStart time to start lunch (1100 - 1400)
     * @param lunchPeriod period used for lunch (5 - 60 mins)
     */
    public Student(int studID, int dive, int lunchStart, int lunchPeriod) {
        this.studID = studID;
        this.dive = dive;
        this.lunchStart = lunchStart;
        this.lunchPeriod = lunchPeriod;
    }

    /**
     * Get ID of the student
     * @return studID
     */
    public int getStudID() {
        return studID;
    }

    /**
     * Set student ID
     * if use default constructor
     * or reset student ID
     * @param studID student ID
     */
    public void setStudID(int studID) {
        this.studID = studID;
    }

    /**
     * Get dive rate of the student
     * @return dive rate (%)
     */
    public int getDive() {
        return dive;
    }

    /**
     * Set dive rate
     * @param dive dive rate of student (%)
     */
    public void setDive(int dive) {
        this.dive = dive;
    }

    /**
     * Get time that the student start having lunch
     * @return lunch start time (1100 - 1400)
     */
    public int getLunchStart() {
        return lunchStart;
    }

    /**
     * Set lunch start time of the student
     * @param lunchStart time to start having lunch (1100- 1400)
     */
    public void setLunchStart(int lunchStart) {
        this.lunchStart = lunchStart;
    }

    /**
     * Get lunch period of the student
     * @return lunch period (5 - 60 mins)
     */
    public int getLunchPeriod() {
        return lunchPeriod;
    }

    /**
     * Set lunch period
     * @param lunchPeriod period from start to end of lunch (5 - 60 mins)
     */
    public void setLunchPeriod(int lunchPeriod) {
        this.lunchPeriod = lunchPeriod;
    }

    /**
     * Add new friend to the person friend list
     * @param toStud the specific person
     */
    public void addEdge(Student toStud) {
        // undirected
        this.friends.add(toStud);
        toStud.friends.add(this);
    }

    /**
     * Add new friend to the person friend list and specify the cluster
     * @param toStud the specific person and cluster the person belongs to
     */
    public void addEdge(Student toStud, int cluster) {
        // undirected
        this.friends.add(toStud);
        toStud.friends.add(this);

        this.cluster = cluster;
        toStud.cluster = cluster;
    }

    @Override
    public String toString() {
        return "Student " + getStudID();
    }
}
