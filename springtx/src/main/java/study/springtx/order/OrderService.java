package study.springtx.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public void order(Order order) throws NotEnoughMoneyException {
        log.info("order 호출");
        orderRepository.save(order);

        log.info("결제 프로세스 진입");
        if(order.getUsername().equals("예외")){
            order.setPayStatus(PayStatus.Fail);
            throw new RuntimeException("시스템 예외");

        } else if(order.getUsername().isBlank() || order.getUsername() == null){
            order.setPayStatus(PayStatus.Wait);
            throw new NotEnoughMoneyException("대기");

        } else{
            log.info("결제 완료");
            order.setPayStatus(PayStatus.Success);
        }

        log.info("결제 프로세스 종료");
    }
}
