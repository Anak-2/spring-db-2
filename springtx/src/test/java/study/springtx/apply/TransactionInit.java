package study.springtx.apply;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Slf4j
@SpringBootTest
public class TransactionInit {

    @Autowired
    InitTx initTx;

    @Test
    void test_tx_init(){

    }

    @TestConfiguration
    static class InitTx{

        @PostConstruct
        @Transactional
        public void initV1(){
            log.info("====== post construct ======");
            boolean isTx = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("is Tx: {}",isTx);
        }

        @EventListener(ApplicationReadyEvent.class)
        @Transactional
        public void initV2(){
            log.info("====== event listener ======");
            boolean isTx = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("is Tx: {}",isTx);
        }
    }

}
