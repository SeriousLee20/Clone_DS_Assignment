package com.newTry;

/*
 * Author: Hui Xin
 * 17 May 2021
 */

/**
 * Class for Event 2
 */
public class ChitChat {

    RelationshipNetwork rn;

    /**
     * To construct a ChitChat object
     * @param rn graph to be used
     */
    public ChitChat(RelationshipNetwork rn) {
        this.rn = rn;
    }

    /**
     * Start chit chat
     *
     * @param me student object of me
     * @param friend student object of my friend
     * @param stranger friend's friend, my stranger
     * @param good my chit chat about my good or bad
     */
    public void chitChat(Student me, Student friend, Student stranger, Boolean good) {

        int myID = me.getStudID();          // get my studID
        int friendID = friend.getStudID();  // get my friend's studID

        int strangerID = stranger.getStudID();  // get stranger's studID

        //rn.addVertex(strangerID);

        // to check if there's connection between friend and stranger
        if (rn.getEdge(friendID, strangerID) == null) {

            System.out.println("No connection between " + friendID + " and " + strangerID);
            return;
        }

        // get my rep point relative to my friend
        double friendToMeRep = rn.getRep(friendID, myID);

        System.out.println("My rep points relative to my friend: " + friendToMeRep);

        if (good) {

            // if chit chat for good, my rep point relative to stranger = friendToMeRep * 0.5
            rn.createNewConnection(new Relationships("friend's friend", 0), new Relationships("friend's friend", friendToMeRep * 0.5), myID, strangerID);

        } else {

            // if chit chat for bad, my rep point relative to stranger = -friendToMeRep
            rn.createNewConnection(new Relationships("friend's friend", 0), new Relationships("friend's friend", (0 - friendToMeRep)), myID, strangerID);

        }
    }
}

