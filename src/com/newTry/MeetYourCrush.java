package com.newTry;

import org.jgrapht.alg.util.Pair;

import java.util.*;

public class MeetYourCrush {
    Student[] students;
    boolean[] visited;
    List<Pair<Student, Integer>> possibleSpreader = new ArrayList<>();
    List<Pair<Student, Student>> newConnection = new ArrayList<>();

    // method not in main method
    public void createGraph() {
        students = new Student[10];
        for (int i = 0; i < students.length; i++)
            students[i] = new Student(i + 1);

        // first cluster
        students[0].addEdge(students[1], 1); // 1 - 2
        students[0].addEdge(students[6], 1); // 1 - 7
        students[1].addEdge(students[2], 1); // 2 - 3
        students[1].addEdge(students[4], 1); // 2 - 5
        students[1].addEdge(students[5], 1); // 2 - 6

        // second cluster
        students[3].addEdge(students[7], 2); // 4 - 8
        students[3].addEdge(students[9], 2); // 4 - 10
        students[8].addEdge(students[9],2); // 9 - 10

        // everyone has no idea to the rumor yet
        visited = new boolean[students.length];
    }

    public void buildNewConnection(int from, int to) {
        if (from != 0 || to != 0) {
            students[from - 1].addEdge(students[to - 1]);
            Pair<Student, Student> connection = new Pair<>(students[from - 1], students[to - 1]);
            newConnection.add(connection);
            System.out.println("New connection between person " + from + " to person " + to);
            return;
        }
        System.out.println("No such person, cannot build new connection");
    }

    public boolean friendWithCrush(Student spreader, Student crush) {
        for (Student friend : spreader.friends) {
            if (friend.equals(crush)) return true;
        }
        return false;
    }

    public void repeatSpreader(List<Pair<Student, Integer>> possibleSpreader, Pair<Student, Integer> successSpreader) {
        for (int i = 0; i < possibleSpreader.size(); i++) {
            if (possibleSpreader.get(i).getFirst().equals(successSpreader.getFirst())) {
                if (successSpreader.getSecond() < possibleSpreader.get(i).getSecond()) {
                    possibleSpreader.set(i, successSpreader);
                }
                return;
            }
        }
        possibleSpreader.add(successSpreader);
    }

    public void sort(List<Pair<Student, Integer>> possibleSpreader) {
        for (int i = 0; i < possibleSpreader.size(); i++) {
            Pair<Student, Integer> currentPair = possibleSpreader.get(i);
            int currentIndex = i;
            for (int j = i + 1; j < possibleSpreader.size(); j++) {
                if (currentPair.getSecond() > possibleSpreader.get(j).getSecond()) {
                    currentPair = possibleSpreader.get(j);
                    currentIndex = j;
                }
            }
            if (currentIndex != i) {
                possibleSpreader.set(currentIndex, possibleSpreader.get(i));
                possibleSpreader.set(i, currentPair);
            }
        }
    }


    public boolean basicConditions(Student spreader, Student crush) {
        if (spreader.cluster != crush.cluster) {
            int sameCluster = 0;

            for (Pair<Student, Student> studentStudentPair : newConnection) {
                if (studentStudentPair.getFirst().cluster == studentStudentPair.getSecond().cluster) {
                    sameCluster++;
                }
            }
            if (sameCluster == newConnection.size()) {
                System.out.println("Result: No need to worry, my crush won't know the rumor");
                System.out.println("Reason: My crush and the spreader are in different clusters and not connected");
                return true;
            }
        }

        int propagatesPerDay = spreader.friends.size();
        if (friendWithCrush(spreader, crush)) {
            System.out.println("Result: I'm unable to stop the rumor");
            System.out.println("Reason: My crush is one of the spreader's friend");
            System.out.println("Action: Too late to convince");
            System.out.println("Number of propagates on day 1: " + propagatesPerDay);
            return true;
        }

        if (spreader.friends.size() == 1) {
            System.out.println("Result: I'm able to stop the rumor");
            System.out.println("Reason: The spreader has only one friend");
            System.out.println("Action: Convince person" + spreader.friends.get(0));
            System.out.println("Number of propagates on day 1: " + propagatesPerDay);
            return true;
        }

        if (crush.friends.size() == 1) {
            System.out.println("Result: I'm able to stop the rumor");
            System.out.println("Reason: My crush has only one friend");
            System.out.println("Action: Convince person" + crush.friends.get(0));
            System.out.println("Number of propagates on day 1: " + propagatesPerDay);
            return true;
        }
        return false;
    }

    public void DFSRecursion(Student spreader, Student crush, boolean[] visited, int numberOfDays) {
        visited[spreader.getStudID() - 1] = true;
        spreader.numberOfDaysToKnow = numberOfDays;

        if (friendWithCrush(spreader, crush)) {
            Pair<Student, Integer> successSpreader = new Pair<>(spreader, spreader.numberOfDaysToKnow);
            if (possibleSpreader.size() > 0)
                repeatSpreader(possibleSpreader, successSpreader);
            else
                possibleSpreader.add(successSpreader);
        }

        for (Student peopleAround : spreader.friends) {
            if (!visited[peopleAround.getStudID() - 1] && !peopleAround.equals(crush)) {
                numberOfDays++;
                DFSRecursion(peopleAround, crush, visited, numberOfDays);
                numberOfDays--;
                visited[peopleAround.getStudID() - 1] = false;
            }
        }
    }

