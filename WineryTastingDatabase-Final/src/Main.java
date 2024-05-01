import java.sql.*;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    public static void main(String[] args) {
        //connect(URL, UserName, Password)
        connect(args[0], args[1], args[2]);

        MainMenu mainMenu = new MainMenu(conn);
        mainMenu.run();

        closeResources();
    }

    private static void connect(String url, String username, String password) {
        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database");
        } catch (SQLException e) {
            System.out.println("SQL error");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static void closeResources() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error while closing resources");
            e.printStackTrace();
        }
    }
}

class MainMenu {
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private static final int MENU_OPTION_CREATE_TABLES = 0;
    private static final int MENU_OPTION_ADD_ITEM = 1;
    private static final int MENU_OPTION_EDIT_ITEM = 2;
    private static final int MENU_OPTION_LIST_ITEMS = 3;
    private static final int MENU_OPTION_SEARCH_ITEM = 4;
    private static final int MENU_OPTION_DELETE_ITEM = 5;
    private static final int MENU_OPTION_EXIT = 6;

    private static Connection conn;
    private static Scanner scanner;

    public MainMenu(Connection conn) {
        this.conn = conn;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        boolean exitMenu = false;
        while (!exitMenu) {
            displayMenu();
            int choice = getUserChoice();

            switch (choice) {
                case MENU_OPTION_CREATE_TABLES:
                    createTables();
                    break;
                case MENU_OPTION_ADD_ITEM:
                    addItem();
                    break;
                case MENU_OPTION_EDIT_ITEM:
                    EditTables();
                    break;
                case MENU_OPTION_LIST_ITEMS:
                    listTables();
                    break;
                case MENU_OPTION_SEARCH_ITEM:
                    searchItem();
                    break;
                case MENU_OPTION_DELETE_ITEM:
                    deleteItem();
                    break;
                case MENU_OPTION_EXIT:
                    exitMenu = true;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    public static void EditTables() {
        try {
            System.out.println("What table would you like to Edit");
            System.out.println("    [1] Wine");
            System.out.println("    [2] Customer");
            System.out.println("    [3] Location");
            System.out.println("    [4] Transaction");
            System.out.println("    [5] Delivery");
            System.out.println("    [6] Stock");
            System.out.println("    [7] Moved");
            System.out.println("    [8] Creates");
            System.out.println("    [9] Holds");
            Scanner s = new Scanner(System.in);
            int temp = s.nextInt();
            if (temp == 1) {
                listWine();
                System.out.println("Please enter the Varietal and Vintage you would like to update");
                System.out.println("Varietal: ");
                String Varietal = s.next();
                System.out.println("Vintage: ");
                int Vintage = s.nextInt();
                System.out.println("Please enter the new Price");
                double num = s.nextDouble();
                PreparedStatement pstmt = conn.prepareStatement("UPDATE WINE SET Price = ? WHERE WINE.Varietal = ? AND WINE.Vintage = ?");
                pstmt.setDouble(1, num);
                pstmt.setString(2, Varietal);
                pstmt.setInt(3, Vintage);
                pstmt.execute();
                System.out.println("Successfully updated Wine");
            } else if (temp == 2) {
                listCustomer();
                System.out.println("Please enter the Customer ID you would like to update");
                int id = s.nextInt();
                System.out.println("Please enter the new First Name");
                String firstName = s.nextLine();
                System.out.println("Please enter the new Last Name");
                String lastName = s.nextLine();
                PreparedStatement pstmt = conn.prepareStatement("UPDATE CUSTOMER SET FirstName = ?, LastName = ? WHERE CustomerId = ?"
                        + " values (?, ?, ?)");
                pstmt.setString(1, firstName);
                pstmt.setString(2, lastName);
                pstmt.setInt(3, id);
                pstmt.execute();
            } else if (temp == 3) {
                listLocation();
                System.out.println("Please enter the Location ID you would like to update");
                int id = s.nextInt();
                System.out.println("Please enter the updated Name of the location");
                String Name = s.nextLine();
                System.out.println("Please enter the updated Address");
                String Address = s.nextLine();
                PreparedStatement pstmt = conn.prepareStatement("UPDATE LOCATION SET Name = ?, Address = ? WHERE LocationId = ?"
                        + " values (?, ?, ?)");
                pstmt.setString(1, Name);
                pstmt.setString(2, Address);
                pstmt.setInt(3, id);
                pstmt.execute();
            } else if (temp == 4) {
                listTransactions();
                System.out.println("Please enter the Transaction ID you would like to update");
                int id = s.nextInt();
                System.out.println("Please enter the updated Date");
                String date = s.nextLine();
                System.out.println("Please enter the updated Total");
                double total = s.nextDouble();
                System.out.println("Please enter the updated CustomerID");
                int customerID = s.nextInt();
                System.out.println("Please enter the updated LocationID");
                int LocationID = s.nextInt();
                PreparedStatement pstmt = conn.prepareStatement("UPDATE TRANSACTIONS SET Date = ?, Total = ?, CustomerId = ?, LocationId = ? WHERE TransactionID = ?"
                        + " values (?, ?, ?, ?, ?)");
                pstmt.setString(1, date);
                pstmt.setDouble(2, total);
                pstmt.setInt(3, customerID);
                pstmt.setInt(4, LocationID);
                pstmt.setInt(5, id);
                pstmt.execute();
            } else if (temp == 5) {
                listDelivery();
                System.out.println("Please enter the Delivery ID you would like to update");
                int id = s.nextInt();
                System.out.println("Please enter the updated Date");
                String date = s.nextLine();
                System.out.println("Please enter the updated From_Id");
                int fromId = s.nextInt();
                System.out.println("Please enter the updated To_Id");
                int toId = s.nextInt();
                PreparedStatement pstmt = conn.prepareStatement("UPDATE DELIVERY SET Date = ?, From_Id = ?, To_Id = ? WHERE DeliveryID = ?"
                        + " values (?, ?, ?, ?)");
                pstmt.setString(1, date);
                pstmt.setInt(2, fromId);
                pstmt.setInt(3, toId);
                pstmt.setInt(4, id);
                pstmt.execute();
            } else if (temp == 6) {
                //LocationID, Varietal, Vintage, StockQty
                listStock();
                System.out.println("Please enter the Location ID you would like to update");
                int id = s.nextInt();
                System.out.println("Please enter the updated Varietal");
                String Varietal = s.nextLine();
                System.out.println("Please enter the updated Vintage");
                String Vintage = s.nextLine();
                System.out.println("Please enter the updated StockQty");
                int StockQty = s.nextInt();
                PreparedStatement pstmt = conn.prepareStatement("UPDATE STOCK SET Varietal = ?, Vintage = ?, StockQty = ? WHERE LocationID = ?"
                        + " values (?, ?, ?, ?)");
                pstmt.setString(1, Varietal);
                pstmt.setString(2, Vintage);
                pstmt.setInt(3, StockQty);
                pstmt.setInt(4, id);
                pstmt.execute();
            } else if (temp == 7) {
                //DeliveryId, Varietal, Vintage, Quantity
                listMoved();
                System.out.println("Please enter the Delivery ID you would like to update");
                int id = s.nextInt();
                System.out.println("Please enter the updated Varietal");
                String Varietal = s.nextLine();
                System.out.println("Please enter the updated Vintage");
                String Vintage = s.nextLine();
                System.out.println("Please enter the updated StockQty");
                int Quantity = s.nextInt();
                PreparedStatement pstmt = conn.prepareStatement("UPDATE MOVED SET Varietal = ?, Vintage = ?, Quantity = ? WHERE DeliveryId = ?"
                        + " values (?, ?, ?, ?)");
                pstmt.setString(1, Varietal);
                pstmt.setString(2, Vintage);
                pstmt.setInt(3, Quantity);
                pstmt.setInt(4, id);
                pstmt.execute();
            } else if (temp == 8) {
                listCreates();
                //LocationId, DeliveryId
                System.out.println("Please enter the Location ID you would like to update");
                int locationID = s.nextInt();
                System.out.println("Please enter the updated Delivery ID");
                int deliveryID = s.nextInt();
                PreparedStatement pstmt = conn.prepareStatement("UPDATE CREATES SET DeliveryId WHERE LocationId = ?"
                        + " values (?, ?)");
                pstmt.setInt(1, deliveryID);
                pstmt.setInt(2, locationID);
                pstmt.execute();
            } else if (temp == 9) {
                listHolds();
                //HoldId, Varietal, Vintage, Qty
                System.out.println("Please enter the Hold ID you would like to update");
                int id = s.nextInt();
                System.out.println("Please enter the updated Varietal");
                String Varietal = s.nextLine();
                System.out.println("Please enter the updated Vintage");
                String Vintage = s.nextLine();
                System.out.println("Please enter the updated Qty");
                int Quantity = s.nextInt();
                PreparedStatement pstmt = conn.prepareStatement("UPDATE HOLDS SET Varietal = ?, Vintage = ?, Qty = ? WHERE HoldId = ?"
                        + " values (?, ?, ?, ?)");
                pstmt.setString(1, Varietal);
                pstmt.setString(2, Vintage);
                pstmt.setInt(3, Quantity);
                pstmt.setInt(4, id);
                pstmt.execute();
            } else {
                System.out.println("Not a Valid option, Please try again if you would like.");
            }
        } catch (SQLException e) {
            System.out.println("SQL error");
            e.printStackTrace();
        }
    }

    public static void listTables() {
        try {
            System.out.println("What table would you like Listed");
            System.out.println("    [1] Wine");
            System.out.println("    [2] Customer");
            System.out.println("    [3] Location");
            System.out.println("    [4] Transaction");
            System.out.println("    [5] Delivery");
            System.out.println("    [6] Stock");
            System.out.println("    [7] Moved");
            System.out.println("    [8] Creates");
            System.out.println("    [9] Holds");
            Scanner s = new Scanner(System.in);
            int temp = s.nextInt();
            if (temp == 1) {
                listWine();
            } else if (temp == 2) {
                listCustomer();
            } else if (temp == 3) {
                listLocation();
            } else if (temp == 4) {
                listTransactions();
            } else if (temp == 5) {
                listDelivery();
            } else if (temp == 6) {
                listStock();
            } else if (temp == 7) {
                listMoved();
            } else if (temp == 8) {
                listCreates();
            } else if (temp == 9) {
                listHolds();
            } else {
                System.out.println(temp + " is not a option");
            }
            System.out.println();
        } catch (SQLException e) {
            System.out.println("SQL error");
            e.printStackTrace();
        }
    }

    public static void listWine() throws SQLException{
        //Varietal, Vintage, Price
        System.out.println("WINE");
        stmt = conn.createStatement();
        rs = stmt.executeQuery("Select * from WINE");
        while (rs.next()) {
            System.out.print(rs.getString(1) + "\t");
            System.out.print(rs.getInt(2) + "\t");
            System.out.println(rs.getDouble(3));
        }
    }
    public static void listCustomer() throws SQLException {
        //CustomerId, FirstName, LastName
        System.out.println("CUSTOMER");
        stmt = conn.createStatement();
        rs = stmt.executeQuery("Select * from CUSTOMER");
        while (rs.next()) {
            System.out.print(rs.getInt(1) + "\t");
            System.out.print(rs.getString(2) + "\t");
            System.out.println(rs.getString(3));
        }
    }

    public static void listLocation() throws SQLException {
        //LocationID, Name, Address
        System.out.println("LOCATIONS");
        stmt = conn.createStatement();
        rs = stmt.executeQuery("Select * from LOCATION");
        while (rs.next()) {
            System.out.print(rs.getInt(1) + "\t");
            System.out.print(rs.getString(2) + "\t");
            System.out.println(rs.getString(3));
        }
    }
    public static void listTransactions() throws SQLException {
        //TransactionID, Date, Total, CustomerId, LocationId
        System.out.println("TRANSACTIONS");
        stmt = conn.createStatement();
        rs = stmt.executeQuery("Select * from TRANSACTIONS");
        while (rs.next()) {
            System.out.print(rs.getInt(1) + "\t");
            System.out.print(rs.getString(2) + "\t");
            System.out.print(rs.getInt(3) + "\t");
            System.out.print(rs.getInt(4) + "\t");
            System.out.println(rs.getInt(5));
        }
    }
    public static void listDelivery() throws SQLException {
        //DeliveryID, Date, From_Id, To_Id
        System.out.println("DELIVERY");
        stmt = conn.createStatement();
        rs = stmt.executeQuery("Select * from DELIVERY");
        while (rs.next()) {
            System.out.print(rs.getInt(1) + "\t");
            System.out.print(rs.getString(2) + "\t");
            System.out.print(rs.getInt(3) + "\t");
            System.out.println(rs.getInt(4));
        }
    }
    public static void listStock() throws SQLException {
        //LocationID, Varietal, Vintage, StockQty
        System.out.println("STOCK");
        stmt = conn.createStatement();
        rs = stmt.executeQuery("Select * from STOCK");
        while (rs.next()) {
            System.out.print(rs.getInt(1) + "\t");
            System.out.print(rs.getString(2) + "\t");
            System.out.print(rs.getInt(3) + "\t");
            System.out.println(rs.getInt(4));
        }
    }
    public static void listMoved() throws SQLException {
        //DeliveryId, Varietal, Vintage, Quantity
        System.out.println("MOVED");
        stmt = conn.createStatement();
        rs = stmt.executeQuery("Select * from MOVED");
        while (rs.next()) {
            System.out.print(rs.getInt(1) + "\t");
            System.out.print(rs.getString(2) + "\t");
            System.out.print(rs.getInt(3) + "\t");
            System.out.println(rs.getInt(4));
        }
    }
    public static void listCreates() throws SQLException {
        //LocationId, DeliveryId
        System.out.println("CREATES");
        stmt = conn.createStatement();
        rs = stmt.executeQuery("Select * from CREATES");
        while (rs.next()) {
            System.out.print(rs.getInt(1) + "\t");
            System.out.println(rs.getInt(2));
        }
    }
    public static void listHolds() throws SQLException {
        //HoldId, Varietal, Vintage, Qty
        System.out.println("HOLDS");
        stmt = conn.createStatement();
        rs = stmt.executeQuery("Select * from HOLDS");
        while (rs.next()) {
            System.out.print(rs.getInt(1) + "\t");
            System.out.print(rs.getString(2) + "\t");
            System.out.print(rs.getInt(3) + "\t");
            System.out.println(rs.getInt(4));
        }
    }
    public static void searchItem() {
        System.out.println("Please enter the SELECT statement you want to search the database for. (Ex. 'SELECT * FROM WINE' will return all of the values in the WINE Table)");
        System.out.println("List of available tables: WINE, CUSTOMER, LOCATION, TRANSACTION, DELIVERY, STOCK, MOVED, CREATES, HOLD.");

        //Get Entered Select Statement
        String searchQuery = scanner.nextLine();

        //Check that the first word in the statement is "SELECT"
        int n = searchQuery.indexOf(' ');
        String selectCheck = searchQuery.substring(0,n);

        //If the first word is not SELECT then the user is not entering a SELECT statement so they shouldnt be in the search function

            //Else run the Select Query
            Statement stmt = null;
            ResultSet rs = null;
            try {
                //Create the statement
                stmt = conn.createStatement();

                //Create the result set and metadata
                rs = stmt.executeQuery(searchQuery);

                ResultSetMetaData metaData = rs.getMetaData();

                //Get the Column headers
                int numColumns = metaData.getColumnCount();
                for (int i = 1; i <= numColumns; i++) //May need Curly Braces here
                    System.out.print(metaData.getColumnLabel(i) + "\t");
                System.out.println("");

                //Print the results
                Object obj = null;
                while (rs.next()) { //while there is another item in the result set
                    for (int i = 1; i <= numColumns; i++) {
                        obj = rs.getObject(i); //get the item
                        if(obj != null) { //if its not empty
                            System.out.print(rs.getObject(i).toString() + "\t"); //print the item
                        } else {
                            System.out.print("\t\t"); //else print out some tabs
                        }
                    }
                    System.out.println("");
                }

            } catch (SQLException e) { //catch the sql exception
                System.out.println("SQL error. Please double check your SELECT statement for valid tables and keys.");
                e.printStackTrace();
            }
        }


    private void displayMenu() {
        System.out.println("Please choose what you would like to do: ");
        System.out.println("    [" + MENU_OPTION_CREATE_TABLES + "]  Create Tables");
        System.out.println("    [" + MENU_OPTION_ADD_ITEM + "]  Add an Item");
        System.out.println("    [" + MENU_OPTION_EDIT_ITEM + "]  Edit");
        System.out.println("    [" + MENU_OPTION_LIST_ITEMS + "]  List");
        System.out.println("    [" + MENU_OPTION_SEARCH_ITEM + "]  Search for an Item");
        System.out.println("    [" + MENU_OPTION_DELETE_ITEM + "]  Delete an Item");
        System.out.println("    [" + MENU_OPTION_EXIT + "]  Exit");
        System.out.println();
    }

    private int getUserChoice() {
        System.out.print("Enter your choice: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void createTables() {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE ...");
            System.out.println("Tables created successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to create tables.");
            e.printStackTrace();
        }
    }



    public static void deleteItem() {
        System.out.println("Enter the table name you wish to edit: ");
        System.out.println("List of available tables: WINE, CUSTOMER, LOCATION, TRANSACTIONS, DELIVERY, STOCK, MOVED, CREATES, HOLD.");
        String tableName = scanner.nextLine();

        String[] possibleTables = {"WINE", "CUSTOMER", "LOCATION", "TRANSACTIONS", "DELIVERY", "STOCK", "MOVED", "CREATES", "HOLD"};

        boolean isValidTable = false;
        for (String table : possibleTables) {
            if (table.equalsIgnoreCase(tableName)) {
                isValidTable = true;
                break;
            }
        }

        if (!isValidTable) {
            System.out.println("Invalid table name. Please enter a valid table name.");
            return;
        }

        System.out.println("Enter the condition for deletion (e.g., WHERE column_name = value): ");
        String condition = scanner.nextLine();

        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            String deleteQuery = "DELETE FROM " + tableName + " " + condition;
            int rowsAffected = stmt.executeUpdate(deleteQuery);
            System.out.println(rowsAffected + " rows deleted successfully.");
        } catch (SQLException e) {
            System.out.println("SQL error");
            e.printStackTrace();
        }
    }
    public static void addItem() {
        int selection = -1;

        while (selection < 0 || selection > 9) {
            //Ask user to select a table
            System.out.println("Select a table for inserting values:");
            System.out.println("    [0] Wine");
            System.out.println("    [1] Customer");
            System.out.println("    [2] Location");
            System.out.println("    [3] Transactions");
            System.out.println("    [4] Delivery");
            System.out.println("    [5] Stock");
            System.out.println("    [6] Moved");
            System.out.println("    [7] Creates");
            System.out.println("    [8] Hold");
            System.out.println("    [9] Cancel");

            Scanner scanner = new Scanner(System.in);
            selection = scanner.nextInt();

            if (selection < 0 || selection > 9) {
                System.out.println("Please make a valid selection");
            }
        }

        if (selection == 9) {
            return;
        }
        //Create 2D Array with tables and their respective attribute names
        String[][] tablesArray = new String[9][];

        tablesArray[0] = new String[5]; //Wine
        tablesArray[1] = new String[5]; //Customer
        tablesArray[2] = new String[5]; //Location
        tablesArray[3] = new String[7]; //Transactions
        tablesArray[4] = new String[6]; //Delivery
        tablesArray[5] = new String[6]; //Stock
        tablesArray[6] = new String[6]; //Moved
        tablesArray[7] = new String[4]; //Creates
        tablesArray[8] = new String[6]; //Hold

        tablesArray[0][0] = "Wine";
        tablesArray[0][1] = "(?, ?, ?)";
        tablesArray[0][2] = "Varietal";
        tablesArray[0][3] = "Vintage";
        tablesArray[0][4] = "Price";

        tablesArray[1][0] = "Customer";
        tablesArray[1][1] = "(?, ?, ?)";
        tablesArray[1][2] = "CustomerId";
        tablesArray[1][3] = "FirstName";
        tablesArray[1][4] = "LastName";

        tablesArray[2][0] = "Location";
        tablesArray[2][1] = "(?, ?, ?)";
        tablesArray[2][2] = "LocationId";
        tablesArray[2][3] = "Name";
        tablesArray[2][4] = "Address";

        tablesArray[3][0] = "Transactions";
        tablesArray[3][1] = "(?, ?, ?, ?, ?)";
        tablesArray[3][2] = "TransactionId";
        tablesArray[3][3] = "Date";
        tablesArray[3][4] = "Total";
        tablesArray[3][5] = "CustomerId";
        tablesArray[3][6] = "LocationId";

        tablesArray[4][0] = "Delivery";
        tablesArray[4][1] = "(?, ?, ?, ?)";
        tablesArray[4][2] = "DeliveryId";
        tablesArray[4][3] = "Date";
        tablesArray[4][4] = "From_Id";
        tablesArray[4][5] = "To_Id";

        tablesArray[5][0] = "Stock";
        tablesArray[5][1] = "(?, ?, ?, ?)";
        tablesArray[5][2] = "LocationId";
        tablesArray[5][3] = "Varietal";
        tablesArray[5][4] = "Vintage";
        tablesArray[5][5] = "StockQty";

        tablesArray[6][0] = "Moved";
        tablesArray[6][1] = "(?, ?, ?, ?)";
        tablesArray[6][2] = "DeliveryId";
        tablesArray[6][3] = "Varietal";
        tablesArray[6][4] = "Vintage";
        tablesArray[6][5] = "Quantity";

        tablesArray[7][0] = "Creates";
        tablesArray[7][1] = "(?, ?)";
        tablesArray[7][2] = "LocationId";
        tablesArray[7][3] = "DeliveryId";

        tablesArray[8][0] = "Hold";
        tablesArray[8][1] = "(?, ?, ?, ?)";
        tablesArray[8][2] = "HoldId";
        tablesArray[8][3] = "Varietal";
        tablesArray[8][4] = "Vintage";
        tablesArray[8][5] = "Qty";

        //Metadata for attribute data types in each table
        //1 = String
        //2 = Int
        //3 = Float
        //4 = Date
        int[][] dataTypes = {
                {1, 2, 3}, //Wine
                {2, 1, 1}, //Customer
                {2, 1, 1}, //Location
                {2, 4, 3, 2, 2}, //Transactions
                {2, 4, 2, 2}, //Delivery
                {2, 1, 2, 2}, //Stock
                {2, 1, 2, 2}, //Moved
                {2, 2}, //Creates
                {2, 1, 2, 2} //Hold
        };

        //Ask for parameters
        Scanner valueScanner = new Scanner(System.in);
        LinkedList<String> valueList = new LinkedList<>();
        //We start at index 2 in tablesArray because that's where attributes start
        for (int i = 2; i < tablesArray[selection].length; i++) {
            System.out.println("Please enter a value to insert for " + tablesArray[selection][i] + ":");
            //Additional format guideline for date is provided for the user
            if (dataTypes[selection][i - 2] == 4) {
                System.out.println("Use the format: \"yyyy-mm-dd\"");
            }
            valueList.add(valueScanner.next());
        }

        PreparedStatement ps = null;

        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement("INSERT INTO " + tablesArray[selection][0] + " VALUES " + tablesArray[selection][1]);
            for (int i = 0; i < dataTypes[selection].length; i++) {
                int currParameter = i + 1;

                switch (dataTypes[selection][i]) {
                    case 1:
                        ps.setString(currParameter, valueList.get(i));
                        break;
                    case 2:
                        ps.setInt(currParameter, Integer.parseInt(valueList.get(i)));
                        break;
                    case 3:
                        ps.setFloat(currParameter, Float.parseFloat(valueList.get(i)));
                        break;
                    case 4:
                        ps.setDate(currParameter, java.sql.Date.valueOf(valueList.get(i)));
                        break;
                }
            }
            //Commit changes
            if (ps.executeUpdate() > 0) {
                System.out.println("Insert Operation was Successful");
            }
            ps.clearParameters();
            conn.commit();

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
