package dev.haymon.ordermanagement.service;

import dev.haymon.ordermanagement.controller.dto.order.OrderItemRequest;
import dev.haymon.ordermanagement.controller.dto.order.OrderRequest;
import dev.haymon.ordermanagement.model.Order;
import dev.haymon.ordermanagement.model.OrderItem;
import dev.haymon.ordermanagement.model.Product;
import dev.haymon.ordermanagement.model.User;
import dev.haymon.ordermanagement.repository.OrderItemRepository;
import dev.haymon.ordermanagement.repository.OrderRepository;
import dev.haymon.ordermanagement.repository.ProductRepository;
import dev.haymon.ordermanagement.repository.UserRepository;
import dev.haymon.ordermanagement.util.SecurityUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;

    @Transactional
    public Order placeOrder(OrderRequest request) {

        User currentUser = SecurityUtil.getAuthenticatedUser();

        Order newOrder = Order.builder()
                .user(currentUser)
                .build();

        repository.save(newOrder);

        List<OrderItem> items = new ArrayList<>();
        for (OrderItemRequest item : request.getItems()) {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new EntityNotFoundException("Produto não existe"));

            OrderItem orderItem = OrderItem.builder()
                    .quantity(item.getQuantity())
                    .unitPrice(product.getPrice())
                    .order(newOrder)
                    .product(product)
                    .build();
            orderItemRepository.save(orderItem);
            items.add(orderItem);
        }
        newOrder.setItems(items);
        newOrder.calculateTotal();

        return repository.save(newOrder);
    }

    public Page<Order> getUserOrders(Integer pageNumber, Integer pageSize) {
        User currentUser = SecurityUtil.getAuthenticatedUser();
        Pageable orderPage = PageRequest.of(pageNumber, pageSize);
        return repository.findByUser(currentUser, orderPage);
    }

    public Optional<Order> gerOrderDetails(Integer id) {
        return repository.findById(id);
    }

    public Page<Order> getAllOrders(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Order> getUserOrders(Integer pageNumber, Integer pageSize, Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Pageable orderPage = PageRequest.of(pageNumber, pageSize, Sort.by("createdAt").descending());
        return repository.findByUserId(user.getId(), orderPage);
    }
}
