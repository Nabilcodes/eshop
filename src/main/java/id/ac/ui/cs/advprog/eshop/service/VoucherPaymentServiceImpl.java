package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class VoucherPaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        String voucherCode = paymentData.get("voucherCode");
        String paymentStatus = isValidVoucherCode(voucherCode) ? PaymentStatus.SUCCESS.toString() : PaymentStatus.REJECTED.toString();
        Payment payment = new Payment(UUID.randomUUID().toString(), paymentData, method, paymentStatus);
        paymentRepository.save(payment);

        if (paymentStatus.equals(PaymentStatus.SUCCESS.toString())) {
            order.setStatus(OrderStatus.SUCCESS.getValue());
            orderRepository.save(order);
        } else {
            order.setStatus(OrderStatus.FAILED.getValue());
            orderRepository.save(order);
        }

        return payment;
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        payment.setStatus(status);
        paymentRepository.save(payment);

        if (status.equals(PaymentStatus.SUCCESS.toString())) {
            Order order = orderRepository.findByPaymentId(payment.getId());
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

    private boolean isValidVoucherCode(String voucherCode) {
        if (voucherCode == null || voucherCode.length() != 16) {
            return false;
        }

        if (!voucherCode.startsWith("ESHOP")) {
            return false;
        }

        try {
            Long.parseLong(voucherCode.substring(5));
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}
