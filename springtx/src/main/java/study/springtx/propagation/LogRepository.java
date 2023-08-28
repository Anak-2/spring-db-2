package study.springtx.propagation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LogRepository{

    private final EntityManager em;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(Log logMessage){
        log.info("log 저장");
        em.persist(logMessage);

        if(logMessage.getMessage().contains(LogException.Message.getValue())){
            log.info("log 저장 시 예외 발생");
            throw new RuntimeException("예외 발생");
        }
    }

    public Log findByUsername(String username){
        log.info("find log");

        Optional<Log> findLog = em.createQuery("select l from Log l where l.message = :username")
                .setParameter("username", username)
                .getResultList().stream().findAny();

        return findLog.orElse(null);
    }
}
