package dev.barapp.service;

import dev.barapp.entities.OrderEntity;
import dev.barapp.entities.WaiterEntity;
import dev.barapp.repositories.OrderRepository;
import dev.barapp.repositories.WaiterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final WaiterService waiterService;


    public OrderEntity createOrder(long restId, OrderEntity orderEntity) {
        WaiterEntity waiterEntity = waiterService.findAvailableWaiterByRestId(restId);

        orderEntity.setWaiter(waiterEntity);
        return orderRepository.save(orderEntity);
    }
}
