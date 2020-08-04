package com.supermarket.sales.acceptance

import com.supermarket.sales.acceptance.stages.Given
import com.supermarket.sales.acceptance.stages.Then
import com.supermarket.sales.acceptance.stages.When
import com.tngtech.jgiven.spock.ScenarioSpec

class CalculateReceiptFeature extends ScenarioSpec<Given, When, Then> {

    def "should calculate receipt"() {
        expect:
        given().sale_items(saleItems)
        when().the_receipt_is_calculated()
        then().the_receipt_is(receipt)

        where:
        saleItems                                                               | receipt
        ["1 book at 12.49", "1 music CD at 14.99", "1 packet of chips at 0.85"] | ["1 book: 12.49","1 music CD: 16.49", "1 packet of chips: 0.85", "Sales Taxes: 1.50", "Total: 29.83"]
    }
}

