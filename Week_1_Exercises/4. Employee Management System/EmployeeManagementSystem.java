/*Exercise 4: Employee Management System
Scenario: 
You are developing an employee management system for a company. Efficiently managing employee records is crucial.
Steps:
1.	Understand Array Representation:
o	Explain how arrays are represented in memory and their advantages.
2.	Setup:
o	Create a class Employee with attributes like employeeId, name, position, and salary.
3.	Implementation:
o	Use an array to store employee records.
o	Implement methods to add, search, traverse, and delete employees in the array.
4.	Analysis:
o	Analyze the time complexity of each operation (add, search, traverse, delete).
o	Discuss the limitations of arrays and when to use them.

*/
import java.util.*;
class employee{
    private int id;
    private String name;
    private String pos;
    private double salary;

    public employee(int id , String name, String pos,double salary){
        this.id = id;
        this.name=name;
        this.pos = pos;
        this.salary = salary;
    }
    public int getEmployeeid(){ return id;}
    public String getName(){ return name;}
    public String getPos(){return pos;}
    public double getSalary(){ return salary;}

    public void setEmployeeId(int id){ this.id = id;}
    public void setName (String name){this.name = name;}
    public void setPos (String pos){this.pos = pos;}
    public void Setsalary(double salary){this.salary = salary;}

    @Override
    public String toString(){
        return "Employee [ID=" + id + ", Name=" + name + ", Position=" + pos + ", Salary=" + salary + "]";
    }

}
public class EmployeeManagementSystem {
    private employee[] emps;
    private int count;

    public EmployeeManagementSystem(int cap){
        emps = new employee[cap];
        count = 0;
    }
    public void addEmployee(employee employee){
        if(count < emps.length){
            emps[count++]  = employee;
        }
        else{
            System.out.println("Employee list is full");
        }
    }

    public employee searcEmployee(int empID){
        for(employee e : emps){
            if(e.getEmployeeid() == empID){
                return e;
            }
        }
        return null;
    }

    public void traverseEmp(){
        for(employee e :emps){
            System.out.println(e);
        }
    }

    public void delEmp(int empID){
        for (int i = 0; i < count; i++) {
            if (emps[i].getEmployeeid() == empID) {
                emps[i] = emps[count - 1]; 
                emps[count - 1] = null; 
                count--;
                return;
            }
        }
        System.out.println("Employee not found.");
    }

    public static void main(String[] args) {
        System.out.println("Enter number of employees:");
        Scanner sc = new Scanner(System.in);
        int cap = sc.nextInt();
        EmployeeManagementSystem em = new EmployeeManagementSystem(cap);
        for(int i=0;i<cap;i++){
            int id;
            String name, des;
            double salary;
            System.out.println("employee id:");
            id = sc.nextInt();
            System.out.println("employee name:");
            name = sc.next();
            System.out.println("employee post:");
            des = sc.next();
            System.out.println("employee salary:");
            salary = sc.nextDouble();
            em.addEmployee(new employee(id,name,des,salary));
        }
        System.out.println("All Employees:");
        em.traverseEmp();

        // Searching for an employee
        System.out.println("Enter ID of employee to search:");
        int id = sc.nextInt();
        employee e = em.searcEmployee(id);
        if (e != null) {
            System.out.println(e);
        } else {
            System.out.println("Employee not found.");
        }

        // Deleting an employee
        System.out.println("Enter ID of employee to delete:");
        id = sc.nextInt();
        em.delEmp(id);
        System.out.println("\nAll Employees after deletion:");
        em.traverseEmp();
        sc.close();
    }
}
