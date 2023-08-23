package study.springtx.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MessageRepositoryImpl implements MessageRepository{

    @Override
    public void saveMessage(Connection connection, String message) {
        try {
            String sql = "INSERT INTO message(content) VALUES(?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, message);
            preparedStatement.execute();
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }
}
