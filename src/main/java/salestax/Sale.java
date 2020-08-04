package salestax;

import java.text.DecimalFormat;
import java.util.List;

public class Sale
{
    private List<SaleLine> saleLines;
    private double totalTax;
    private double totalValue;

    /// <summary>
    /// Adds a line to the sale.
    /// </summary>
    /// <param name="inputLine">The line to add.</param>
    /// <returns>True for success, False for failure.  Failures are usually caused via incorrect formatting of the input</returns>
    public boolean add(String inputLine)
    {
        SaleLine saleLine;

        saleLine = InputParser.ProcessInput(inputLine);
        saleLines.add(saleLine);
        totalTax += saleLine.getTaxAmount();
        totalValue += saleLine.getLineValue();
        return true;
    }

    /// <summary>
    /// The total Tax amount for the sale
    /// </summary>
    public double getTotalTax()
    {
        return totalTax;
    }

    /// <summary>
    /// The total value of the sale (including Tax)
    /// </summary>
    public double getTotalValue()
    {
        return totalValue;
    }

    /// <summary>
    /// Converts the sale to a string
    /// </summary>
    /// <returns></returns>
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        for(SaleLine line : saleLines)
        {
            if (output.length() > 0)
                output.append("\n");
            output.append(line.toString());
        }
        //Now add footer information
        output.append("\n");
        output.append(String.format("Sales Taxes: %s", new DecimalFormat("#,##0.00").format(getTotalTax())));
        output.append("\n");
        output.append(String.format("Total: %s", new DecimalFormat("#,##0.00").format(getTotalValue())));
        return output.toString();
    }
}
