public class StockItem {

// Basically the blueprint
// Actually stores the data on to objects so updates are easier (entire goal of this class)

    // Stock item variables (each object = one item from the master file)
    String partID;       // Store the part id number
    String partName;     // Name of the item
    int quantity;        // Amount of stock
    int orderPoint;      // Store when item is low or considered low
    String site;         // Job site

    
    
    // Constructor (basically the setup for "objects")
    // Takes values from the program8master file and stores them inside the objects so i can use them later (easier to update that way)
    public StockItem(String partID, String partName, int quantity, int orderPoint, String site) {
        this.partID = partID;       
        this.partName = partName;   
        this.quantity = quantity;   
        this.orderPoint = orderPoint; 
        this.site = site;           
    }

    
    
    
    // Updates quantity, negative sold, positive restocked
    public void updateQuantity(int amount) {
        quantity += amount; // Adds or subtracts from current quantity
    }

    
    
    
    // Check status, tells if out of stock or low (quantity)
    public String getStatus() {
        if (quantity <= 0) {             // If less than or equal to 0 (out of stock)
            return "Out Of Stock";
        }
        else if (quantity <= orderPoint) {
            return "Stock low";
        }
        else {
            return "Ok";
        }
    }
}