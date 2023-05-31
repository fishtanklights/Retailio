import java.security.InvalidParameterException;

public class Employee {
    protected String firstName;
    protected String lastName;
    protected String password;
    private String ID;
    public final double HOURLY_WAGE;

    /**
     * Constructor
     * 
     * Preconditions:   First and last name must be longer than length 0 and can
     *                  have no other special characters than spaces or hyphens.
     *                  Password must be an String of exactly 6 digits
     * Postconditions:  First and last name and password will be initialized to
     *                  user argument. ID will be initialized to first initial
     *                  and last name, in all lower case and with no spaces or
     *                  special characters.
     * 
     * @param first
     * @param last
     * @param password
     */
    public Employee(String first, String last, String password, double wage){
        this.password = "000000"; // temp password used to call mutators
        this.setFirstName(this.password, first);
        this.setLastName(this.password, last);
        this.setID();
        this.setPassword(this.password, password);
        HOURLY_WAGE = wage;
    }

    /**
     * Copy Constructor
     * 
     * Preconditions:   Argument must be a valid Employee object
     * Postconditions:  All fields will be initialized to the same values as
     *                  Specified employee. HOURLY_WAGE will be updated to 30
     * 
     * @param first
     * @param last
     * @param password
     */
    public Employee(Employee other){
        this.password = other.password;
        this.setFirstName(this.password, other.firstName);
        this.setLastName(this.password, other.lastName);
        this.setID();
        this.HOURLY_WAGE = other.HOURLY_WAGE;
    }

    /*
     * Creates an ID for each new employee. ID will not be able to be changed
     * by employee, but will be updated if employee ever changes their name
     * 
     * Preconditions:   First and last name are obtained from instance variables
     * Postconditions:  ID is created by concatenating first initial and last 
     *                  name in lower case with no spaces or special characters
     */
    private void setID(){
        String first   = this.firstName.toLowerCase();
        String last    = this.lastName.toLowerCase();
        last           = last.replaceAll("[- ]","");
        this.ID        = first.charAt(0) + last;
    }

    /**
     * Mutator
     * 
     * Preconditions:   first name must be longer than length 0 and contain no
     *                  special characters aside from space or hyphen
     * Postconditions:  firstName is updated to user specification
     */
    public void setFirstName(String currentPW, String newFirst){
        if(this.password == currentPW) {
            if(newFirst.length() < 1) {
                throw new InvalidParameterException("No last name entered");
            } else if(newFirst.matches
                     (".*[~`!@#$%^&*()_+=|/><.,{}:;\"'0123456789].*")) {
                throw new InvalidParameterException("Invalid special characters");
            } else {
                this.firstName = new String(newFirst);

                //ensures setID will only be called if lastName has been initialized
                if(this.lastName != null){
                    setID();
                }
            }
        } else {
            throw new InvalidParameterException
            ("Access denied");
        }
    }

    /**
     * Mutator
     * 
     * Preconditions:   last name must be longer than length 0 and contain no
     *                  special characters aside from space and/or hyphen
     * Postconditions:  lastName is updated to user specification
     */
    public void setLastName(String currentPW, String newLast){
        if(this.password == currentPW) {
            if(newLast.length() < 1) {
                throw new InvalidParameterException("No last name entered");
            } else if (newLast.matches
                      (".*[~`!@#$%^&*()_+=|/><.,{}:;\"'0123456789].*")) {
                throw new InvalidParameterException("Invalid special characters");
            } else {
                this.lastName = new String(newLast);
                setID();
            }
        } else {
            throw new InvalidParameterException
            ("Access denied");
        }
    }

    /**
     * Mutator
     * 
     * Preconditions:   password must be an int of exactly 6 digits
     * Postconditions:  password is updated to user argument
     */
    public void setPassword(String currentPW, String newPW){
        // pw is type String rather than int so it can lead with 0 without error
        try{
            Integer.parseInt(newPW); // checks if password is a number
        } catch (Exception e){
            throw new InvalidParameterException
            ("Password must be a number");
        }
        if(this.password == currentPW) {
            if(newPW.length() == 6) { // checks if password is correct length
                this.password = newPW;
            } else {
                throw new InvalidParameterException
                ("Password must be exactly 6 digits long");
            }
        } else {
            throw new InvalidParameterException
            ("Access denied");
        }
    }

    /**
     * Returns the password of the invoking Employee
     * 
     * Preconditions:   None
     * Postconditions:  None
     * 
     * @param masterKey
     * @return
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Returns the ID of invoking employee
     * 
     * Preconditions:   None
     * Postconditions:  None
     * 
     * @return String
     */
    public String getID() {
        return new String(this.ID);
    }

    /**
     * Returns full name of invoking Employee
     * 
     * Preconditions:   None
     * Postconditions:  None
     * 
     * @return String
     */
    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    /**
     * Returns first name of invoking Employee
     * 
     * Preconditions:   None
     * Postconditions:  None
     * 
     * @return String
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Returns last name of invoking Employee
     * 
     * Preconditions:   None
     * Postconditions:  None
     * 
     * @return String
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Returns a string representation of invoking Employee
     */
    public String toString(){
        return "Name: " + this.getName() + " ID: " + this.getID();
    }
}