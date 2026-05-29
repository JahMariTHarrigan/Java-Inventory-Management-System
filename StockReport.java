// program8master.txt is basically the inventory before any chances
// program8transaction.txt lets me know what changed so I know what to update
import java.util.*;
import java.io.*;

// Named "StockReport" because it's the main and running this one actually gives the updates report
public class StockReport {
    public static void main(String[] args) throws IOException {

        // Holds all stock items from program8master file
        ArrayList<StockItem> items = new ArrayList<>();

        
        
        // Stuff to read program8master file below
        // Master file has starting inventory before updates
        Scanner masterFile = new Scanner(new File("Program8Master.txt"));        // This one actually opens the file

        while (masterFile.hasNextLine()) {             // Start of the reading
            String line = masterFile.nextLine();

            
            if (line.length() == 0) {     // Skips the empty lines
                continue;
            }

            String[] info = line.split("\\s+"); // Splits line into respective values in info array

            String partID = info[0]; // ID
            String site = info[info.length - 1]; // (always at the end) so site, quarterpoint, quantity
            int orderPoint = Integer.parseInt(info[info.length - 2]); // 2nd from last
            int quantity = Integer.parseInt(info[info.length - 3]);   // 3rd from last

            String partName = "";

            // Builds the part name (multiple words from info array to form full name)
            for (int i = 1; i < info.length - 3; i++) {
                partName += info[i] + " ";
            }

            partName = partName.trim(); // Removes extra space at end "trim"

            
            // Constructor, new stockitem object made, This is where a new StockItem object is made
            // Info from program8masters gets sent to stockitem constructor
            StockItem item = new StockItem(partID, partName, quantity, orderPoint, site);

            items.add(item); 
        }

        masterFile.close();

        
        
        // Stuff to read program8transaction file
        // Updates quantities based on buying/selling
        Scanner transFile = new Scanner(new File("Program8Transaction.txt"));

        while (transFile.hasNext()) {                      // Start of actual reading
            String transID = transFile.next();   
            String transSite = transFile.next(); 
            int amount = transFile.nextInt();    // Change in quantity

            // Loops to find matching item size
            for (int i = 0; i < items.size(); i++) {
                StockItem current = items.get(i);

                // ID and site gotta match
                if (current.partID.equals(transID) && current.site.equals(transSite)) {
                    current.updateQuantity(amount);
                }
            }
        }

        transFile.close();

        
        
        // Final report is printed below
        System.out.println("==== Manufacturing Company Updated Stock Report ====");
        System.out.println();
        System.out.println("PART ID\t\tPART NAME\t\tQUANTITY\tORDER POINT\tSITE\t\tSTATUS");

        for (int i = 0; i < items.size(); i++) {
            StockItem s = items.get(i);

            System.out.println(s.partID + "\t\t" + s.partName + "\t\t" + s.quantity + "\t\t" + s.orderPoint + "\t\t" + s.site + "\t\t" + s.getStatus());
        }

        System.out.println();
        System.out.println("Report Submitted.");

    } // End Main
} // End Class