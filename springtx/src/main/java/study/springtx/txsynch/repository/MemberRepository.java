package study.springtx.txsynch.repository;

import java.sql.Connection;

public interface MemberRepository {

    void saveMember(final Connection connection, final String name);

}
