package dev.barapp.service;

import dev.barapp.DTOs.user.UserNewOrderDTO;
import dev.barapp.DTOs.waiter.WaiterNewOrderDTO;
import dev.barapp.DTOs.waiter.WaiterOrderDTO;
import dev.barapp.DTOs.waiter.WaiterPreviewOrderDTO;
import dev.barapp.entities.*;
import dev.barapp.entities.enums.OrderStatus;
import dev.barapp.entities.enums.TableStatus;
import dev.barapp.mappers.OrderMapper;
import dev.barapp.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final WaiterService waiterService;
    private final TableService tableService;
    private final OrderMapper orderMapper;
    private final UserService userService;


    public UserNewOrderDTO createOrder(long restId, UserNewOrderDTO orderEntity) throws ChangeSetPersister.NotFoundException {
        WaiterEntity availableWaiter = waiterService.findAvailableWaiterByRestId(restId);

        if(tableService.isNonReserved(orderEntity.getTable())){
            TableEntity table = tableService.findById(orderEntity.getTable().getId());

            OrderEntity newOrder = OrderEntity.builder()
                    .table(table)
                    .date(orderEntity.getDate())
                    .foods(orderEntity.getFoods())
                    .status(OrderStatus.NEW)
                    .user(orderEntity.getUser())
                    .waiter(availableWaiter)
                    .total(orderEntity.getTotal())
                    .build();

            newOrder.getTable().setStatus(TableStatus.OCCUPIED);

            orderRepository.save(newOrder);

            return orderMapper.toUserNewOrderDTO(newOrder);
        }
        throw new RuntimeException("Table is not available");
    }

    public WaiterNewOrderDTO createOrder(WaiterNewOrderDTO orderEntity) throws ChangeSetPersister.NotFoundException {
        WaiterEntity waiter = waiterService.findWaiterById(orderEntity.waiterId());

        RestaurantEntity restaurant = waiter.getRestaurant();

        UserEntity user = userService.findUserByEmail(orderEntity.userEmail());

        OrderEntity newOrder = OrderEntity.builder()
                .total(orderEntity.total())
                .table(orderEntity.table())
                .user(user)
                .status(OrderStatus.NEW)
                .foods(orderEntity.foods())
                .date(orderEntity.date())
                .waiter(waiter)
                .build();

        newOrder.getTable().setStatus(TableStatus.OCCUPIED);

        orderRepository.save(newOrder);

        return orderMapper.toWaiterNewOrderDTO(newOrder);
    }

    public List<WaiterPreviewOrderDTO> getWaiterOrders(long waiterId) throws ChangeSetPersister.NotFoundException {
        List<OrderEntity> orders = orderRepository.findAllByWaiterId(waiterId);

        if(orders.isEmpty()){
            throw new ChangeSetPersister.NotFoundException();
        }

        return orderMapper.toWaiterPreviewOrderDTOs(orders);
    }

    public WaiterOrderDTO getWaiterOrderById(long orderId) throws ChangeSetPersister.NotFoundException {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow(ChangeSetPersister.NotFoundException::new);

        return orderMapper.toWaiterOrderDTO(orderEntity);
    }

    public WaiterOrderDTO setOrderStatus(long orderId, OrderStatus orderStatus) throws ChangeSetPersister.NotFoundException {
        OrderEntity order = orderRepository.findById(orderId).orElseThrow(ChangeSetPersister.NotFoundException::new);

        order.setStatus(orderStatus);

        orderRepository.save(order);

        return orderMapper.toWaiterOrderDTO(order);
    }
}
