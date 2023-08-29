package study.springtx.propagation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final LogRepository logRepository;

    // 트랜잭션 사용 X
    public void joinV1(String username){
        Member member = new Member(username);
        Log log1 = new Log(username);

        memberRepository.save(member);
        logRepository.save(log1);
    }

    // 트랜잭션 모두 사용
    @Transactional
    public void joinV2(String username){
        Member member = new Member(username);
        Log log1 = new Log(username);

        memberRepository.save(member);
        logRepository.save(log1);
    }

    // 트랜잭션 모두 사용 & 예외 처리
    @Transactional
    public void joinV3(String username){
        Member member = new Member(username);
        Log log1 = new Log(username);

        memberRepository.save(member);
        try{
            logRepository.save(log1);
        }catch(Exception e){
            log.info("log 저장에 실패했습니다. log.username={}", log1.getMessage());
            log.info("정상 흐름 반환");
        }


    }

    // Log 트랜잭션과 완전히 분리
    @Transactional
    public void joinV4(String username){
        Member member = new Member(username);

        memberRepository.save(member);
    }
}
