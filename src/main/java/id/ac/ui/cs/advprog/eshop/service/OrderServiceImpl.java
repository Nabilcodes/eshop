package id.ac.ui.cs.advprog.eshop.service;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class OrderServiceImpl {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {return null;}

    @Override
    public Order updateStatus(String orderId, String status) {return null;}

    @Override
    public List<Order> findAllByAuthor(String author) {return null;}

    @Override
    public Order findById(String orderId) {return null;}
}
