/*Exercise 6: Library Management System
Scenario: 
You are developing a library management system where users can search for books by title or author.
Steps:
1.	Understand Search Algorithms:
o	Explain linear search and binary search algorithms.
2.	Setup:
o	Create a class Book with attributes like bookId, title, and author.
3.	Implementation:
o	Implement linear search to find books by title.
o	Implement binary search to find books by title (assuming the list is sorted).
4.	Analysis:
o	Compare the time complexity of linear and binary search.
o	Discuss when to use each algorithm based on the data set size and order.

*/
import java.util.*;

class Book{
    int bookid;
    String title;
    String author;

    public Book(int id,String title,String auth){
        this.bookid = id;
        this.title = title;
        this.author = auth;
    }

    public int getbookID(){
        return bookid;
    }
    public String getTitle(){
        return title;
    }
    public String getAuth(){
        return author;
    }

    @Override
    public String toString(){
        return "[Book id=" + bookid +", Title="+ title +", Author= " + author + "]";
    }
}
class LinearSearch{
    public static Book linearsearch(Book[] books,String tgt){
        for(Book b: books){
            if(b.getTitle().equalsIgnoreCase(tgt)) return b;
        }
        return null;
    }
}
class BinarySearch{
    public static Book binarysearch(int l, int h, Book[] books, String tgt){
        while(l<=h){
            int mid = l +(h-l)/2;
            int comparison = books[mid].getTitle().compareToIgnoreCase(tgt);
        if (comparison == 0) {
            return books[mid];
        } else if (comparison < 0) {
            l = mid + 1;
        } else {
            h = mid - 1;
        }

        
    }
    return null;
    }
}
public class LMS {
    public static void main(String[] args) {
        Book[] books = {
        new Book(1, "The Great Gatsby", "F. Scott Fitzgerald"),
        new Book(2, "1984", "George Orwell"),
        new Book(3, "To Kill a Mockingbird", "Harper Lee"),
        new Book(4, "The Catcher in the Rye", "J.D. Salinger")
        };

        for(Book b: books){
        System.out.println(b);
        }

        
        System.out.println("Enter book title to search:");
        Scanner sc = new Scanner(System.in);
        String tgt = sc.nextLine();
        Book result1 = LinearSearch.linearsearch(books, tgt);
        System.out.println(result1 != null ? result1 : "Book not found");

        Arrays.sort(books, (a, b) -> a.getTitle().compareToIgnoreCase(b.getTitle()));

        // Binary Search
        Book result2 = BinarySearch.binarysearch(0,books.length-1,books, tgt);
        System.out.println(result2 != null ? result2 : "Book not found");

    }
    

}
