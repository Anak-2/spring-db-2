package hello.itemservice.repository.custom;

import hello.itemservice.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Map;
import java.util.Optional;

//@Repository
@RequiredArgsConstructor
@Transactional
public class MyBasicRepositoryImpl<T> implements MyBasicRepository<T>{

    private final EntityManager entityManager;
    private final NamedParameterJdbcTemplate template;

    @Override
    public <S extends T> S save(S entity) {
        System.out.println("============== Customized Save ==============");
        entityManager.persist(entity);
        entityManager.flush();
        return entity;
    }


}
