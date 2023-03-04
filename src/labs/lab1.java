package labs;

import java.util.Scanner;

public class lab1 {

    public static void main(String[] args) {
//        ans1();
//        ans2();
//        ans3();
//        ans4();
//        ans5();
//        ans6();
    }

    private static void ans1() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter 3 edges of the triangle");
        int a = scan.nextInt(), b = scan.nextInt(), c = scan.nextInt();
        if (a + b > c && a + c > b && b + c > a && a > 0 && b > 0 && c > 0 && a - b < c && a - c < b && b - c < a) {
            System.out.println("It is a triangle");
        } else {
            System.out.println("It is not a triangle");
        }
        scan.close();
    }

    private static void ans2() {
        int spaceCount = 10, starCount = 1;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < spaceCount; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < starCount; j++) {
                System.out.print("*");
            }

            System.out.println();
            spaceCount -= 2;
            starCount += 2;
        }
    }

    private static void ans3() {
        int[] myIntArray = new int[]{3, 12, 9, 4, 2};
        double sum = 0;
        for (int i = 0; i < myIntArray.length; i++) {
            sum += myIntArray[i];
        }
        double avg = sum / myIntArray.length;
        System.out.println("Sum: " + sum + " Avg: " + avg);

        double sumOfSquares = 0;
        for (int i = 0; i < myIntArray.length; i++) {
            sumOfSquares += Math.pow(myIntArray[i] - avg, 2);
        }
        double sd = Math.sqrt(sumOfSquares / myIntArray.length);
        System.out.printf("Standard Deviation: %.3f", sd);

    }

    private static void ans4() {
        int multiDimensional[][] = {{1, 0, 0, 0, 0}, {0, -1, 0, 0, 0}, {0, 0, 1, 0, 0}, {0, 0, 0, 1, 0}, {0, 0, 0, 0, -1}};
        int size = multiDimensional.length;
        boolean isDiagonal = false;
        for (int i = 0; i < size; i++) {
            if (multiDimensional[i][i] == 1 || multiDimensional[i][i] == -1)
                isDiagonal = true;
            else {
                isDiagonal = false;
                break;
            }
        }
        System.out.println(isDiagonal);
    }

    private static void ans5() {
        String input = "bus city country girl";
        String[] parts = input.split(" ");

        for (int i = 0; i < parts.length; i++) {
//            System.out.print(parts[i] + " ");
            if (parts[i].endsWith("y")) {
                parts[i] = parts[i].substring(0, parts[i].length() - 1) + "ies";
            } else if (parts[i].endsWith("s") || parts[i].endsWith("c") || parts[i].endsWith("ch") || parts[i].endsWith("sh")) {
                parts[i] = parts[i] + "es";
            } else {
                parts[i] = parts[i] + "s";
            }
            if (i != parts.length - 1)
                System.out.print(parts[i] + " ");
            else
                System.out.print(parts[i]);
        }
    }

    private static void ans6() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a number to calculate sumOfCubes: ");
        int n = scan.nextInt();
        System.out.println(calculateSumOfCubes(n));
    }

    // These methods are used for ans6
    private static Integer calculateCube(Integer x) {
        return x*x*x;
    }
    private static Integer calculateSumOfCubes(Integer n) {
        Integer sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += calculateCube(i);
        }
        return sum;
    }
    // These methods are used for ans6
}
