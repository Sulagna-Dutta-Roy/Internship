//library management system.Each book should contain following details ->Title, Author, ISBN, Price, Publication, Date of release
//To add books,to delete books,to update the list of books
//To display all the books
//display new arrivals
//To display removed books
//To give user option to request new books
//To display details of a particular book
//Give switch option to end user to perform above activities form n  number of times
//Create The application should be compatible with 4 users and Thread safe
//Each user is allowed to use application only for 4 minutes.
////Library should hold maximum 15 books , if count exceeds user should get a message
//"Racks are full" with customized class

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// the LibraryManagement Interface
interface LibraryManagement {
    void requestBook(int bookNumber);
    void completeDetails(int bookNo);
    void startLibrary() throws InterruptedException;
    void addNewData();
    void addToData();
    void removeBook(int bookNo);
    void update(String book);
    void newDisplayList();
    void displayList();
    void removedBooks();
}

//Custom exceptions for alert
class FullRackException extends Exception {
      public FullRackException() {
}
// getMessage alert for library overloading for books
public String getMessage() {
    return "Racks are full: Cannot add new Books!";
  }
}

class Book {
        String title;
        String author;
        String ISBN;
        int price;
        String publication;
        String dateOfRelease;

Book() { }
Book(String title, String author, String ISBN, int price, String publication, String dateOfRelease) {
     this.title = title;
     this.author = author;
     this.ISBN = ISBN;
     this.price = price;
     this.publication = publication;
     this.dateOfRelease = dateOfRelease;
   }
}
// library threads using vector
public class Library extends Thread implements LibraryManagement {
     static Vector<Book> data = new Vector<>();
     static Vector<String> removed_data = new Vector<>();

// adding some new data to the system
public void addNewData() {
     Scanner sc = new Scanner(System.in);
     System.out.println("#===========================");
     data.add(new Book());
     System.out.println("Enter the title of the book: ");

     data.get(data.size() - 1).title = sc.nextLine();
     System.out.println("Enter the Author of " + data.get(data.size() - 1).title + " book name: ");
     data.get(data.size() - 1).author = sc.nextLine();
     System.out.println("Enter the ISBN of " + data.get(data.size() - 1).title + " book name: ");
     data.get(data.size() - 1).ISBN = sc.next();
     System.out.println("Enter the price of " + data.get(data.size() - 1).title + " book name: ");
     data.get(data.size() - 1).price = sc.nextInt();
     System.out.println("Enter the name of publication of " + data.get(data.size() - 1).title + " book name: ");
     sc.nextLine();
     data.get(data.size() - 1).publication = sc.nextLine();
     System.out.println("Enter the date of release of " + data.get(data.size() - 1).title + " book name: ");
     data.get(data.size() - 1).dateOfRelease = sc.next();
}
// new data
public void addToData() {
      data.add(new Book("Harry Potter", "J.k. Rowling", "2-567891923-913-2", 100, "Bloomsbury","10-05-2000"));
      data.add(new Book("East of Eden", "John Steinback", "1-456243483632-2", 180, "Steinback", "02-01-2001"));
      data.add(new Book("The Sun Also Rises", "Ernest Hemingway"," 56789-01-2", 940, "Hemingway", "10-12-1986"));
      data.add(new Book("The House of Mirth", "Edith Wharton", "10009-1-2-1567", 190, "P publications","4-02-2010"));
      data.add(new Book("Their Eyes Were Watching God", "Zora Neale Hurston", "111009-2-456", 100, "K portal ","19-08-1986"));
      data.add(new Book("Engineering psychology and human performance", "Christopher D Wickens", "12345-90-1-3", 220,"Filledman", "09-7-2011"));
      data.add(new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", "123456-0-989", 1050, "", ""));

}
// remove all the books one by one
public void removeBook(int bookNo) {
   removed_data.add(data.get(bookNo - 1).title);
   data.remove(bookNo - 1);
}
// update the existing system
public void update(String book) {
    Scanner sc = new Scanner(System.in);
    int index = -1;
    for (int i = 0; i <= data.size() - 1; i++) {
       if (data.get(i).title.equals(book)) {
           index = i;
           break;
     }
}
    System.out.println("#===================================");
    if (index == -1) {
    System.out.println("Books does not exist");
    return;
}

    System.out.println("Enter 1 to update title");
    System.out.println("Enter 2 to update Author");
    System.out.println("Enter 3 to update ISBN");
    System.out.println("Enter 4 to update price");
    System.out.println("Enter 5 to update name of the publication");
    System.out.println("Enter 6 to update the date of release");
    System.out.println("Enter exit to exit");
    System.out.println("#======================");
    String update = sc.next();

switch (update) {
case "1":
    System.out.println("Enter the new book title: ");
    sc.nextLine();
    String title = sc.nextLine();
    System.out.println(title);
    data.get(index).title = title;
    System.out.println("Book title is updated");
    break;
case "2":
    sc.nextLine();
    System.out.println("Enter the new Author: ");
    String author = sc.nextLine();
    data.get(index).author = author;
    System.out.print("Book author is updated");
    break;
case "3":
    System.out.println("Enter the new ISBN: ");
    String ISBN = sc.next();
    data.get(index).ISBN = ISBN;
    break;
    case "4":
    System.out.println("Enter the new price: ");
    int price = sc.nextInt();
    data.get(index).price = price;
    System.out.print("Book price is updated");
    break;
    case "5":
       sc.nextLine();
       System.out.println("Enter the new publication: ");
       String publication = sc.nextLine();
       data.get(index).publication = publication;
       System.out.print("Book publication is updated");
    break;
    case "6":
      System.out.println("Enter the new date: ");
      String date = sc.next();
      data.get(index).dateOfRelease = date;
      System.out.print("Book date of release is updated");
    break;
    default:System.out.println("Exited!");
    break;
   }
}
public void newDisplayList() {
     for (int i = data.size() - 1; i >= 0 && i >= data.size() - 1 - 4; i--) {
         System.out.println(data.get(i).title);
     }
}
public void displayList() {
     for (int i = 0; i <= data.size() - 1; i++) {
        System.out.println((i + 1) + "-" + data.get(i).title);
     }
}
public void removedBooks() {
   if (removed_data.size() == 0) {
       System.out.println("No books removed yet..");
       return;
}
for (int i = 0; i <= removed_data.size() - 1; i++) {
      System.out.println(removed_data.get(i));
   }
}

public void requestBook(int bookNumber) {
    System.out.println("#=====================================");
    System.out.println(data.get(bookNumber).title + " is issued to User");
    data.remove(bookNumber);
    System.out.println("#=====================================");
}

public void completeDetails(int bookNo) {
    System.out.println("------------------------------------------------");
    System.out.println("Complete details: ");
    System.out.println("------------------------------------------------");
    System.out.println("Title: " + data.get(bookNo).title);
    System.out.println("Author: " + data.get(bookNo).author);
    System.out.println("ISBN: " + data.get(bookNo).ISBN);
    System.out.println("Price: Rs " + data.get(bookNo).price);
    System.out.println("Publication: " + data.get(bookNo).publication);
    System.out.println("Date Of Release: " + data.get(bookNo).dateOfRelease);
}
// starting the library function using multi threads
public void startLibrary() throws InterruptedException {
    Scanner sc = new Scanner(System.in);

    String ch = "";
    do {
        Thread.sleep(300);
        System.out.println("#=====================================");
        Thread.sleep(300);
        System.out.println("Enter 1 to add a book. ");
        Thread.sleep(300);
        System.out.println("Enter 2 to remove a book. ");
        Thread.sleep(300);
        System.out.println("Enter 3 to update a book. ");
        Thread.sleep(300);
        System.out.println("Enter 4 to display all the books. ");
        Thread.sleep(300);
        System.out.println("Enter 5 to display new arrivals. ");
        Thread.sleep(300);
        System.out.println("Enter 6 to display removed books. ");
        Thread.sleep(300);
        System.out.println("Enter 7 to request a new book. ");
        Thread.sleep(300);
        System.out.println("Enter 8 to display details of particular book. ");
        Thread.sleep(300);
        System.out.println("Enter exit to logout or end the program. ");
        Thread.sleep(300);
        System.out.println("------------------------------------------------");
        ch = sc.next();
        switch (ch) {

            case "1":
                try {
                  if (data.size() == 15) {
                FullRackException e = new FullRackException();
                     throw e;
  }

     addNewData();
     System.out.println("BOOK added!");

}
     catch (Exception e) {
         System.out.println(e.getMessage());
}
     System.out.println("------------------------------------------------");
     break;
     case "2":
        System.out.println("------------------------------------------------");
        System.out.println("Enter the book number to remove ");
        System.out.println("------------------------------------------------");
        displayList();

        System.out.println("------------------------------------------------");
        int bookNos = sc.nextInt();
        removeBook(bookNos);
        System.out.println("BOOK removed!");
        System.out.println("------------------------------------------------");
        break;
       case "3":
         System.out.println("------------------------------------------------");
         System.out.println("Enter the book number to update: ");

         System.out.println("------------------------------------------------");
         displayList();

       System.out.println("------------------------------------------------");
       int book = sc.nextInt();
       update(data.get(book - 1).title);
       System.out.println("------------------------------------------------");
       break;
       case "4":
           System.out.println("------------------------------------------------");
           System.out.println("List of available books: ");

           System.out.println("------------------------------------------------");
           displayList();
           System.out.println("------------------------------------------------");
           break;
        case "5":
           System.out.println("------------------------------------------------");
           System.out.println("Newly arrived books: ");
           System.out.println("------------------------------------------------");
           newDisplayList();
           System.out.println("------------------------------------------------");
        break;
        case "6":
           System.out.println("------------------------------------------------");
           System.out.println("Removed books: ");
           System.out.println("------------------------------------------------");
           removedBooks();
           System.out.println("------------------------------------------------");
       break;
       case "7":
           System.out.println("------------------------------------------------");
           System.out.println("Select the book number from below to request : ");
           System.out.println("------------------------------------------------");
           displayList();
      int bookNumber = sc.nextInt();
      bookNumber--;
      requestBook(bookNumber);
          break;
          case "8":
          System.out.println("------------------------------------------------");
          System.out.println("Enter the book number for complete details: ");
          System.out.println("------------------------------------------------");
          displayList();
          int bookNo = sc.nextInt();
          bookNo--;
          completeDetails(bookNo);
          System.out.println("------------------------------------------------");
          break;
  }

 }
    while (!ch.equals("exit"));
}

public static void main(String[] args) throws InterruptedException, FullRackException
{

    Library l = new Library();
    l.addToData();
    Scanner sc = new Scanner(System.in);
    System.out.println("--------LIBRARY MANAGEMENT SYSTEM-----------");
    Thread2 t = new Thread2();
    System.out.println("Enter the number of Users (upto 4): ");
    int user = sc.nextInt();
    if (user == 1) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
    try {
        executor.submit(new Thread(t));
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

   }
    catch (Exception e) {
     System.out.println("Task done.");
  }
    finally {
    System.out.println("USER LOGGED OUT");
    System.exit(0);
}
    executor.shutdownNow();

}
    else if (user == 2) {
       ExecutorService executor = Executors.newFixedThreadPool(2);

    try {
       executor.submit(new Thread(t));
       executor.shutdown();
       executor.awaitTermination(10, TimeUnit.SECONDS);

   }
    catch (Exception e) {
       System.out.println("Task done.");
  } finally
    {
      System.out.println("USER LOGGED OUT");
}
executor.shutdownNow();
System.out.println("Next user logged in..Enter start to start program");
executor = Executors.newSingleThreadExecutor();

try {
   executor.submit(new Thread(t));
   executor.shutdown();
   executor.awaitTermination(10, TimeUnit.SECONDS);
}
catch (Exception e) {
   System.out.println("Task done.");
}
finally {
   System.out.println("USER LOGGED OUT");

}
executor.shutdownNow();

} else if (user == 3) {
ExecutorService executor = Executors.newSingleThreadExecutor();

try {
executor.submit(new Thread(t));
executor.shutdown();
executor.awaitTermination(10, TimeUnit.SECONDS);

} catch (Exception e) {
System.out.println("Task done.");
} finally {
System.out.println("USER LOGGED OUT");

}
executor.shutdownNow();
System.out.println("Next user logged in..Enter start to start program");
executor = Executors.newSingleThreadExecutor();

try {
    executor.submit(new Thread(t));
    executor.shutdown();
    executor.awaitTermination(10, TimeUnit.SECONDS);

} catch (Exception e) {
    System.out.println("Task done.");
}
finally {
    System.out.println("USER LOGGED OUT");
}
executor.shutdownNow();

System.out.println("Next user logged in..Enter start to start program");
executor = Executors.newSingleThreadExecutor();

try {
   executor.submit(new Thread(t));
   executor.shutdown();
   executor.awaitTermination(10, TimeUnit.SECONDS);

}
catch (Exception e) {
   System.out.println("Task done.");
}
finally {
   System.out.println("USER LOGGED OUT");
}
executor.shutdownNow();
}
else if (user == 4) {
   ExecutorService executor = Executors.newSingleThreadExecutor();
try {
executor.submit(new Thread(t));
executor.shutdown();
executor.awaitTermination(10, TimeUnit.SECONDS);

} catch (Exception e) {
System.out.println("Task done.");
} finally {
System.out.println("USER LOGGED OUT");

}
executor.shutdownNow();
System.out.println("Next user logged in..Enter start to start program");
executor = Executors.newSingleThreadExecutor();

try {
executor.submit(new Thread(t));
executor.shutdown();
executor.awaitTermination(10, TimeUnit.SECONDS);

} catch (Exception e) {
System.out.println("Task done.");
} finally {
System.out.println("USER LOGGED OUT");
}
executor.shutdownNow();

System.out.println("Next user logged in..Enter start to start program");
executor = Executors.newSingleThreadExecutor();

try {
executor.submit(new Thread(t));
executor.shutdown();
executor.awaitTermination(10, TimeUnit.SECONDS);

} catch (Exception e) {
System.out.println("Task done.");
} finally {
System.out.println("USER LOGGED OUT");

}
executor.shutdownNow();
System.out.println("Next user logged in..Enter start to start program");
executor = Executors.newSingleThreadExecutor();

try {
executor.submit(new Thread(t));
executor.shutdown();
executor.awaitTermination(10, TimeUnit.SECONDS);

} catch (Exception e) {
System.out.println("Task done.");
} finally {
System.out.println("USER LOGGED OUT");

}
executor.shutdownNow();
} else {
System.out.println("Users cannot be more than 4");
    }
 }
}

class Thread2 extends Thread {

    public synchronized void run() {
         Library l= new Library();
         try {
           l.startLibrary();
       }
         catch (InterruptedException e) {

    }
     System.out.println("Thanks for using our library :)");
     }
}
