/*
Exercise 1: Implementing the Singleton Pattern
Scenario: 
You need to ensure that a logging utility class in your application has only one instance throughout the application lifecycle to ensure consistent logging.
Steps:
1.	Create a New Java Project:
o	Create a new Java project named SingletonPatternExample.
2.	Define a Singleton Class:
o	Create a class named Logger that has a private static instance of itself.
o	Ensure the constructor of Logger is private.
o	Provide a public static method to get the instance of the Logger class.
3.	Implement the Singleton Pattern:
o	Write code to ensure that the Logger class follows the Singleton design pattern.
4.	Test the Singleton Implementation:
o	Create a test class to verify that only one instance of Logger is created and used across the application.

*/
import java.util.*;

class Logger{
    private static Logger instance;
    private Logger(){

    }
    public static Logger getInstance(){
        if(instance == null){
            instance = new Logger();
        }
        return instance;
    }

    public void log(String msg){
        System.out.println("log message: "+msg);
    }
}
public class SingletonPatternExample {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        if(logger1==logger2){
            System.out.println("both logger is same...");
        }
        else{
            System.out.println("Both loggers are different");
        }
        logger1.log("This is a log message.");
        logger2.log("This is another log message.");
    }
}
