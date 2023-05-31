import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Retailio represents a retail management system commonly used in retail
 * businesses to manage employees and inventory of the store. It contains a
 * main method and several helper methods to navigate through the menues. All
 * methods are private as this is a fully CLI oriented program, and will not be
 * used or constructed in any outside drivers.
 * 
 * This program is currently a living document that is still in progress.
 * 
 * Future iterations will include encryption of txt files so that they are not
 * accessible outside of this program, further security for stored objects,
 * optimized methods to improve time complexity, and potentially POS style
 * functionality.
 * 
 * @author Ayleen Piteo-Tarpy
 * @version 5.29.23
 */
public class Retailio {
    private static Scanner keyboard = new Scanner(System.in);
    private static ArrayList<Manager> managers   = new ArrayList<>();
    private static ArrayList<ShiftLead> leads    = new ArrayList<>();
    private static ArrayList<TeamMember> crew    = new ArrayList<>();
    private static ArrayList<SaleItem> inventory = new ArrayList<>();
    private static String masterKey;

    public static void main(String[] args) {
        setMasterKey();
        loadData();
        System.out.println("\n**************** WELCOME ****************");
        System.out.println("Please begin by making a selection below.");
        int input = 99;

        while (input != 0) {
            System.out.println("*****************************************");
            System.out.println("* 1. Manage employees                   *");
            System.out.println("* 2. Manage inventory                   *");
            System.out.println("* 0. Exit                               *");
            System.out.println("*****************************************");
            input = Integer.valueOf(keyboard.nextLine());
            switch (input) {
                case 1:
                    manageEmployees();
                    break;
                case 2:
                    manageInventory();
                    break;
                case 0:
                    saveData();
                    System.out.println("**************** GOODBYE "
                    + "****************");
                    return;
                default:
                    System.out.println("Invalid input. Please try again");
            }
        }
    }

    /**
     * View available items in inventory
     */
    private static void viewAvailableItems() {
        if (inventory.isEmpty()) {
            System.out.println("No items available in inventory.");
        } else {
            System.out.println("Available Items:");
            for (int i = 0; i < inventory.size(); i++) {
                SaleItem item = inventory.get(i);
                System.out.println((i + 1) + ": " + item.getName()
                + " - Price: " + item.getPrice()
                + " - Stock: " + item.getStock());
            }
        }
    }

    /*
     * Manage inventory menu
     */
    private static void manageInventory() {
        int input = 99;
        while (input != 0) {
            System.out.println("*****************************************");
            System.out.println("* 1. View inventory                     *");
            System.out.println("* 2. Update item price                  *");
            System.out.println("* 3. Update item stock                  *");
            System.out.println("* 4. Add item to inventory              *");
            System.out.println("* 5. Remove item from inventory         *");
            System.out.println("* 0. Go back                            *");
            System.out.println("*****************************************");
            input = Integer.valueOf(keyboard.nextLine());
            switch (input) {
                case 1:
                    viewAvailableItems();
                    break;
                case 2:
                    updateItemPrice();
                    break;
                case 3:
                    updateItemStock();
                    break;
                case 4:
                    addItemToInventory();
                    break;
                case 5:
                    removeItemFromInventory();
                    break;
                case 0:
                    System.out.println("Returning to main menu");
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
                    manageInventory();
                    return;
            }
        }
    }

    /*
     * Adds an item to inventory according to user specifications
     */
    private static void addItemToInventory() {
        System.out.println("Please enter your master key:");
        if((keyboard.nextLine()).equals(masterKey)) {
            System.out.println("Enter the name of the item:");
            String name = keyboard.nextLine();
            System.out.println("Enter the price of the item:");
            double price = Double.parseDouble(keyboard.nextLine());
            System.out.println("Enter the stock of the item:");
            int stock = Integer.parseInt(keyboard.nextLine());
            SaleItem item = new SaleItem(name, price, stock);
            inventory.add(item);
            System.out.println("Item added to inventory successfully:");
            System.out.println("Name: " + item.getName());
            System.out.println("Price: $" + item.getPrice());
            System.out.println("Stock: " + item.getStock());
        } else {
            System.out.println("Access Denied");
        }
    }

