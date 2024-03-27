package sample.cafekiosk.spring.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sample.cafekiosk.spring.IntegrationTestSupport;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;
import sample.cafekiosk.spring.domain.product.ProductType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.SELLING;
import static sample.cafekiosk.spring.domain.product.ProductType.HANDMADE;

class OrderRepositoryTest extends IntegrationTestSupport {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @DisplayName("요청일시에 해당하는 결제완료 상태인 order 리스트를 가져온다.")
    @Test
    void findOrdersBy() {
        // given
        LocalDateTime currentDateTime = LocalDateTime.of(2024, 3, 25, 10, 37);
        LocalDate currentDate = currentDateTime.toLocalDate();

        Product product1 = createProduct(HANDMADE, "001", 1000);
        Product product2 = createProduct(HANDMADE, "002", 2000);
        Product product3 = createProduct(HANDMADE, "003", 3000);
        productRepository.saveAll(List.of(product1, product2, product3));

        orderRepository.save(
                Order.builder()
                        .products(List.of(product1, product2, product3))
                        .orderStatus(OrderStatus.PAYMENT_COMPLETED)
                        .registeredDateTime(currentDateTime)
                        .build()
        );

        // when
        List<Order> orders = orderRepository.findOrdersBy(
                currentDate.atStartOfDay(),
                currentDate.plusDays(1).atStartOfDay(),
                OrderStatus.PAYMENT_COMPLETED
        );

        // then
        assertThat(orders).hasSize(1);
        assertThat(orders.stream().mapToInt(Order::getTotalPrice).sum()).isEqualTo(6000);
    }

    private static Product createProduct(ProductType type, String productNumber, int price) {
        return Product.builder()
                .type(type)
                .productNumber(productNumber)
                .price(price)
                .sellingStatus(SELLING)
                .name("메뉴 이름")
                .build();
    }
}
