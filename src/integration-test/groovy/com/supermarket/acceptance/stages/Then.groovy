package com.supermarket.acceptance.stages

import com.tngtech.jgiven.Stage
import com.tngtech.jgiven.annotation.ExpectedScenarioState

class Then extends Stage<Then> {
    @ExpectedScenarioState
    private ByteArrayOutputStream output

    void the_receipt_is(List<String> receiptLines) {
        String receipt = ""
        receiptLines.forEach({ line -> receipt += line + "\n" })
        def s = output.toString()
        assert s.contains(receipt)
        self()
    }
}
