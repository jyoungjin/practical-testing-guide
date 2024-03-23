package sample.cafekiosk.spring.domain.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class ProductTypeTest {

    @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
    @Test
    void containsStockType() {
        // given
        ProductType handmade = ProductType.HANDMADE;
        ProductType bakery = ProductType.BAKERY;

        // when
        boolean handmadeResult = ProductType.containsStockType(handmade);
        boolean bakeryResult = ProductType.containsStockType(bakery);

        // then
        assertThat(handmadeResult).isFalse();
        assertThat(bakeryResult).isTrue();
    }
}
