package com.newTry;

import java.util.Scanner;
import java.util.Stack;

public class ArrangingBooks {

    public boolean nonIncreasingOrder(Stack<Integer> heights) {
        Stack<Integer> copy = (Stack<Integer>) heights.clone();

        while (copy.size() > 1) {
            int last = copy.pop();
            if (last > copy.peek())
                return false;
        }
        return true;
    }

    public int arrangeBook(Stack<Integer> books) {
        int round = 0;

        while (!nonIncreasingOrder(books)) {
            Stack<Integer> temp = new Stack<>();

            while (books.size() > 1) {
                int last = books.pop();
                int secondLast = books.pop();
                if (last <= secondLast) {
                    temp.push(last);
                }
                books.push(secondLast);
            }
            round++;

            while (!temp.isEmpty())
                books.push(temp.pop());

            System.out.println(books.toString());
        }
        return round;
    }

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        System.out.print("Number of books: ");
//        String numBooks = sc.nextLine();
//
//        System.out.println("Height of each book");
//        String[] booksHeight = new String[Integer.parseInt(numBooks)];
//        for (int i = 0; i < Integer.parseInt(numBooks);) {
//            System.out.print("Book " + (i+1) + " :");
//            String height = sc.next();
//            int h = Integer.parseInt(height);
//            if (h <= 0)
//                System.out.println("Invalid input, height must > 0");
//            else {
//                booksHeight[i] = height;
//                i++;
//            }
//        }
//
//        Stack<Integer> books = new Stack<>();
//        for (String s : booksHeight)
//            books.push(Integer.parseInt(s));
//
//        System.out.println(books.toString());
//        System.out.println("Number of round: " + arrangeBook(books));
    }
}

