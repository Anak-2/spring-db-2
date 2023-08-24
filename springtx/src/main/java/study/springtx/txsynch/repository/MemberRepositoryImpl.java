package study.springtx.txsynch.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MemberRepositoryImpl implements MemberRepository{

    @Override
    public void saveMember(final Connection connection, final String name) {
        try {
            String sql = "INSERT INTO member(name) VALUES(?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.execute();
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }
}
