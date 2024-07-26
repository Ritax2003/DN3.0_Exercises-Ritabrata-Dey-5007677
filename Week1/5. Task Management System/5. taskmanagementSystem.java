/*
Exercise 5: Task Management System
Scenario: 
You are developing a task management system where tasks need to be added, deleted, and traversed efficiently.
Steps:
1.	Understand Linked Lists:
o	Explain the different types of linked lists (Singly Linked List, Doubly Linked List).
2.	Setup:
o	Create a class Task with attributes like taskId, taskName, and status.
3.	Implementation:
o	Implement a singly linked list to manage tasks.
o	Implement methods to add, search, traverse, and delete tasks in the linked list.
4.	Analysis:
o	Analyze the time complexity of each operation.
o	Discuss the advantages of linked lists over arrays for dynamic data.
*/
import java.util.*;
class Task{
    int id;
    String taskName;
    String status;
    public Task(int id,String name,String status){
        this.id = id;
        this.taskName = name;
        this.status = status;
    }

    @Override
    public String toString(){
        return "Task ID: " + id + ", Name: " + taskName + ", Status: " + status;
    }
}

class Node{
    Task task;
    Node next;

    Node(Task task){
        this.task = task;
        this.next = null;
    }
}

class TaskList{
    
    private Node head;
    public void addTask(Task task) {
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }
    public Task searchTask(int taskId) {
        Node current = head;
        while (current != null) {
            if (current.task.id == taskId) {
                return current.task;
            }
            current = current.next;
        }
        return null; // task not found
    }
    public void deleteTask(int taskId) {
        if (head == null) {
            return; // list is empty
        }

        if (head.task.id == taskId) {
            head = head.next;
            return;
        }

        Node current = head;
        while (current.next != null && current.next.task.id != taskId) {
            current = current.next;
        }

        if (current.next == null) {
            System.out.println("Task not found.");
            return;
        }

        current.next = current.next.next;
    }
}
public class taskmanagementSystem {
    public static void main(String[] args) {
        TaskList ts = new TaskList();
        int choice;
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("1. Enter new task:");
            System.out.println("2. Search task:");
            System.out.println("3. Delete task:");
            choice = sc.nextInt();
            if(choice==-1){
                break;
            }
            int id;
            String name; 
            String status;
            switch (choice) {
                case 1:
                System.out.println("Enter task id:");
                id = sc.nextInt();
                System.out.println("Enter task name:");
                name = sc.next();
                System.out.println("Enter task status:");
                status = sc.next();
                ts.addTask(new Task(id,name,status));
                break;

                case 2:
                System.out.println("Enter taskid to search:");
                id = sc.nextInt();
                Task t = ts.searchTask(id);
                System.out.println(t);
                break;

                case 3:
                System.out.println("Enter taskid to delete:");
                id = sc.nextInt();
                ts.deleteTask(id);
                break;
            }
        }
        sc.close();
    }
}
