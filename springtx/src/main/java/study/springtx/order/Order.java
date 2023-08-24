package study.springtx.order;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.EnableLoadTimeWeaving;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    @Enumerated(value = EnumType.STRING)
    private PayStatus payStatus;
}
