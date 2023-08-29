package study.springtx.propagation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
public class MemberFacade {

    private final MemberService memberService;
    private final LogRepository logRepository;

    public void joinV4(String username){
        memberService.joinV4(username);

        Log log1 = new Log(username);

        try{
            logRepository.save(log1);
        }catch (RuntimeException e){
            System.out.println(e.getClass()+" 발생");
        }
    }

}
