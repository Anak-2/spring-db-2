package study.springtx.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import study.springtx.repository.MemberRepository;
import study.springtx.repository.MessageRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@RequiredArgsConstructor
public class UserService {

    private static final String URL = "jdbc:h2:tcp://localhost/./test";

    private static final String USERNAME = "sa";

    private static final String PASSWORD = "";

    private final MemberRepository memberRepository;

    private final MessageRepository messageRepository;

    private final DataSource dataSource;

    public void register(final String name){
        TransactionSynchronizationManager.initSynchronization();

        Connection connection = DataSourceUtils.getConnection(dataSource);

        try{
            connection.setAutoCommit(false);

            memberRepository.saveMember(connection, name);


        } catch (final SQLException e) {
            try {
                connection.rollback();
            } catch (final SQLException ignored) {
            }
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
            // 커넥션 자원 해제
        }

    }
}
