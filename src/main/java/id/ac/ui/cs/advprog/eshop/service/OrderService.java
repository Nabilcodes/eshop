package id.ac.ui.cs.advprog.eshop.service;

public class OrderService {
    public Order createOrde(Order order);
    public Order updateStatus(String orderId, String status);
    public Order findById(String orderId);
    public List<Order> findAllByAuthor(String author);
}
