/*Core Java Mini Project Name As Publisher Royalty.*/

//Importing the classes & Interface from java package
import java.sql.*;
//Importing the classes  sql from java package
import java.util.Scanner;

// Base class (book)
class book {
    // Taking input from the user.
    Scanner sc = new Scanner(System.in);

    // Class Instances
    String title, author, publisher;
    int price, Quantity, TotalP;
    double Authors_Royalty;

    // Making classs methods as Insert_Book_Details
    public void Insert_Book_Details() {
        System.out.print("Enter Title Of The Book : ");
        title = sc.nextLine();
        System.out.print("Enter Name Of The Author : ");
        author = sc.nextLine();
        System.out.print("Enter Name Of The Publisher : ");
        publisher = sc.nextLine();
        System.out.print("Enter Price Of The Book : ");
        price = sc.nextInt();
    }

    // Making class methods as Retrieve_User_From_Details()
    public void Retrieve_User_From_Details() {
        System.out.print("Enter Title Of The Book : ");
        title = sc.nextLine();
        System.out.print("Enter Quantity Of The Book : ");
        Quantity = sc.nextInt();
    }

    // Making class methods as Retrieve_Price_From_User()
    public void Retrieve_Price_From_User() {
        System.out.print("Enter Price (in Rs.): ");
        price = sc.nextInt();
        System.out.print("Enter Quantity: ");
        Quantity = sc.nextInt();
    }

    // Object class methods as return integer,& Calcualte_Price()
    public int Calculate_Price() {
        TotalP = price * Quantity;
        return TotalP;
    }

    // Object class methods as no return, Calculate_Royalty()
    public void Calculate_Royalty() {
        if (Quantity < 500) {
            System.out.println("No Royality to author");
        } else if (Quantity == 500) {
            Authors_Royalty = (TotalP * 10) / 100;
        } else if (Quantity > 500 && Quantity <= 1000) {
            Authors_Royalty = (TotalP * 12.5) / 100;
        } else if (Quantity > 1000) {
            Authors_Royalty = (TotalP * 15) / 100;
        }

        System.out.println("Author Royalty In Rs. : " + Authors_Royalty);
    }
}

// Child class (ebook)
class ebook extends book {
    String EPUB, PDF, MOBI;
}

// Main Executable class (App)
public class Publisher_Royalty {
    Scanner sc = new Scanner(System.in);

    /*
     * Print_All_Data() is a method used to establish,
     * a connection between Java application & database.
     * It's print all data recorded inside book bank.
     */
    public static void Print_All_Data() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root",
                    "9968075985");
            // javaproject is database name, root is username and 9968075985 is password.
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from books");
            while (rs.next())
                System.out.println(
                        rs.getString(1) + " | " + rs.getString(2) + " | " + rs.getString(3) + " | " + rs.getInt(4));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /*
     * Insert_Books_Data() is a method used to establish ,
     * a connection between Java application & database.
     * It's print insert data inside the book bank.
     */
    public static void Insert_Books_Data(String Title, String Author, String Publisher, int Price) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root",
                    "9968075985");
            // javaproject is database name, root is username and 996807985 is password
            String sql = "Insert into books" + "(Title,Author,Publisher,Price)" + "values(?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, Title);
            stmt.setString(2, Author);
            stmt.setString(3, Publisher);
            stmt.setInt(4, Price);
            stmt.executeUpdate();
            System.out.println("Information Successfully Added !!!");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /*
     * Fetch_Price_Data() is a method used to establish,
     * a connection between Javaapplication & database.
     * It's print fetch price data inside the books bank.
     */
    public static void Fetch_Price_Data(String Title) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root",
                    "9968075985");
            // javaproject is database name, root is username and 996807985 is password.
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select Price From Books Where Title = '" + Title + "' ");
            while (rs.next())
                System.out.println("Price Of The " + Title + " is: " + +rs.getInt(1));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Main execulatble statements begin from here.
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Created an instance of book class as 's'
        book s = new book();
        System.out.println("\n<-------------------- Welcome to Joshi Brother's Book Store -------------------->\n");
        System.out.println("0. Exit");
        System.out.println("1. Add Book In Section");
        System.out.println("2. User Purchase Fetch Details");
        System.out.println("3. Total Amount");
        System.out.println("4. Explore Avaliable books \n");
        System.out.println("Choose Option From The Above:  ");
        int input = sc.nextInt();
        if (input == 1) {
            s.Insert_Book_Details();
            Insert_Books_Data(s.title, s.author, s.publisher, s.price);
        } else if (input == 2) {
            s.Retrieve_User_From_Details();
            Fetch_Price_Data(s.title);
        } else if (input == 3) {
            s.Calculate_Price();
            System.out.println("Total Price of Purchased Books are: " + s.Calculate_Price());
            s.Calculate_Royalty();
        } else if (input == 4) {
            System.out.println("|    TITLE   |   AUTHOR  |     PUBLISHER    |   PRICE   |");
            Print_All_Data();
        } else if (input == 0) {
            System.out.println("Thanks For Purchasing Book,Revisit Again");
        } else if (input != 0 || input != 1 || input != 2 || input != 3 || input != 4) {
            System.out.println("Please Take Right Input");
        }
        sc.close();
    }
}


Designed & Developed 
Sushant Joshi