    /*
     * Updates item price in inventory according to user specifications
     */
    public static void updateItemPrice() {
        System.out.println("Please enter your master key:");
        if((keyboard.nextLine()).equals(masterKey)) {
            if (inventory.isEmpty()) {
                System.out.println("No items available in inventory.");
            } else {
                System.out.println("Enter the name of the item to update:");
                String name = keyboard.nextLine();
                for(SaleItem item : inventory) {
                    if((item.getName()).equals(name)){
                        System.out.println("Enter the new price:");
                        double price = Double.valueOf(keyboard.nextLine());
                        item.setPrice(price);
                        System.out.println("Item price updated successfully:");
                        System.out.println("Name: " + item.getName());
                        System.out.println("Price: $" + item.getPrice());
                        System.out.println("Stock: " + item.getStock());
                        return;
                    }
                }
                System.out.println("Item not found in inventory.");
                return;
            }
        } else {
            System.out.println("Access Denied");
        }
    }

    /*
     * Updates item stock in inventory according to user specifications
     */
    public static void updateItemStock() {
        System.out.println("Please enter your master key:");
        if((keyboard.nextLine()).equals(masterKey)) {
            if (inventory.isEmpty()) {
                System.out.println("No items available in inventory.");
            } else {
                System.out.println("Enter the name of the item to update:");
                String name = keyboard.nextLine();
                for(SaleItem item : inventory) {
                    if((item.getName()).equals(name)){
                        System.out.println("Enter the new stock count:");
                        int stock = Integer.valueOf(keyboard.nextLine());
                        item.updateStock(stock);
                        System.out.println("Item stock updated successfully:");
                        System.out.println("Name: " + item.getName());
                        System.out.println("Price: $" + item.getPrice());
                        System.out.println("Stock: " + item.getStock());
                    }
                }
                System.out.println("Item not found in inventory.");
                return;
            }
        } else {
            System.out.println("Access Denied");
        }
    }

    /*
     * Removes an item from inventory according to user specifications
     */
    public static void removeItemFromInventory() {
        System.out.println("Please enter your master key:");
        if((keyboard.nextLine()).equals(masterKey)) {
            if (inventory.isEmpty()) {
                System.out.println("No items available in inventory.");
            } else {
                System.out.println("Enter the name of the item to remove:");
                String name = keyboard.nextLine();
                for(SaleItem item : inventory) {
                    if((item.getName()).equals(name)){
                        inventory.remove(item);
                        System.out.println(item.getName()
                        + " removed from inventory successfully.");
                    }
                }
                System.out.println("Item not found in inventory.");
                return;
            }
        } else {
            System.out.println("Access Denied");
        }
    }

