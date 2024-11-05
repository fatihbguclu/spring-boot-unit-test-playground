package org.ft;

public class Main {
    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            System.out.println(i + ", " + FizzBuzz.computeFizzBuzz(i));
        }
    }
}
