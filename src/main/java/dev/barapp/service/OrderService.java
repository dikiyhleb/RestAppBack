package dev.barapp.service;

import dev.barapp.DTOs.user.UserNewOrderDTO;
import dev.barapp.entities.OrderEntity;
import dev.barapp.entities.WaiterEntity;
import dev.barapp.entities.enums.OrderStatus;
import dev.barapp.entities.enums.TableStatus;
import dev.barapp.mappers.OrderMapper;
import dev.barapp.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final WaiterService waiterService;
    private final TableService tableService;
    private final OrderMapper orderMapper;


    public UserNewOrderDTO createOrder(long restId, UserNewOrderDTO orderEntity) throws ChangeSetPersister.NotFoundException {
        WaiterEntity availableWaiter = waiterService.findAvailableWaiterByRestId(restId);

        if(tableService.isNonReserved(orderEntity.getTable())){
            OrderEntity newOrder = OrderEntity.builder()
                    .table(orderEntity.getTable())
                    .date(orderEntity.getDate())
                    .foods(orderEntity.getFoods())
                    .status(OrderStatus.NEW)
                    .user(orderEntity.getUser())
                    .waiter(availableWaiter)
                    .build();

            newOrder.getTable().setStatus(TableStatus.OCCUPIED);

            orderRepository.save(newOrder);

            return orderMapper.toUserNewOrderDTO(newOrder);
        }
        throw new RuntimeException("Table is not available");
    }
}
