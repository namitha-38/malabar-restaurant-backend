package com.restaurant.restaurant_backend;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "*")
@RestController
public class RestaurantController {

    private List<Order> orders = new ArrayList<>();

    @GetMapping("/")
    public String home() {
        return "Restaurant Ordering System Backend is Running!";
    }

    @GetMapping("/menu")
public List<MenuItem> getMenu() {
    List<MenuItem> menu = new ArrayList<>();

    menu.add(new MenuItem(1, "Chicken Burger", 180, "Main Course", true));
    menu.add(new MenuItem(2, "Chicken Pizza", 250, "Main Course", true));
    menu.add(new MenuItem(3, "Grilled Chicken", 280, "Main Course", true));
    menu.add(new MenuItem(4, "Chocolate Cake", 140, "Starter", true));

    return menu;
}

    @PostMapping("/order")
public Order placeOrder(@RequestBody Order order) {

    double subtotal = 0;

    for (OrderItem orderItem : order.getItems()) {
        subtotal = subtotal + orderItem.getSubtotal();
    }

    double discount = order.getDiscountAmount();

    double total = subtotal - discount;

    if (total < 0) {
        total = 0;
    }

    order.setTotalAmount(total);

    orders.add(order);

    return order;
}

    @GetMapping("/orders")
    public List<Order> getOrders() {
        return orders;
    }
}