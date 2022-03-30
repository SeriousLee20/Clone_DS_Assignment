package com.newTry;

/*
 * Author: Hui Xin
 * 17 May 2021
 */

import org.jgrapht.alg.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    static RelationshipNetwork newR = new RelationshipNetwork();
    static Student[] group1 = new Student[10];
    static boolean[] visited = new boolean[10];
    //static List<Pair<Student, Integer>> possibleSpreader = new ArrayList<>();

    public static void main(String[] args) {

        // render a default graph
        newR.initializeGraph();

        // generate a student group
        // can add more info if needed
        for (int i = 1; i < 11; i++) {
            group1[i - 1] = new Student(i);
        }

        // test Arranging Books
        arrangingBooks();

        // this event has its own graph'
        // test Meet Your Crush
        meetYourCrush();
    }

    public static void meetYourCrush() {
        MeetYourCrush e5 = new MeetYourCrush();

        System.out.println("------Event 5 - Meet your crush------\n");
        e5.createGraph();

        Scanner sc = new Scanner(System.in);

        Student spreader, crush;

        while (true) {
            System.out.println("Who is the spreader? ");
            System.out.print("Spreader: ");
            int s = sc.nextInt();
            if ((s >= 1 && s <= 10)) {
                spreader = e5.students[s - 1];
                System.out.println();
                break;
            }
            else System.out.println("Invalid input, no such person, please try again");
            System.out.println();

        }

        while (true) {
            System.out.println("Who is your crush? ");
            System.out.print("Crush: ");
            int c = sc.nextInt();
            if ((c >= 1 && c <= 10)) {
                crush = e5.students[c - 1];
                System.out.println();
                break;
            }
            else System.out.println("Invalid input, no such person, please try again");
            System.out.println();
        }

        sc.nextLine();

        System.out.print("Do you want to have new connection? [Yes(y) / No(n)] --> ");
        String n = sc.nextLine();
        System.out.println();
        if (n.equals("y")) {
            System.out.println("How many connection do you want? ");
            int numberOfConnection = sc.nextInt();
            for (int i = 0; i < numberOfConnection;) {
                System.out.print("From the person: ");
                int from = sc.nextInt();
                System.out.print("To the person: ");
                int to = sc.nextInt();
                if ((from < 1 || from > 10) || (to < 1 || to > 10))
                    System.out.println("Invalid input, no such person in the graph, please try again");
                else {
                    e5.buildNewConnection(from, to);
                    i++;
                }
                System.out.println();
            }
        }

        int numberOfDays = 0;
        if (!e5.basicConditions(spreader, crush)) {
            e5.DFSRecursion(spreader, crush, visited, numberOfDays);
            e5.printPossibleSpreader(e5.possibleSpreader);
            e5.possibilityStopRumor(e5.possibleSpreader);
        }

        System.out.println("\n\nPropagates per day");
        List<Integer> propagatesPerDay = new ArrayList<>();
        e5.bfs(spreader, crush, visited, propagatesPerDay);
        for (int i = 1; i < propagatesPerDay.size(); i++) {
            System.out.println("Day " + i + ": " + propagatesPerDay.get(i) + " propagates");
        }
    }

    public static void arrangingBooks() {
        ArrangingBooks e4 = new ArrangingBooks();

        Scanner sc = new Scanner(System.in);

        System.out.print("Number of books: ");
        String numBooks = sc.nextLine();

        System.out.println("Height of each book");
        String[] booksHeight = new String[Integer.parseInt(numBooks)];
        for (int i = 0; i < Integer.parseInt(numBooks);) {
            System.out.print("Book " + (i+1) + " :");
            String height = sc.next();
            int h = Integer.parseInt(height);
            if (h <= 0)
                System.out.println("Invalid input, height must > 0");
            else {
                booksHeight[i] = height;
                i++;
            }
        }

        Stack<Integer> books = new Stack<>();
        for (String s : booksHeight)
            books.push(Integer.parseInt(s));

        System.out.println(books.toString());
        System.out.println("Number of round: " + e4.arrangeBook(books));
    }

    public static Student getStudent(int ID) {

        Student stud = group1[0];

        for (Student student : group1) {
            if (student.getStudID() == ID) {
                stud =  student;
            }
        }
        return stud;
    }
}