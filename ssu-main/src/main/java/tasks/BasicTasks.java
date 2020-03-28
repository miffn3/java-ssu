package tasks;

import tasks.model.Point;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class BasicTasks {
    public static void task1_1() {
        System.out.println("Enter first number:");
        int a = Utils.readNum();
        System.out.println("Enter second number:");
        int b = Utils.readNum();

        int sumOfSqrs = a * a + b * b;
        int sqrdSum = (a + b) * (a + b);
        System.out.println(Math.max(sumOfSqrs, sqrdSum));
    }

    public static void task1_2() {
        System.out.println("Enter the salary: ");
        int salary = Utils.readNum();
        System.out.println("Enter the years in company:");
        int years = Utils.readNum();

        double sumToPay = 0;
        double addedPart = 0;

        if(years >=2 && years < 5) {
            addedPart = 0.02;
        } else if (years >= 5 && years <= 10){
            addedPart = 5;
        }

        sumToPay = salary * (1 + addedPart);

        System.out.println("Sum to pay: " + sumToPay + "\nAdded part is: " + addedPart);
    }

    public static void task1_3() {
        Point A = new Point();
        Point B = new Point();
        System.out.println("Enter A point: ");
        System.out.println("Enter Ax:");
        A.setX(Utils.readNum());
        System.out.println("Enter Ay:");
        A.setY(Utils.readNum());
        System.out.println("Enter B point: ");
        System.out.println("Enter Bx:");
        B.setX(Utils.readNum());
        System.out.println("Enter By:");
        B.setY(Utils.readNum());

        double aTo0 = A.lengthTo0();
        double bTo0 = B.lengthTo0();

        if (aTo0 > bTo0) {
            System.out.println("A");
        } else if (bTo0 > aTo0) {
            System.out.println("B");
        } else {
            System.out.println("They have same length to 0");
        }
    }

    public static void task1_4() {
        System.out.println("Enter a: ");
        int a = Utils.readNum();
        System.out.println("Enter b: ");
        int b = Utils.readNum();
        System.out.println("Enter c: ");
        int c = Utils.readNum();

        String message = "no";

        if(a * a + b * b == c * c) {
            message = "yes";
        }
        System.out.println(message);
    }

    public static void task1_5() {
        System.out.println("Enter a: ");
        int a = Utils.readNum();
        System.out.println("Enter b: ");
        int b = Utils.readNum();
        System.out.println("Enter c: ");
        int c = Utils.readNum();

        a = checkNum(a);
        b = checkNum(b);
        c = checkNum(c);

        System.out.println(a + " " + b + " " + c);
    }

    private static int checkNum(int n) {
        if (n >= 0) {
            return n * n;
        }
        return n;
    }

    public static void task1_6() {
        System.out.println("Enter number in range 1-12: ");
        int m = Utils.readNumInRangeMonths();

        if(m == 1 || m == 2 || m == 12) {
            System.out.println("It's winter");
        }

        if (m >= 3 && m <= 5) {
            System.out.println("It's spring");
        }

        if (m >= 6 && m <= 8) {
            System.out.println("It's summer");
        }

        if (m >= 9 && m <= 11) {
            System.out.println("It's autumn");
        }
    }

    public static void task2_1() {
        System.out.println("Enter n: ");
        int n = Utils.readNum();
        List<Integer> allNumsInRange = new ArrayList<Integer>();

        for (int i = 0; i < n; i++) {
            int sum = 0;
            int current = i;
            while (current != 0) {
                sum += current % 10;
                current = current/10;
            }
            if (i % 3 == 0 && i % 5 != 0 && sum % 3 == 0 && sum % 5 != 0) {
                allNumsInRange.add(i);
            }
        }
    }

    public static void task2_2() {
        System.out.println("Enter n: ");
        int n = Utils.readNum();
        List<Integer> allNumsInRange = new ArrayList<Integer>();

        for (int i = 4; i < n; i++) {
            if (i % 5 == 0) {
                allNumsInRange.add(i);
            }
        }
    }

    public static void task2_3() {
        System.out.println("Enter number to check: ");
        int a = Utils.readNum();

        while (a != 1) {
            if (a % 2 != 0) {
                System.out.println("No");
                break;
            } else {
                a = a / 2;
            }
        }
        System.out.println("Yes");
    }

    public static void task2_4() {
        System.out.println("Enter n: ");
        int n = Utils.readNum();
        if(n >= 1) {
            List<Integer> fibonacci = new ArrayList<Integer>();
            fibonacci.add(1);
            fibonacci.add(1);

            while (fibonacci.get(fibonacci.size() - 1) < n) {
                int next = fibonacci.get(fibonacci.size() - 2) + fibonacci.get(fibonacci.size() - 1);
                if (next < n) {
                    fibonacci.add(next);
                } else {
                    break;
                }
            }
            System.out.println(fibonacci.get(fibonacci.size() - 1));
        } else {
            System.out.println("There is no fibonacci number");
        }
    }

    public static void task3_1() {
        System.out.println("Enter a: ");
        int a = Utils.readNum();
        System.out.println("Enter b: ");
        int b = Utils.readNum();

        List<Integer> allNumInRange = new ArrayList<Integer>();

        for (int i = a ; i <= b; i++) {
            allNumInRange.add(i);
        }

        System.out.println("Count of all numbers in range : " + allNumInRange.size());
    }

    public static void task3_2() {
        System.out.println("Enter a: ");
        int a = Utils.readNum();
        System.out.println("Enter b: ");
        int b = Utils.readNum();

        List<Integer> allNumInRange = new ArrayList<Integer>();


        for (int i = b - 1 ; i > a; i--) {
            allNumInRange.add(i);
        }

        System.out.println("Count of all numbers in range : " + allNumInRange.size());
    }

    public static void task3_3() {
        System.out.println("Enter a: ");
        double a = Utils.readNumDouble();
        System.out.println("Enter n: ");
        int n = Utils.readNum();

        double res = a;

        for (int i = 1; i < n; i++) {
            res = res * a;
        }

        System.out.println(res);
    }

    public static void task3_4() {
        System.out.println("Enter a: ");
        double a = Utils.readNumDouble();
        System.out.println("Enter n: ");
        int n = Utils.readNum();

        if (n > 0) {
            List<Double> allNums = new ArrayList<Double>();
            allNums.add(a);

            while (allNums.get(allNums.size() - 1) < n) {
                double next = allNums.get(allNums.size() - 1) * a;
                if (next < n) {
                    allNums.add(next);
                } else {
                    break;
                }
            }
        } else {
            System.out.println("Incorrect n");
        }
    }

    public static void task4_1() {
        System.out.println("Enter n: ");
        int n = Utils.readNum();
        List<Integer> even = new ArrayList<Integer>();
        List<Integer> odd = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            System.out.println("Enter next number: ");
            int next = Utils.readNum();

            if (next % 2 == 0) {
                even.add(next);
            } else {
                odd.add(next);
            }
        }

        StringBuilder evenNums = new StringBuilder();
        for (int i = 0; i < even.size(); i++) {
            evenNums.append(even.get(i));
            evenNums.append(" ");
        }

        System.out.println("Even: " + evenNums.toString());

        StringBuilder oddNums = new StringBuilder();
        for (int i = 0; i < odd.size(); i++) {
            oddNums.append(odd.get(i));
            oddNums.append(" ");
        }

        System.out.println("Odd: " + oddNums.toString());
    }

    public static void task4_2() {
        System.out.println("Enter n: ");
        int n = Utils.readNum();
        List<Integer> nums = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            System.out.println("Enter next number: ");
            int next = Utils.readNum();
            nums.add(next);
        }

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < nums.size(); i++) {
            int current = nums.get(i);
            if(current % 3 == 0) {
                res.append(current);
            }
        }

        System.out.println("Result: " + res.toString());
    }

    public static void task4_3() {
        System.out.println("Enter n: ");
        int n = Utils.readNum();
        List<Integer> nums = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            System.out.println("Enter next number: ");
            int next = Utils.readNum();
            nums.add(next);
        }

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < nums.size(); i++) {
            int current = nums.get(i);
            if(current % 5 == 0) {
                res.append(current);
            }
        }

        System.out.println("Result: " + res.toString());
    }

    public static void task4_4() {
        System.out.println("Enter n: ");
        int n = Utils.readNum();
        List<Integer> nums = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            System.out.println("Enter next number: ");
            int next = Utils.readNum();
            nums.add(next);
        }

        int LCM = nums.get(0);
        int GCD = nums.get(0);

        for (int i = 1; i < nums.size(); i++) {
            LCM = leastCommonMultiple(LCM, nums.get(i));
            GCD = greatestCommonDivisor(GCD, nums.get(i));
        }

        System.out.println("Least Common Multiple: " + LCM + "\nGreatest Common Divisor: " + GCD);
    }

    private static int leastCommonMultiple(int number1, int number2) {
        int biggerNumber = Math.max(number1, number2);
        int lessNumber = Math.min(number1, number2);
        int maxCommonMultiple = number1 * number2;
        int j = 1;
        int biggestNumberMultiple = biggerNumber;
        while (biggestNumberMultiple < maxCommonMultiple) {
            biggestNumberMultiple = biggerNumber * j;
            if (biggestNumberMultiple % lessNumber == 0) {
                return biggestNumberMultiple;
            }
            j++;
        }
        return maxCommonMultiple;
    }

    private static int greatestCommonDivisor(int number1, int number2) {
        List<Integer> factorsOfNumber1 = findFactors(number1);
        List<Integer> factorsOfNumber2 = findFactors(number2);
        List<Integer> commonFactors = new ArrayList<Integer>();
        for (int i = 0; i < factorsOfNumber1.size(); i++) {
            for (int j = 0; j < factorsOfNumber2.size(); j++) {
                if (factorsOfNumber1.get(i) == factorsOfNumber2.get(j)
                        && commonFactors.indexOf(factorsOfNumber1.get(i)) == -1) {
                    commonFactors.add(factorsOfNumber1.get(i));
                }
            }
        }

        int max = commonFactors.get(0);
        for (int i = 1; i < commonFactors.size(); i++) {
            int current = commonFactors.get(i);
            if (current > max) {
                max = current;
            }
        }
        return max;
    }

    private static List<Integer>  findFactors(int number) {
        List<Integer> factorsOfNumber = new ArrayList<Integer>();
        for (int i = 1; i <= number / 2; i++) {
            if (number % i == 0) {
                factorsOfNumber.add(i);
            }
        }
        factorsOfNumber.add(number);
        return factorsOfNumber;
    }

    public static void task4_5() {
        System.out.println("Enter n: ");
        int n = Utils.readNum();
        List<Integer> nums = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            System.out.println("Enter next number: ");
            int next = Utils.readNum();
            nums.add(next);
        }

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < nums.size(); i++) {
            int current = nums.get(i);
            if(isPrimeNumber(current)) {
                res.append(current);
            }
        }

        System.out.println("Result: " + res.toString());
    }

    private static boolean isPrimeNumber(int number) {
        boolean isPrime = true;
        double n = Math.sqrt(number);
        for (int j = 2; j < n; j++) {
            if (number % j == 0) {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }

//    public static void task4_6() {
//        System.out.println("Enter n: ");
//        int n = Utils.Utils.readNum();
//        List<Integer> nums = new ArrayList<Integer>();
//        for (int i = 0; i < n; i++) {
//            System.out.println("Enter next number: ");
//            int next = Utils.Utils.readNum();
//            nums.add(next);
//        }
//    }

    public static void task4_7() {
        System.out.println("Enter number: ");
        int a = Utils.readNumInRangeNum();

        switch (a) {
            case (0):
                System.out.println("Ноль");
                break;
            case (1):
                System.out.println("Один");
                break;
            case (2):
                System.out.println("Два");
                break;
            case (3):
                System.out.println("Три");
                break;
            case (4):
                System.out.println("Четыре");
                break;
            case (5):
                System.out.println("Пять");
                break;
            case (6):
                System.out.println("Шесть");
                break;
            case (7):
                System.out.println("Семь");
                break;
            case (8):
                System.out.println("Восемь");
                break;
            case (9):
                System.out.println("Девять");
                break;
        }
    }
}
