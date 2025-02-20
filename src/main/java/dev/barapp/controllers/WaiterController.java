package dev.barapp.controllers;

import dev.barapp.DTOs.waiter.*;
import dev.barapp.entities.enums.OrderStatus;
import dev.barapp.service.OrderService;
import dev.barapp.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/waiter")
@RequiredArgsConstructor
public class WaiterController {
    private final RestaurantService restaurantService;
    private final OrderService orderService;

    @GetMapping("/test")
    public String test() {
        return "OK";
    }

    @GetMapping("/restaurant")
    public WaiterRestDTO getRestaurantByWaiterId(@RequestParam(value = "waiterId") long waiterId) throws ChangeSetPersister.NotFoundException {
        return restaurantService.findRestaurantByWaiterEntityId(waiterId);
    }

    @GetMapping("/tables")
    public WaiterTablesDTO getTablesByRestaurantId(@RequestParam(value = "restId") long restId) throws ChangeSetPersister.NotFoundException {
        return restaurantService.getWaiterTablesByRestaurantId(restId);
    }

    @GetMapping("/orders")
    public List<WaiterPreviewOrderDTO> getOrdersByWaiterId(@RequestParam(value = "waiterId") long waiterId) throws ChangeSetPersister.NotFoundException {
        return orderService.getWaiterOrders(waiterId);
    }

    @GetMapping("/order")
    public WaiterOrderDTO getOrderByOrderId(@RequestParam(value = "orderId") long orderId) throws ChangeSetPersister.NotFoundException {
        return orderService.getWaiterOrderById(orderId);
    }

    @PostMapping("/order/status")
    public WaiterOrderDTO setOrderStatus(@RequestBody Map<String, String> status, @RequestParam(value = "orderId") long orderId) throws ChangeSetPersister.NotFoundException {
        String newStatus = status.get("status");

        OrderStatus orderStatus = OrderStatus.valueOf(newStatus.toUpperCase());

        return orderService.setOrderStatus(orderId, orderStatus);
    }

    @PostMapping("/create/order")
    public WaiterNewOrderDTO createNewOrder(@RequestBody WaiterNewOrderDTO newOrder) throws ChangeSetPersister.NotFoundException {
        return orderService.createOrder(newOrder);
    }
}
