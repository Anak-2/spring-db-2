package hello.itemservice.repository.custom;

import hello.itemservice.domain.Item;
import hello.itemservice.domain.QItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.Optional;

//@RepositoryDefinition(domainClass = Item.class, idClass = Long.class)
public interface JpaItemRepository extends MyItemRepository, JpaRepository<Item, Long>, MyBasicRepository<Item>{

//    Item save(Item item);

    Optional<Item> findById(Long id);
}
