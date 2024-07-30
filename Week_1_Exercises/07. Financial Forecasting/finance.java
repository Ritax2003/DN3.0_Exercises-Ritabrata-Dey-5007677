/*
Exercise 7: Financial Forecasting
Scenario: 
You are developing a financial forecasting tool that predicts future values based on past data.
Steps:
1.	Understand Recursive Algorithms:
o	Explain the concept of recursion and how it can simplify certain problems.
2.	Setup:
o	Create a method to calculate the future value using a recursive approach.
3.	Implementation:
o	Implement a recursive algorithm to predict future values based on past growth rates.
4.	Analysis:
o	Discuss the time complexity of your recursive algorithm.
o	Explain how to optimize the recursive solution to avoid excessive computation.

*/

import java.util.*;
public class finance{
    public static double futureValue(double P0, double r, int n) {
        if (n == 0) {
            return P0;
        } else {
            return futureValue(P0, r, n - 1) * (1 + r);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the starting value:");
        double initialValue = sc.nextDouble(); // Initial investment
        System.out.println("Enter the growth rate:");
        double growthRate = sc.nextDouble();   // 5% annual growth rate
        System.out.println("Enter number of years:");
        int years = sc.nextInt();             // Number of years

        double fv = futureValue(initialValue, growthRate, years);
        System.out.println("The future value after " + years + " years is: " + fv);
        sc.close();
    }
}
/*
1. Understanding Recursive Algorithms
Recursion in Java works similarly to other languages. A recursive function calls itself with modified arguments to solve a problem in smaller steps. The two main components are:

Base Case: The stopping condition for the recursion, preventing infinite loops.
Recursive Case: The part of the function where the problem is broken down into smaller instances, with the function calling itself with these smaller instances.
2. Setup: Calculating Future Value Using a Recursive Approach
The future value of an investment, given an initial principal amount 
4. Analysis
Time Complexity:
The time complexity of this recursive function is O(n)
O(n) because it makes n recursive calls to compute the final value.

Optimizing the Recursive Solution:
To avoid the overhead of recursive calls, which can be expensive in terms of memory and processing time,
we can use an iterative approach. This avoids the issues associated with deep recursion, such as stack overflow.
The iterative method has the same time complexity O(n)
O(n) but is generally more efficient because it avoids the overhead associated with recursive function calls and uses a simple loop to achieve the same result.
*/
