package salestax;

import com.supermarket.domain.sales.ConsoleSaleLineReader;

public class SalesProgram {

    public static void main(String[] args) {
        Sale sale;
        String input;

        sale = new Sale();
        System.out.println("Enter sales in the format <qty> <description> at <unit price>\nFor example: 2 books at 13.25\nEntering a blank line completes the sale\n");
        ConsoleSaleLineReader consoleSaleLineReader = new ConsoleSaleLineReader();
        input = consoleSaleLineReader.getInput();
        while (!"".equals(input))
        {
            if (!sale.add(input))
                System.out.println("Sales should be in the format of <qty> <description> at <unit price>\nFor example: 2 books at 13.25");
            input = consoleSaleLineReader.getInput();
        }
        System.out.println(sale.toString());
        System.out.println("--- Press Enter to Finish ---");
        consoleSaleLineReader.readLine();
    }

}
