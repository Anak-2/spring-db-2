//package hello.itemservice.repository.springdata;
//
//import hello.itemservice.domain.Item;
//import hello.itemservice.repository.ItemRepository;
//import hello.itemservice.repository.ItemSearchCond;
//import hello.itemservice.repository.ItemUpdateDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.StringUtils;
//
//import java.util.List;
//import java.util.Optional;
//
////@Repository
//@Transactional
//@RequiredArgsConstructor
//public class JpaItemRepositoryV2 implements ItemRepository {
//
//    private final SpringDataItemRepository repository;
//
//    @Override
//    public Item save(Item item) {
//        return repository.save(item);
//    }
//
//    @Override
//    public void update(Long itemId, ItemUpdateDto updateParam) {
//        Item findItem = repository.findById(itemId).orElseThrow();
//        findItem.setItemName(updateParam.getItemName());
//        findItem.setPrice(updateParam.getPrice());
//        findItem.setQuantity(updateParam.getQuantity());
//    }
//
//    @Override
//    public Optional<Item> findById(Long id) {
//        return repository.findById(id);
//    }
//
////    1. 전체 조회
////    2. 이름 조회
////    3. 가격 조회
////    4. 이름 & 가격 조회
//    @Override
//    public List<Item> findAll(ItemSearchCond cond) {
//        String itemName = cond.getItemName();
//        Integer maxPrice = cond.getMaxPrice();
//
//        if(StringUtils.hasText(itemName) && maxPrice != null){
//            return repository.findItems(itemName, maxPrice);
//        }else if(StringUtils.hasText(itemName)){
//            return repository.findByItemNameContaining(itemName);
//        }else if(maxPrice != null){
//            return repository.findByPriceLessThanEqual(maxPrice);
//        }else{
//            return repository.findAll();
//        }
//    }
//}
