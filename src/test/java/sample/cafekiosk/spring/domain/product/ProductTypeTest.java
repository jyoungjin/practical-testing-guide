package sample.cafekiosk.spring.domain.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import sample.cafekiosk.spring.IntegrationTestSupport;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class ProductTypeTest extends IntegrationTestSupport {

    @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
    @Test
    void containsStockType() {
        // given
        ProductType handmade = ProductType.HANDMADE;
        ProductType bakery = ProductType.BAKERY;
        ProductType bottle = ProductType.BOTTLE;

        // when
        boolean handmadeResult = ProductType.containsStockType(handmade);
        boolean bakeryResult = ProductType.containsStockType(bakery);
        boolean bottleResult = ProductType.containsStockType(bottle);

        // then
        assertThat(handmadeResult).isFalse();
        assertThat(bakeryResult).isTrue();
        assertThat(bottleResult).isTrue();
    }

    @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
    @CsvSource({"HANDMADE,false", "BOTTLE,true", "BAKERY,true"})
    @ParameterizedTest
    void containsStockTypeWithParameterizedTest1(ProductType productType, boolean expected) {
        //when
        boolean result = ProductType.containsStockType(productType);

        //then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
    @MethodSource("provideProductTypesForCheckingStockType")
    @ParameterizedTest
    void containsStockTypeWithParameterizedTest2(ProductType productType, boolean expected) {
        //when
        boolean result = ProductType.containsStockType(productType);

        //then
        assertThat(result).isEqualTo(expected);
    }

    // given의 역할
    private static Stream<Arguments> provideProductTypesForCheckingStockType() {
        return Stream.of(
                Arguments.of(ProductType.HANDMADE, false),
                Arguments.of(ProductType.BOTTLE, true),
                Arguments.of(ProductType.BAKERY, true)
        );
    }
}
