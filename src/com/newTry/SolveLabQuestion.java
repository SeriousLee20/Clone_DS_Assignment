package com.newTry;

/*
 * Author: Hui Xin
 * 17 May 2021
 */

/**
 * Class for Event 1
 */
public class SolveLabQuestion {

    RelationshipNetwork rn;

    /**
     * To construct an Event 2 object
     * @param rn graph that will be used
     */
    public SolveLabQuestion(RelationshipNetwork rn) {
        this.rn = rn;
    }

    /**
     * Start help solve lab question
     *
     * @param stud student that offering help
     * @param stranger student that is helped
     * @param good experience of learning from stud
     */
    public void solved(Student stud, Student stranger, boolean good) {

        int studID = stud.getStudID();  // get studID of stud

        // if the graph doesn't have stud, then add a new vertex
        if (!rn.getConnection().containsVertex(studID)) {
            rn.addVertex(studID);
        }

        int strangerID = stranger.getStudID();  // get studID of stranger

        //rn.addVertex(strangerID);

        if (good) {

            // if good experience, stud's rep point relative to stranger = 10
            rn.createNewConnection(new Relationships("friend", 0), new Relationships("friend", 10), studID, strangerID);
        } else {

            // if poor experience, stud's rep point relative to stranger = 2
            rn.createNewConnection(new Relationships("friend", 0), new Relationships("friend", 2), studID, strangerID);
        }
    }
}
