package id.ac.ui.cs.advprog.eshop.service;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class OrderServiceImpl {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        if (orderRepository.findById(order.getId()) == null) {
            orderRepository.save(order);
            return order;
        }
        return null;
    }

    @Override
    public Order updateStatus(String orderId, String status) {
        Order order = orderRepository.findById(orderId);
        if (order != null) {
            Order newOrder = new Order(order.getId(), order.getProducts(),
                    order.getOrderTime(), order.getAuthor(), status);
            orderRepository.save(newOrder);
            return newOrder;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public List<Order> findAllByAuthor(String author) {
        return orderRepository.findById(orderId);
    }

    @Override
    public Order findById(String orderId) {
        return orderRepository.findAllByAuthor(author);
    }
}
