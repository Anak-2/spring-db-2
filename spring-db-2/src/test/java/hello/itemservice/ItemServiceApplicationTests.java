package hello.itemservice;

import hello.itemservice.repository.ItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionStatus;

import java.util.Arrays;

@SpringBootTest
class ItemServiceApplicationTests {

    @Autowired
    ApplicationContext ac;

    @Autowired
    PlatformTransactionManager transactionManager;
    TransactionStatus status;

    @BeforeEach
    void beforeEach(){
        status = transactionManager.getTransaction(new DefaultTransactionDefinition());
    }

    @AfterEach
    void afterEach(){
        transactionManager.rollback(status);
    }

	@Test
	void contextLoads() {
	}

//    @Test
//    void showBeanImplements(){
//        System.out.println(ac.getBean("jdbcTemplateItemRepositoryV1", ItemRepository.class).getClass());
//    }

}
