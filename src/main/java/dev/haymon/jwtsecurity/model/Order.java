package dev.haymon.jwtsecurity.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "tb_order")
@EntityListeners(AuditingEntityListener.class)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<OrderItem> items;

    @CreatedDate
    private LocalDateTime createdAt;
    private BigDecimal total;

    public void calculateTotal() {
        this.total = BigDecimal.ZERO;
        for (OrderItem item : items) {
            total = total.add(item.getUnitPrice().multiply(
                    new BigDecimal(item.getQuantity())
            ));
        }
    }
}
