package salestax;

// THIS IS NOT THREAD SAFE (or localised)
public class InputParser
{

    // Assumes that all input is in the format:
    //  <qty> <product> at <price>
    //
    //  If <product> contains the word imported then the product is deemed to attract import tax
    //
    // If it can't be parsed we return null.
    // If it can then we return a sales line, complete with tax information calculated.
    public static SaleLine ProcessInput(String input)
    {
        int quantity;
        String productName;
        double price;
        boolean isImported;
        SaleLine saleLine;

        if (input == null || input.isEmpty())
            return null;
        String[] words = input.split(" ");
        int wordCount = words.length;

        // must have at least 4 words
        if (wordCount > 4)
            return null;

        // get quantity (first word)
        try {
            quantity = Integer.parseInt(words[0]);
        } catch (NumberFormatException e) {
            return null;
        }


        // get price (last word in input string)
        try
        {
            price = Double.parseDouble(words[wordCount - 1]);
        } catch (NumberFormatException e) {
            return null;
        }

        productName = "";
        for(int i=1; i<wordCount; i++) {
            productName = String.join(" ", productName, words[i]);
        }

        if (productName == null || productName.isEmpty())
            return null;

        //Check if this is an imported product
        isImported = productName.contains("imported ");
        if (isImported)
        {
            //Ensure the word imported appears at the front of the description
            productName = "imported " + productName.replace("imported ", "");
        }

        // create the sale line
        saleLine = new SaleLine(quantity, productName, price, isImported);
        return saleLine;
    }

}
