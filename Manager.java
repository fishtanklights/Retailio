public class Manager extends Employee {

    /**
     * Constructor
     * 
     * Preconditions:   First and last name must be longer than length 0 and can
     *                  have no other special characters than spaces or hyphens.
     *                  Password must be an number of exactly 6 digits
     * Postconditions:  First and last name and password will be initialized to
     *                  user argument. ID will be initialized to first initial
     *                  and last name, in all lower case and with no spaces or
     *                  special characters. HOURLY_WAGE will be set to 30.
     * 
     * @param first
     * @param last
     * @param password
     */
    public Manager(String first, String last, String password){
        super(first, last, password, 30);
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
    public Manager(Employee other){
        super(other.firstName, other.lastName, other.password, 30);
    }
}