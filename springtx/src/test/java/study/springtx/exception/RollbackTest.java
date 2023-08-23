package study.springtx.exception;

import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import javax.transaction.Transactional;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Slf4j
public class RollbackTest {

    @Autowired
    RollbackService rollbackService;

    @TestConfiguration
    static class TestConfig{

        @Bean
        public RollbackService makeRollbackService(){
            return new RollbackService();
        }
    }

    @Test
    void test_exception_rollback() throws RuntimeException, MyException{
        assertThatThrownBy(() -> rollbackService.runtimeException())
                .isInstanceOf(RuntimeException.class);
        assertThatThrownBy(() -> rollbackService.checkedException())
                .isInstanceOf(MyException.class);
        assertThatThrownBy(() -> rollbackService.rollbackCheckedException())
                .isInstanceOf(MyException.class);
    }

    static class RollbackService {

        // 언체크 예외 (런타임 에러) --> 롤백
        @Transactional
        public void runtimeException() throws RuntimeException{
            log.info("====== Call RuntimeException ======");
            throw new RuntimeException();
        }

        // 체크 예외 --> 커밋
        @Transactional
        public void checkedException() throws MyException {
            log.info("====== Call CheckedException");
            throw new MyException();
        }

        // 체크 예외 , rollbackFor 지정 --> 롤백
        @Transactional(rollbackOn = MyException.class)
        public void rollbackCheckedException() throws MyException{
            log.info("====== Call RollbackCheckedException");
            throw new MyException();
        }
    }

    static class MyException extends Exception{
        MyException(){
            System.out.println("MyException Called");
        }
    }
}
