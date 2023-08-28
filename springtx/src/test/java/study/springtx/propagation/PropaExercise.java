package study.springtx.propagation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.UnexpectedRollbackException;

@Slf4j
@SpringBootTest
public class PropaExercise {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    LogRepository logRepository;

    @Test
    void separate_transaction(){
        // given
        String username = "kim";
        // when
        memberService.joinV1(username);
        // then
        Assertions.assertTrue(memberRepository.findByUsername(username).isPresent());
        Assertions.assertEquals(logRepository.findByUsername(username).getMessage(), username);
    }

    @Test
    void outerTxOff_fail(){
        // given
        String username = "로그예외_kim";
        // when
        Assertions.assertThrows(RuntimeException.class, () -> memberService.joinV1(username));
        // then
        Assertions.assertTrue(memberRepository.findByUsername(username).isPresent());
        Assertions.assertNull(logRepository.findByUsername(username));
    }

    @Test
    void allTxOn(){
        // given
        String username = "anak";
        // when
        memberService.joinV2(username);
        // then
        Assertions.assertTrue(memberRepository.findByUsername(username).isPresent());
        Assertions.assertEquals(logRepository.findByUsername(username).getMessage(), username);
    }

    @Test
    void allTxOn_fail(){
        // given
        String username = "로그예외_anak";
        // when
        Assertions.assertThrows(RuntimeException.class, () -> memberService.joinV2(username));
        // then
        Assertions.assertTrue(memberRepository.findByUsername(username).isEmpty());
        Assertions.assertNull(logRepository.findByUsername(username));
    }

    @Test
    void recovery_fail(){
        // given
        String username = "로그예외_anak";
        // when
        Assertions.assertThrows(UnexpectedRollbackException.class, () -> memberService.joinV3(username));
    }

    @Test
    void recovery_success(){
        // given
        String username = "로그예외_anak2";
        // when
        memberService.joinV3(username);
        // then
        Assertions.assertTrue(memberRepository.findByUsername(username).isPresent());
        Assertions.assertNull(logRepository.findByUsername(username));
    }
}
