import java.security.InvalidParameterException;

public class SaleItem {
    private String name;
    private double price;
    private int stock;
    private int totalSold;
    
    /**
     * Constructor
     * 
     * Preconditions:   Arguments include a name, price, and stock of new item
     * Postconditions:  All fields are initialized to user specifications
     * 
     * @param name
     * @param price
     * @param stock
     */
    public SaleItem(String name, double price, int stock){
        this.setName(name);
        this.price      = price;
        this.stock      = stock;
        this.totalSold = 0;
    }

    /**
     * Mutator
     * 
     * Preconditions:   newName is longer than length of 0
     * Postconditions:  name is updated to user specifications
     * 
     */
    public void setName(String newName){
        if(newName.length() < 1){
            throw new InvalidParameterException("No name provided");
        } else {
            this.name = new String(newName);
        }
    }

    /**
     * Mutator
     * 
     * Preconditions:   Argument includes new price
     * Postconditions:  price is updated to user specifications
     * 
     */
    public void setPrice(double newPrice){
        this.price = newPrice;
    }

    /**
     * Mutator
     * 
     * Preconditions:   Argument includes new stock count
     * Postconditions:  stock is updated to user specifications
     * 
     */
    public void updateStock(int newStock){
        this.stock = newStock;
    }

    /**
     * Mutator
     * 
     * Preconditions:   Argument includes stock to be added to current stock
     * Postconditions:  stock is updated to current stock + new stock
     * 
     */
    public void addToStock(int newStock){
        this.stock = (this.stock + newStock);
    }

    /**
     * Mutator
     * 
     * Preconditions:   Argument includes stock to be removed from current stock
     * Postconditions:  stock is updated to current stock - sold stock
     * 
     */
    public void removeFromStock(int soldStock){
        this.stock = (this.stock - soldStock);
    }

    /**
     * Mutator
     * 
     * Preconditions:   Argument includes number of items sold for invoking item
     * Postconditions:  totalSold is updated for invoking item
     * @param totalSold
     */
    public void setTotalSold(int totalSold){
        this.totalSold = totalSold;
    }

    /**
     * Accessor
     * 
     * Preconditions:   None
     * Postconditions:  None
     * 
     * @return String
     */
    public String getName(){
        return new String(this.name);
    }

    /**
     * Accessor
     * 
     * Preconditions:   None
     * Postconditions:  None
     * 
     * @return double
     */
    public double getPrice(){
        return this.price;
    }

    /**
     * Accessor
     * 
     * Preconditions:   None
     * Postconditions:  None
     * 
     * @return int
     */
    public int getStock(){
        return this.stock;
    }

    /**
     * Accessor
     * 
     * Preconditions:   None
     * Postconditions:  None
     * 
     * @return int
     */
    public int getTotalSold(){
        return this.totalSold;
    }
}