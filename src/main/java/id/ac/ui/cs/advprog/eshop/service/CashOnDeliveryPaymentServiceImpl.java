import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class CashOnDeliveryPaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        String address = paymentData.get("address");
        String deliveryFee = paymentData.get("deliveryFee");

        if (address == null || address.isEmpty() || deliveryFee == null || deliveryFee.isEmpty()) {
            throw new IllegalArgumentException("Address and delivery fee cannot be empty for Cash on Delivery.");
        }

        Payment payment = new Payment(UUID.randomUUID().toString(), paymentData, method, PaymentStatus.REJECTED.toString());
        paymentRepository.save(payment);

        return payment;
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        payment.setStatus(status);
        paymentRepository.save(payment);

        if (status.equals(PaymentStatus.SUCCESS.toString())) {
            Order order = orderRepository.findAll().stream()
                    .filter(o -> o.getPaymentId() != null && o.getPaymentId().equals(payment.getId()))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("Order not found for paymentId: " + payment.getId()));

            order.setStatus(OrderStatus.SUCCESS.getValue());
            orderRepository.save(order);
        }

        return payment;
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}
