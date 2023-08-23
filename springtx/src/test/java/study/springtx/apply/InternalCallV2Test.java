package study.springtx.apply;

import lombok.RequiredArgsConstructor;
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
public class InternalCallV2Test {

    @TestConfiguration
    static class TestConfig{

        @Bean
        public InternalService internalService(){
            return new InternalService();
        }

        @Bean
        public CallService callService(InternalService internalService){
            return new CallService(internalService);
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


    @RequiredArgsConstructor
    static class CallService{

        private final InternalService internalService;

        public void external() {
            log.info("======= call external =======");
            printTxInfo();
            internal();
        }

        public void internal(){
            internalService.internal();
        }

        void printTxInfo(){
            boolean txActive = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("tx active={}",txActive);
        }
    }

    static class InternalService{

        @Transactional
        public void internal(){
            log.info("======= call internal ======= ");
            printTxInfo();
        }

        void printTxInfo(){
            boolean txActive = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("tx active={}",txActive);
        }
    }

}
