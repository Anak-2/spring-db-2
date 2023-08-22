package hello.itemservice.service;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import hello.itemservice.repository.custom.JpaItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Spring Data Jpa 만 사용하도록
@Service
@RequiredArgsConstructor
public class ItemServiceV2 implements ItemService{

    private final JpaItemRepository repository;


    @Override
    public Item save(Item item) {
        return repository.save(item);
    }

    @Override
    public void update(Long itemId, ItemUpdateDto updateParam) {
        repository.update(itemId, updateParam);
    }

    @Override
    public Optional<Item> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Item> findItems(ItemSearchCond itemSearch) {
        return repository.findAll(itemSearch);
    }
}
