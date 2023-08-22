//package hello.itemservice.repository.springdata;
//
//import hello.itemservice.domain.Item;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.List;
//
//public interface SpringDataItemRepository extends JpaRepository<Item, Long>{
//
//    List<Item> findByItemNameLike(String itemName);
//    List<Item> findByItemNameContaining(String itemName);
//
//    List<Item> findByPriceLessThanEqual(Integer price);
//
//    // 쿼리 메서드
//    @Query("select i from Item i where i.itemName like %:itemName% and i.price <= :price")
//    List<Item> findItems(@Param("itemName") String itemName, @Param("price") Integer price);
//}
