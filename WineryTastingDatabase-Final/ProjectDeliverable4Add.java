package projectdeliverable4add;

import java.sql.*;
import java.util.Scanner;
import java.util.LinkedList;

public class ProjectDeliverable4Add {

    static ResultSet rs = null;
    static Statement stmt = null;
    static Connection conn = null;

    public static void main(String[] args) {
        Scanner S = new Scanner(System.in);
        System.out.println("Please choose what you would like to do: ");
        System.out.println("    [1]  Add an Item");
        System.out.println("    [2]  Delete an Item");
        System.out.println("    [3]  ");
        System.out.println();
        add();
    }

    public static void connect() {
        //String url = "jdbc:mysql://localhost:3306/jdbclab";
        //String username = "root";
        //String password = "Magyarian95!";
        //String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/winery3";
        String username = "root";
        String password = "Mysqlpassword1!";
        String driver = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(driver);//I added this line to the connect method -Rodrigo
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database");
        } catch (Exception e) { //I changed the exception to be a general one
            System.out.println("SQL error");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void add() {
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
        connect();

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
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void createTables() {

    }

    public static void insertDummyData() {

    }

}
