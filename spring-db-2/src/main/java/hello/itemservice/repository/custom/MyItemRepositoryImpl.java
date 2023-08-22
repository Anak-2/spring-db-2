package hello.itemservice.repository.custom;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.itemservice.domain.Item;
import hello.itemservice.domain.QItem;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Transactional
public class MyItemRepositoryImpl implements MyItemRepository{

    private final EntityManager em;
    private final JPAQueryFactory query;

    public MyItemRepositoryImpl(EntityManager em){
        this.em = em;
        query = new JPAQueryFactory(em);
    }

    public BooleanExpression likeName(String itemName){

        if(StringUtils.hasText(itemName)){
            return QItem.item.itemName.contains(itemName);
        }
        return null;
    }

    public BooleanExpression maxPrice(Integer maxPrice){

        if(maxPrice != null){
            return QItem.item.price.loe(maxPrice);
        }
        return null;
    }

    @Override
    public List<Item> findAll(ItemSearchCond cond) {
            String itemName = cond.getItemName();
            Integer maxPrice = cond.getMaxPrice();

            QItem item = QItem.item;

            List<Item> items = query
                    .select(item)
                    .from(item)
                    .where(likeName(itemName), maxPrice(maxPrice))
                    .fetch();

            return items;
    }

    @Override
    public void update(Long itemId, ItemUpdateDto updateParam) {
        Item findItem = em.find(Item.class, itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

}