    /*
     * Allows user to make choices regarding employee management
     */
    private static void manageEmployees() {
        int input = 99;
        while (input != 0) {
            System.out.println("*****************************************");
            System.out.println("* 1. View all employees                 *");
            System.out.println("* 2. Promote employees                  *");
            System.out.println("* 3. Discharge employees                *");
            System.out.println("* 4. Make new hire account              *");
            System.out.println("* 5. Make changes to employee accounts  *");
            System.out.println("* 0. Go back                            *");
            System.out.println("*****************************************");
            input = Integer.parseInt(keyboard.nextLine());
            switch (input) {
                case 1:
                    employeeListMenu();
                    break;
                case 2:
                    promotionMenu();
                    break;
                case 3:
                    dischargeMenu();
                case 4:
                    newHireMenu();
                    break;
                case 5:
                    updateEmployees();
                case 0:
                    System.out.println("Returning to the main menu");
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        }
    }

    /*
     * Menu to choose which kinds of employees to view in a list, will print
     * names and IDs of all members for each chosen type
     */
    private static void employeeListMenu() {
        int input = 99;
        while (input != 0) {
            System.out.println("*****************************************");
            System.out.println("* 1. View all managers                  *");
            System.out.println("* 2. View all shift leads               *");
            System.out.println("* 3. View all team members              *");
            System.out.println("* 4. View all employees                 *");
            System.out.println("* 0. Go back                            *");
            System.out.println("*****************************************");
            input = Integer.parseInt(keyboard.nextLine());
            switch (input) {
                case 1:
                    if (managers.size() == 0) {
                        System.out.println("You currently have no managers.");
                    } else {
                        for (int i = 0; i < managers.size(); i++) {
                            System.out.println((i + 1) + ": "
                            + managers.get(i).getName()
                            + " - ID: " + managers.get(i).getID());
                        }
                    }
                    break;
                case 2:
                    if (leads.size() == 0) {
                        System.out.println("You currently have no shift leads.");
                    } else {
                        for (int i = 0; i < leads.size(); i++) {
                            System.out.println((i + 1) + ": "
                            + leads.get(i).getName()
                            + " - ID: " + leads.get(i).getID());
                        }
                    }
                    break;
                case 3:
                    if (crew.size() == 0) {
                        System.out.println("You currently have no team members.");
                    } else {
                        for (int i = 0; i < crew.size(); i++) {
                            System.out.println((i + 1) + ": "
                            + crew.get(i).getName()
                            + " - ID: " + crew.get(i).getID());
                        }
                    }
                    break;
                case 4:
                    if((managers.size() == 0) && (leads.size() == 0)
                    && (crew.size() == 0)){
                        System.out.println("You currently have no employees.");
                    }
                    int count = 1;
                    for(int i = 0; i < managers.size(); i++){
                        System.out.println(count + ": "
                        + "Manager - " + managers.get(i).getName() + " - ID: "
                        + managers.get(i).getID());
                        count ++;
                    }
                    for(int i = 0; i < leads.size(); i++){
                        System.out.println(count + ": "
                        + "Shift lead - " + leads.get(i).getName() + " - ID: "
                        + leads.get(i).getID());
                        count++;
                    }
                    for(int i = 0; i < crew.size(); i++){
                        System.out.println(count + ": "
                        + "Team member - " + crew.get(i).getName() + " - ID: "
                        + crew.get(i).getID());
                        count++;
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        }
    }

    /*
     * Promotes an employee according to user specifications
     */
    private static void promotionMenu() {
        System.out.println("Please enter the master key to access this menu:");
        String key = keyboard.nextLine();

        if(!key.equals(masterKey)) {
            System.out.println("Access Denied");
            return;
        }

        System.out.println("Please enter the employee ID of the employee you "
        + "would like to promote:");
        String input = keyboard.nextLine();

        // searches through managers to find entered employee ID
        for(Manager manager : managers) {
            if (manager.getID().equals(input)) {
                System.out.println(manager.getName()
                + " is a manager and can not be promoted further.");
                return;
            }
        }

        // searches through leads to find entered employee ID
        for(ShiftLead lead : leads) {
            if (lead.getID().equals(input)) {
                managers.add(new Manager(lead));
                leads.remove(lead);
                System.out.println(lead.getName()
                + " promoted to Manager.");
                return;
            }
        }

        // searches through crew to find entered employee ID
        for(TeamMember teamMember: crew) {
            if (teamMember.getID().equals(input)) {
                leads.add(new ShiftLead(teamMember));
                crew.remove(teamMember);
                System.out.println(teamMember.getName()
                + " promoted to Shift Lead.");
                return;
            }
        }

        // prints if employee was not located
        System.out.println("No employee found with the given ID.");
    }

    /*
     * Discharges an employee according to user specifications
     */
    private static void dischargeMenu() {
        System.out.println("Please enter the master key to access this menu:");
        String key = keyboard.nextLine();

        if(!key.equals(masterKey)) {
            System.out.println("Access Denied");
            return;
        }

        System.out.println("Please enter the employee ID of the employee you "
        + "would like to discharge:");
        String input = keyboard.nextLine();

        // searches through managers to find entered employee ID
        for(Manager manager : managers) {
            if (manager.getID().equals(input)) {
                managers.remove(manager);
                System.out.println(manager.getName()
                + " has been removed from your system.");
                return;
            }
        }

        // searches through leads to find entered employee ID
        for(ShiftLead lead : leads) {
            if (lead.getID().equals(input)) {
                leads.remove(lead);
                System.out.println(lead.getName()
                + " has been removed from your system.");
                return;
            }
        }

        // searches through crew to find entered employee ID
        for(TeamMember teamMember: crew) {
            if (teamMember.getID().equals(input)) {
                crew.remove(teamMember);
                System.out.println(teamMember.getName()
                + " has been removed from your system.");
                return;
            }
        }

        // prints if employee was not located
        System.out.println("No employee found with the given ID.");
    }

    /*
     * Menu to choose the type of employee to be constructed,
     * according to user specifications
     */
    private static void newHireMenu() {
        System.out.println("*****************************************");
        System.out.println("* 1. Add new manager.                   *");
        System.out.println("* 2. Add new shift lead.                *");
        System.out.println("* 3. Add new team member.               *");
        System.out.println("* 0. Go back                            *");
        System.out.println("*****************************************");
        int choice = Integer.parseInt(keyboard.nextLine());
        if (choice >= 1 && choice <= 3) {
            Employee temp = new Employee("first", "last", "123456", 0);
            employeeBuilder(temp);
            switch (choice) {
                case 1:
                    Manager m = new Manager(temp);
                    managers.add(m);
                    System.out.println("Your new manager's employee ID is "
                    + temp.getID());
                    break;
                case 2:
                    ShiftLead s = new ShiftLead(temp);
                    leads.add(s);
                    System.out.println("Your new shift lead's employee ID is "
                    + temp.getID());
                    break;
                case 3:
                    TeamMember c = new TeamMember(temp);
                    crew.add(c);
                    System.out.println("Your new team member's employee ID is "
                    + temp.getID());
                    break;
            }
        } else if (choice == 0) {
            return;
        } else {
            System.out.println("Invalid value entered. Please try again.");
            newHireMenu();
        }
    }

    /*
     * allows access to update names and/or passwords of employees
     */
    private static void updateEmployees() {
        Employee temp;
        System.out.println("Please enter your password or master key:");
        String password = keyboard.nextLine();

        // allows access to all employees if masterKey is entered
        if(password.equals(masterKey)) {
            System.out.println("Please enter the employee ID of the employee "
            + "whose information you would like to update:");
            temp = getEmployeeWithID(keyboard.nextLine());

        // allows access to one employee if that employees password is entered
        } else {
            temp = getEmployeeWithPW(password);
        }

        if(temp == null){
            System.out.println("No employee found with the given information.");
            return;
        }
        System.out.println("Accessing employee " + temp.getName());
        int input = 99;
        while (input != 0) {
            System.out.println("*****************************************");
            System.out.println("* 1. View employee password             *");
            System.out.println("* 2. Update employee first name         *");
            System.out.println("* 3. Update employee last name          *");
            System.out.println("* 4. Update employee password           *");
            System.out.println("* 0. Go back                            *");
            System.out.println("*****************************************");
            input = Integer.parseInt(keyboard.nextLine());
            switch(input) {
                case 1:
                    System.out.println(temp.getName() 
                    + "'s password is: " + temp.getPassword());
                    break;
                case 2:
                    setFirstName(temp, temp.getPassword());
                    System.out.println("Name succesfully updated to "
                    + temp.getName() + ".");
                    break;
                case 3:
                    setLastName(temp, temp.getPassword());
                    System.out.println("Name succesfully updated to "
                    + temp.getName() + ".");
                    break;
                case 4:
                    setPassword(temp, temp.getPassword());
                    System.out.println("Password succesfully updated.");
                    break;
                case 0:
                    System.out.println("Returning to the main menu");
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        }
    }

    /*
     * Updates the first name of the argument Employee to user specifications
     */
    private static void setFirstName(Employee temp, String pw) {
        boolean flag;
        do {
            try {
                System.out.print("Please enter the employee's first name: ");
                temp.setFirstName(pw, keyboard.nextLine());
                flag = false;
            } catch (InvalidParameterException e) {
                System.out.println("ERROR: " + e.getMessage());
                System.out.println("Please try again.");
                flag = true;
            }
        } while (flag);
    }

    /*
     * Updates the last name of the argument Employee to user specifications
     */
    private static void setLastName(Employee temp, String pw) {
        boolean flag;
        do {
            try {
                System.out.print("Please enter the employee's last name: ");
                temp.setLastName(pw, keyboard.nextLine());
                flag = false;
            } catch (InvalidParameterException e) {
                System.out.println("ERROR: " + e.getMessage());
                System.out.println("Please try again.");
                flag = true;
            }
        } while (flag);
    }

    /*
     * Updates the password of the argument Employee to user specifications
     */
    private static void setPassword(Employee temp, String pw) {
        boolean flag;
        do {
            try {
                System.out.println("Please enter the employee's new password.");
                System.out.println("Passwords must be numerical and exactly "
                + "6 digits long.");
                temp.setPassword(pw, keyboard.nextLine());
                flag = false;
            } catch (InvalidParameterException e) {
                System.out.println("ERROR: " + e.getMessage());
                System.out.println("Please try again.");
                flag = true;
            }
        } while (flag);
    }

    /**
     * Updates information for a new hire according to user specifications
     */
    private static void employeeBuilder(Employee temp) {
        String pw = "123456";
        setFirstName(temp, pw);
        setLastName(temp, pw);
        setPassword(temp, pw);
    }

    /*
     * Saves Employee and SaleItem data from ArrayLists to txt files so that no
     * data is lost upon closing program
     */
    private static void saveData() {
        try {
            // Saves Managers
            PrintWriter managersWriter = new PrintWriter("managers.txt");
            for (Manager manager : managers) {
                managersWriter.println(manager.getFirstName());
                managersWriter.println(manager.getLastName());
                managersWriter.println(manager.getPassword());
            }
            managersWriter.close();

            // Saves TeamMembers
            PrintWriter crewWriter = new PrintWriter("crew.txt");
            for (TeamMember teamMember : crew) {
                crewWriter.println(teamMember.getFirstName());
                crewWriter.println(teamMember.getLastName());
                crewWriter.println(teamMember.getPassword());
            }
            crewWriter.close();

            // Saves ShiftLeads
            PrintWriter leadsWriter = new PrintWriter("leads.txt");
            for (ShiftLead shiftLead : leads) {
                leadsWriter.println(shiftLead.getFirstName());
                leadsWriter.println(shiftLead.getLastName());
                leadsWriter.println(shiftLead.getPassword());
            }
            leadsWriter.close();

            // Saves SaleItems
            PrintWriter inventoryWriter = new PrintWriter("inventory.txt");
            for (SaleItem item : inventory) {
                inventoryWriter.println(item.getName());
                inventoryWriter.println(item.getPrice());
                inventoryWriter.println(item.getStock());
                inventoryWriter.println(item.getTotalSold());
            }
            inventoryWriter.close();
        } catch (IOException e) {
            System.out.println("Error occurred while saving data: "
            + e.getMessage());
        }
    }

    /*
     * Loads data from saved txt files into ArrayLists to use in POS software
     */
    private static void loadData() {
        try {
            // Load Managers
            Scanner managerScanner = new Scanner(new File("managers.txt"));
            while (managerScanner.hasNextLine()) {
                String first = managerScanner.nextLine();
                String last = managerScanner.nextLine();
                String password = managerScanner.nextLine();
                Manager manager = new Manager(first, last, password);
                managers.add(manager);
            }
            managerScanner.close();

            // Load Team Members
            Scanner crewScanner = new Scanner(new File("crew.txt"));
            while (crewScanner.hasNextLine()) {
                String first = crewScanner.nextLine();
                String last = crewScanner.nextLine();
                String password = crewScanner.nextLine();
                TeamMember teamMember = new TeamMember(first, last, password);
                crew.add(teamMember);
            }
            crewScanner.close();

            // Load Shift Leads
            Scanner leadsScanner = new Scanner(new File("leads.txt"));
            while (leadsScanner.hasNextLine()) {
                String first = leadsScanner.nextLine();
                String last = leadsScanner.nextLine();
                String password = leadsScanner.nextLine();
                ShiftLead shiftLead = new ShiftLead(first, last, password);
                leads.add(shiftLead);
            }
            leadsScanner.close();

            // Load Inventory
            Scanner inventoryScanner = new Scanner(new File("inventory.txt"));
            while (inventoryScanner.hasNextLine()) {
                String name = inventoryScanner.nextLine();
                double price = Double.parseDouble(inventoryScanner.nextLine());
                int stock = Integer.parseInt(inventoryScanner.nextLine());
                int totalSold = Integer.parseInt(inventoryScanner.nextLine());
                SaleItem item = new SaleItem(name, price, stock);
                item.setTotalSold(totalSold);
                inventory.add(item);
            }
            inventoryScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error loading data. "
            + "Please ensure the following files are saved on your computer: "
            + "inventory.txt, managers.txt, leads.txt, and crew.txt.");
            System.exit(0);
        }
    }

    /*
     * Returns the employee that contains specified employee ID, or null if not 
     * found
     */
    private static Employee getEmployeeWithID(String id){
        for(Manager manager : managers) {
            if (manager.getID().equals(id)) {
                return manager;
            }
        }

        // searches through leads to find entered employee ID
        for(ShiftLead lead : leads) {
            if (lead.getID().equals(id)) {
                return lead;
            }
        }

        // searches through crew to find entered employee ID
        for(TeamMember teamMember: crew) {
            if (teamMember.getID().equals(id)) {
                return teamMember;
            }
        }

        // returns null if employee was not located
        return null;
    }

    /*
     * Returns the employee that contains specified employee password,
     * or null if not found
     */
    private static Employee getEmployeeWithPW(String pw){
        for(Manager manager : managers) {
            if (manager.getPassword().equals(pw)) {
                System.out.println("Changing password for employee "
                + manager.getName() + ".");
                setPassword(manager, manager.getPassword());
                System.out.println("Password successfully changed.");
                return manager;
            }
        }

        // searches through leads to find entered employee ID
        for(ShiftLead lead : leads) {
            if (lead.getPassword().equals(pw)) {
                System.out.println("Changing password for employee "
                + lead.getName() + ".");
                setPassword(lead, lead.getPassword());
                System.out.println("Password successfully changed.");
                return lead;
            }
        }

        // searches through crew to find entered employee ID
        for(TeamMember teamMember: crew) {
            if (teamMember.getID().equals(pw)) {
                return teamMember;
            }
        }

        // returns null if employee was not located
        return null;
    }
    
    /*
     * Sets the master key. Only runs at startup, will prompt user if there is
     * currently no master key (if the program has not run before), or will
     * load up the saved master key from previous runs
     */
    private static void setMasterKey() {
        String password;
        try{
            Scanner keyScanner = new Scanner(new File("masterkey.txt"));
            if(keyScanner.hasNextLine()){
                password = keyScanner.nextLine();
                keyScanner.close();
            } else {
                System.out.println("\nWelcome, new user!");
                System.out.println("Please choose a master key to secure your "
                + "new POS system. This can not be changed later.");
                password = keyboard.nextLine();
                PrintWriter keyWriter = new PrintWriter("masterkey.txt");
                keyWriter.println(password);
                keyWriter.close();
                System.out.println("Your master key has been set. "
                + "Initializing startup.\n");
            }
            masterKey = password;
        } catch(FileNotFoundException e) {
            System.out.println("Error loading data. "
            + "Please ensure the following file is saved on your computer: "
            + "masterkey.txt.");
            System.exit(0);   
        }
    }
}