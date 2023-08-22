package hello.itemservice.repository.custom;

import com.sun.xml.bind.v2.model.core.ID;

import java.util.Optional;

public interface MyBasicRepository<T> {

    <S extends T> S save (S entity);

}
