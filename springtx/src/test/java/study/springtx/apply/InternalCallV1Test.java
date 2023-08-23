package study.springtx.apply;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.transaction.Transactional;

@Slf4j
@SpringBootTest
public class InternalCallV1Test {

    @TestConfiguration
    static class TestConfig{

        @Bean
        public CallService callService(){
            return new CallService();
        }
    }

    @Autowired
    CallService callService;


    @Test
    void internalCall(){
        callService.internal();
    }

    @Test
    void externalCall(){
        callService.external();
    }


    static class CallService{

        @Transactional
        public void internal(){
            log.info("call internal");
            printTxInfo();
        }

        public void external() {
            log.info("call external");
            printTxInfo();
            internal();
        }

        void printTxInfo(){
            boolean txActive = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("tx active={}",txActive);
        }
    }

}
