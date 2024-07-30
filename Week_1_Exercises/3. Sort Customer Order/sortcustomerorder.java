/*
Scenario: 
You are tasked with sorting customer orders by their total price on an e-commerce platform. This helps in prioritizing high-value orders.
Steps:
1.	Understand Sorting Algorithms:
o	Explain different sorting algorithms (Bubble Sort, Insertion Sort, Quick Sort, Merge Sort).
2.	Setup:
o	Create a class Order with attributes like orderId, customerName, and totalPrice.
3.	Implementation:
o	Implement Bubble Sort to sort orders by totalPrice.
o	Implement Quick Sort to sort orders by totalPrice.
4.	Analysis:
o	Compare the performance (time complexity) of Bubble Sort and Quick Sort.
o	Discuss why Quick Sort is generally preferred over Bubble Sort.
*/
import java.util.*;
class Order{
    private int orderId;
    private String customerName;
    private double totalPrice;
    public Order(int id,String name,double price ){
            this.orderId = id;
            this.customerName = name;
            this.totalPrice = price;
    }

    public int getorderId(){
        return orderId;
    }
    public String getCustomerName(){
        return customerName;
    }
    public double getPrice(){
        return totalPrice;
    }
    @Override
    public String toString(){
        return "Product{" +
        "productId=" + orderId +
        ", CustoemrName='" + customerName + '\'' +
        ", price='" + totalPrice + '\'' +
        '}';
    }
}
class Bubblesort{
    public static void bubblesort(Order[] orders){
            int n = orders.length;
            for(int i=0;i<n-1;i++){
                for(int j=0;j<n-i-1;j++){
                    if(orders[j].getPrice() > orders[j+1].getPrice()){
                        Order temp = orders[j];
                        orders[j]=orders[j+1];
                        orders[j+1] = temp;
                    }
                }
            }
        }
}

class QuickSort{
    public static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pi = partition(orders, low, high);

            quickSort(orders, low, pi - 1);
            quickSort(orders, pi + 1, high);
        }
    }
    public static int partition(Order[] orders,int l, int h){
        double pivot = orders[h].getPrice();
        int i = l-1;
        for(int j=l;j<h;j++){
            if(orders[j].getPrice() <= pivot){
                i++;
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j]= temp;
            }
        }
        Order temp = orders[i + 1];
        orders[i + 1] = orders[h];
        orders[h] = temp;
        return i+1;
    }
}
public class sortcustomerorder {
    public static void main(String[] args) {
        Order[] orders = {
                new Order(3, "Charlie", 450.00),
                new Order(1, "Alice", 300.50),
                new Order(4, "David", 200.00),
                new Order(2, "Bob", 150.75),
                
        };

        System.out.println("Original Orders:");
        for(Order o : orders){
            System.out.println(o);
        }

       
        Bubblesort.bubblesort(orders);
        System.out.println("\nSorted Orders (Bubble Sort):");
        System.out.println(Arrays.toString(orders));

        
        QuickSort.quickSort(orders, 0, orders.length - 1);
        System.out.println("\nSorted Orders (Quick Sort):");
        System.out.println(Arrays.toString(orders));
    }
}
