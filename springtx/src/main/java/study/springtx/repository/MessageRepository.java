package study.springtx.repository;

import java.sql.Connection;

public interface MessageRepository {

    void saveMessage(final Connection connection, final String message);

}
