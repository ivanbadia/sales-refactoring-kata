package com.supermarket.acceptance.stages

import com.tngtech.jgiven.Stage
import com.tngtech.jgiven.annotation.ProvidedScenarioState

class Given extends Stage<Given> {
    @ProvidedScenarioState
    private List<String> saleItems

    def sale_items(List<String> saleItems) {
        this.saleItems = saleItems
    }
}