    public void bfs(Student start, Student crush, boolean[] visited, List<Integer> propagatesPerDay) {
        Queue<Student> print = new LinkedList<>();
        Queue<Student> bfs = new LinkedList<>();
        int layer = 0;

        print.add(start);
        visited[start.getStudID() - 1] = true;

        while (!print.isEmpty()) {
            System.out.print("Layer " + layer + ": ");
            propagatesPerDay.add(print.size());

            while (!print.isEmpty()) {
                Student temp = print.poll();
                System.out.print(temp + " ");
                bfs.add(temp);
            }
            System.out.println();

            while (!bfs.isEmpty()) {
                for (Student s : bfs.poll().friends) {
                    if (s.equals(crush)) {
                        System.out.println("Reached crush " + crush.getStudID() + "\n");
                        return;
                    }
                    if (!visited[s.getStudID() - 1]) {
                        print.add(s);
                        visited[s.getStudID() - 1] = true;
                    }
                }
            }
            layer++;
        }
    }


    public void possibilityStopRumor(List<Pair<Student, Integer>> possibleSpreader) {
//        sort(possibleSpreader);

        int maxDays = possibleSpreader.get(possibleSpreader.size() - 1).getSecond();

        if (possibleSpreader.size() > maxDays) {
            System.out.println("Result: I can't stop the rumor");
            System.out.println("Reason: Too late to convince all possible spreader");
            System.out.println("Action: Cry");
        } else {
            System.out.println("Result: I can convince all the possible spreaders");
            System.out.println("Reason: Enough days for me to have teatime with them");
            System.out.print("Action: I convinced ");
            for (Pair<Student, Integer> studentIntegerPair : possibleSpreader) {
                System.out.print(studentIntegerPair.getFirst().getStudID() + " ");
            }
        }
    }

    public void printPossibleSpreader(List<Pair<Student, Integer>> possibleSpreader) {
        sort(possibleSpreader);

        System.out.println("The people who will spread the rumor to my crush with the least days to know the rumor");
        for (int i = 0; i < possibleSpreader.size(); i++) {
            System.out.println((i+1) + ". " +  possibleSpreader.get(i).getFirst().getStudID() + " --> " +
                    possibleSpreader.get(i).getSecond() + " days");
        }
        System.out.println();
    }

    public static void main(String[] args) {
//        System.out.println("------Event 5 - Meet your crush------\n");
//        createGraph();
//
//        Scanner sc = new Scanner(System.in);
//
//        Student spreader, crush;
//
//        while (true) {
//            System.out.println("Who is the spreader? ");
//            System.out.print("Spreader: ");
//            int s = sc.nextInt();
//            if ((s > 1 && s < 10)) {
//                spreader = group1[s - 1];
//                System.out.println();
//                break;
//            }
//            else System.out.println("Invalid input, no such person, please try again");
//            System.out.println();
//
//        }
//
//        while (true) {
//            System.out.println("Who is your crush? ");
//            System.out.print("Crush: ");
//            int c = sc.nextInt();
//            if ((c > 1 && c < 10)) {
//                crush = group1[c - 1];
//                System.out.println();
//                break;
//            }
//            else System.out.println("Invalid input, no such person, please try again");
//            System.out.println();
//        }
//
//        sc.nextLine();
//
//        System.out.print("Do you want to have new connection? [Yes(y) / No(n)] --> ");
//        String n = sc.nextLine();
//        System.out.println();
//        if (n.equals("y")) {
//            System.out.println("How many connection do you want? ");
//            int numberOfConnection = sc.nextInt();
//            for (int i = 0; i < numberOfConnection;) {
//                System.out.print("From the person: ");
//                int from = sc.nextInt();
//                System.out.print("To the person: ");
//                int to = sc.nextInt();
//                if ((from < 1 || from > 10) || (to < 1 || to > 10))
//                    System.out.println("Invalid input, no such person in the graph, please try again");
//                else {
//                    buildNewConnection(from, to);
//                    i++;
//                }
//                System.out.println();
//            }
//        }
//
//        int numberOfDays = 0;
//        if (!basicConditions(spreader, crush)) {
//            DFSRecursion(spreader, crush, visited, numberOfDays);
//            printPossibleSpreader(possibleSpreader);
//            possibilityStopRumor(possibleSpreader);
//        }
//
//        System.out.println("\n\nPropagates per day");
//        List<Integer> propagatesPerDay = new ArrayList<>();
//        bfs(spreader, crush, visited, propagatesPerDay);
//        for (int i = 1; i < propagatesPerDay.size(); i++) {
//            System.out.println("Day " + i + ": " + propagatesPerDay.get(i) + " propagates");
//        }
    }
}