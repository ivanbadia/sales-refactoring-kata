package salestax;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SalesProgram {

    public static final BufferedReader BUFFERED_READER = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        Sale sale;
        String input;

        sale = new Sale();
        System.out.println("Enter sales in the format <qty> <description> at <unit price>\nFor example: 2 books at 13.25\nEntering a blank line completes the sale\n");
        input = getInput();
        while (!"".equals(input))
        {
            if (!sale.add(input))
                System.out.println("Sales should be in the format of <qty> <description> at <unit price>\nFor example: 2 books at 13.25");
            input = getInput();
        }
        System.out.println(sale.toString());
        System.out.println("--- Press Enter to Finish ---");
        readLine();
    }

    static String getInput()
    {
        String result;
        System.out.println("Sale : ");

        result = readLine();

        if (!"".equals(result))
            result = result.trim();
        return result;
    }

    static String readLine() {
        try {
            return BUFFERED_READER.readLine();
        } catch (IOException e) {
            return "";
        }
    }

}
