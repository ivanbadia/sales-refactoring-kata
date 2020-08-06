package com.supermarket.domain.receipt;

import com.supermarket.domain.product.Product;
import com.supermarket.domain.product.ProductName;
import com.supermarket.domain.product.ProductType;
import com.supermarket.domain.product.Products;
import com.supermarket.domain.sales.SaleLine;
import com.supermarket.domain.shared.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.supermarket.domain.product.ProductType.GENERAL;
import static com.supermarket.domain.sales.SaleLineBuilder.aSaleLine;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TaxRateProviderShould {
    private static final int BASE_TAX = 10;
    private static final ProductName PRODUCT_NAME = new ProductName("product");
    private static final SaleLine NON_IMPORTED_SALE_ITEM = new SaleLine(1, PRODUCT_NAME, new Money(12.3), false);

    @Mock
    private Products products;

    private TaxRateProvider taxRateProvider;

    @BeforeEach
    void setUp() {
        taxRateProvider = new TaxRateProvider(products);
    }

    @ParameterizedTest
    @CsvSource(value = {"BOOK", "MEDICAL_ITEM", "FOOD"})
    void return_no_base_tax_for_products_exempt_of_taxes(ProductType type) {
        given(products.by(PRODUCT_NAME))
                .willReturn(Optional.of(new Product(PRODUCT_NAME, type)));

        int taxRate = taxRateProvider.taxRateFor(NON_IMPORTED_SALE_ITEM);

        assertThat(taxRate)
                .isEqualTo(0);
    }

    @Test
    void  return_base_tax_for_general_products() {
        given(products.by(PRODUCT_NAME))
                .willReturn(Optional.of(new Product(PRODUCT_NAME, GENERAL)));

        int taxRate = taxRateProvider.taxRateFor(NON_IMPORTED_SALE_ITEM);

        assertThat(taxRate)
                .isEqualTo(BASE_TAX);
    }

    @Test
    void  return_extra_tax_for_imported_products() {
        given(products.by(PRODUCT_NAME))
                .willReturn(Optional.of(new Product(PRODUCT_NAME, GENERAL)));

        int taxRate = taxRateProvider.taxRateFor(aSaleLine().withName(PRODUCT_NAME).imported().build());

        assertThat(taxRate)
                .isEqualTo(15);
    }


    @Test
    void  return_base_tax_for_products_not_found() {
        given(products.by(PRODUCT_NAME))
                .willReturn(Optional.empty());

        int taxRate = taxRateProvider.taxRateFor(NON_IMPORTED_SALE_ITEM);

        assertThat(taxRate)
                .isEqualTo(BASE_TAX);
    }
}