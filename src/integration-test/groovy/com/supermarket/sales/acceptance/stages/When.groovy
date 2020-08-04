package com.supermarket.sales.acceptance.stages

import com.tngtech.jgiven.Stage
import com.tngtech.jgiven.annotation.ExpectedScenarioState
import com.tngtech.jgiven.annotation.ProvidedScenarioState
import salestax.SalesProgram

import static java.nio.charset.StandardCharsets.UTF_8

class When extends Stage<When> {
    @ExpectedScenarioState
    private List<String> saleItems
    @ProvidedScenarioState
    private ByteArrayOutputStream output = new ByteArrayOutputStream()

    void the_receipt_is_calculated() {
        InputStream stdin = System.in
        redirectConsoleOutputTo(output)
        writeToConsole(saleItems)
        SalesProgram.main()
        System.setIn(stdin);
        self()
    }

    private def redirectConsoleOutputTo(ByteArrayOutputStream outputStream) {
        PrintStream ps = new PrintStream(outputStream)
        System.setOut(ps)
    }


    private def writeToConsole(List<String> saleItems) {
        String lines = ""
        saleItems.forEach({ saleItem ->
            lines += saleItem + System.lineSeparator()
        })
        lines += "\n"
        ByteArrayInputStream inputStream = new ByteArrayInputStream(lines.getBytes(UTF_8))
        System.setIn(inputStream)
    }

}
