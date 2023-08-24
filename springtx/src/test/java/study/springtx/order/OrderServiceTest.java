package study.springtx.order;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    void orderComplete() throws NotEnoughMoneyException {
        // given
        Order order = new Order();
        order.setUsername("정상");

        // when
        orderService.order(order);

        // then
        Order findOrder = orderRepository.findOrderById(order.getId()).get();
        Assertions.assertThat(findOrder.getPayStatus()).isEqualTo(PayStatus.Success);
    }

//    커밋 o
    @Test
    void orderWait() throws NotEnoughMoneyException {
        // given
        Order order = new Order();
        order.setUsername("");

        // when
        Assertions.assertThatThrownBy(() -> orderService.order(order))
                .isInstanceOf(NotEnoughMoneyException.class);

        // then
        Order findOrder = orderRepository.findOrderById(order.getId()).get();
        Assertions.assertThat(findOrder.getPayStatus()).isEqualTo(PayStatus.Wait);
    }

    @Test
    void orderFail() throws NotEnoughMoneyException {
        // given
        Order order = new Order();
        order.setUsername("예외");

        // when
        Assertions.assertThatThrownBy(() -> orderService.order(order))
                .isInstanceOf(RuntimeException.class);

        // then
       Assertions.assertThat(orderRepository.findOrderById(order.getId()).isEmpty()).isEqualTo(true);
    }
}