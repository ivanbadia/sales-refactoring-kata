package com.supermarket.domain.receipt;

import com.supermarket.domain.product.Product;
import com.supermarket.domain.product.ProductName;
import com.supermarket.domain.product.ProductType;
import com.supermarket.domain.product.Products;
import com.supermarket.domain.sales.SaleLine;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.supermarket.domain.product.ProductType.GENERAL;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TaxRateProviderShould {
    private static final int BASE_TAX = 10;
    private static final ProductName PRODUCT = new ProductName("product");

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
        given(products.by(new ProductName("product")))
                .willReturn(Optional.of(new Product(PRODUCT, type)));

        int taxRate = taxRateProvider.taxRateFor(new SaleLine(1, PRODUCT, 12.3, false));

        assertThat(taxRate)
                .isEqualTo(0);
    }

    @Test
    void  return_base_tax_for_general_products() {
        given(products.by(new ProductName("product")))
                .willReturn(Optional.of(new Product(new ProductName("product"), GENERAL)));

        int taxRate = taxRateProvider.taxRateFor(new SaleLine(1, PRODUCT, 12.3, false));

        assertThat(taxRate)
                .isEqualTo(BASE_TAX);
    }

    @Test
    void  return_extra_tax_for_imported_products() {
        given(products.by(new ProductName("product")))
                .willReturn(Optional.of(new Product(new ProductName("product"), GENERAL)));

        int taxRate = taxRateProvider.taxRateFor(new SaleLine(1, PRODUCT, 12.3, true));

        assertThat(taxRate)
                .isEqualTo(15);
    }


    @Test
    void  return_base_tax_for_products_not_found() {
        given(products.by(new ProductName("product")))
                .willReturn(Optional.empty());

        int taxRate = taxRateProvider.taxRateFor(new SaleLine(1, PRODUCT, 12.3, false));

        assertThat(taxRate)
                .isEqualTo(BASE_TAX);
    }
}