package org.ft;

public class FizzBuzz {

    // If number is divisible by 3, print Fizz
    // If number is divisible by 5, print Buzz
    // If number is divisible by 3 and 5, print FizzBuzz
    // If number is NOT divisible by 3 or 5, then print the number

    // Refactored method
    public static String computeFizzBuzz(int number) {
        StringBuilder sb = new StringBuilder();
        if (number % 3 == 0) {
            sb.append("Fizz");
        }
        if (number % 5 == 0) {
            sb.append("Buzz");
        }
        if (sb.isEmpty()) {
            sb.append(number);
        }
        return sb.toString();
    }

    /*
    // tdd circle 4
    public static String computeFizzBuzz(int number) {
        if (number % 3 == 0 && number % 5 == 0) {
            return "FizzBuzz";
        } else if (number % 3 == 0) {
            return "Fizz";
        } else if (number % 5 == 0) {
            return "Buzz";
        } else
        return String.valueOf(number);
    }

    // tdd circle 3
    public static String computeFizzBuzz(int number) {
        if (number % 3 == 0) {
            return "Fizz";
        } else if (number % 5 == 0) {
            return "Buzz";
        }
        return String.valueOf(number);
    }

    //tdd circle 2
    public static String computeFizzBuzz(int number) {
        if (number % 3 == 0) {
            return "Fizz";
        }
        return String.valueOf(number);
    }

    //tdd circle 1
    public static String computeFizzBuzz(int number) {
        return String.valueOf(number);
    }
    */
}
