package study.springtx.apply;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.transaction.Transactional;

@Slf4j
@SpringBootTest
public class TransactionTest {

    @TestConfiguration
    static class TestConfig{
        @Bean
        public BasicService basicService(){
            return new BasicService();
        }
    }

    @Autowired
    BasicService basicService;

    static class BasicService {

        @Transactional
        public void tx() {
            log.info("call tx");
            boolean txActive =
                    TransactionSynchronizationManager.isActualTransactionActive();
            log.info("tx active={}", txActive);
        }

        public void nonTx() {
            log.info("call nonTx");
            boolean txActive =
                    TransactionSynchronizationManager.isActualTransactionActive();
            log.info("tx active={}", txActive);
        }

    }

    @Test
    void check_proxy(){
        log.info("aop class={}",basicService.getClass());
        Assertions.assertThat(AopUtils.isAopProxy(basicService)).isTrue();
    }

    @Test
    void check_tx(){
        basicService.tx();
        basicService.nonTx();
    }
}
