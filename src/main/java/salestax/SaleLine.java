package salestax;

import java.text.DecimalFormat;

public class SaleLine
{
    private String productName;
    private double price;
    private boolean isImported;
    private int quantity;
    private double taxAmount;
    private double lineValue;

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public boolean isImported() {
        return isImported;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public double getLineValue() {
        return lineValue;
    }


    /// <summary>
    /// Default constructor is not publicly accesible
    /// </summary>
    private SaleLine()
    {
    }

    /// <summary>
    /// Public constructor for the sale line
    /// </summary>
    /// <param name="lineQuantity">Quantity on order</param>
    /// <param name="name">name of the product</param>
    /// <param name="unitPrice">price of a single item</param>
    /// <param name="itemIsImported">flag to indicate if the item is imported</param>
    public SaleLine(int lineQuantity, String name, double unitPrice, boolean itemIsImported)
    {
        int taxRate;
        if (name == null || name.isEmpty()) name = "";

        quantity = lineQuantity;
        productName = name;
        price = unitPrice;
        isImported = itemIsImported;
        lineValue = price * quantity;

        // calculate taxable amount
        // ideally should really have a product list and tax rules, but this'll have to do for the exercise.
        if (productName.contains("book") || productName.contains("tablet") || productName.contains("chip") || productName.contains("chocolate"))
            taxRate = 0;  //No base tax applicable for books, medicals items or food
        else
            taxRate = 10; //10% base tax or general products
        if (isImported)
            taxRate += 5; //5% regardless for any imported items

        taxAmount = CalculateTax(lineValue,taxRate);
        //Add tax to line value
        lineValue += taxAmount;
        return;
    }

    /// <summary>
    /// Calculates the amount of tax for a value, rounded up to the nearest 5 cents
    /// </summary>
    /// <param name="value">The original value</param>
    /// <param name="taxRate">The tax rate to apply</param>
    /// <returns>The calculated tax on the original value</returns>
    public static double CalculateTax(double value, int taxRate)
    {
        double amount;
        double remainder;

        amount = (value * taxRate)/100;

        //Now round up to nearest 5 cents.
        remainder = amount % .05;
        if (remainder > 0)
            amount += .05 - remainder;
        return amount;
    }

    /// <summary>
    /// Converts the sale line to a string
    /// </summary>
    /// <returns>The string representation of the sale line</returns>
    @Override
    public String toString() {
        return String.format("%d %s: %s", getQuantity(), getProductName(), new DecimalFormat("#,##0.00").format(getLineValue()));
    }
}
