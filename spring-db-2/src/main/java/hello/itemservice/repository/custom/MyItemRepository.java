package hello.itemservice.repository.custom;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;

import java.util.List;
import java.util.Optional;

public interface MyItemRepository {

    List<Item> findAll(ItemSearchCond cond);

    void update(Long itemId, ItemUpdateDto updateParam);

}
