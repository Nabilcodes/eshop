package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepository paymentRepository;

    @Mock
    OrderRepository orderRepository;

    Map<String, String> paymentData;
    List<Payment> payments;

    @BeforeEach
    void setUp() {

        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("author", "NabilMA");
        paymentData.put("amount", "1000000");

        payments = new ArrayList<>();
        Payment payment1 = new Payment("13652556-012a-4c07-b546-54eb1396d79b",
                paymentData, "VOUCHER_CODE", "SUCCESS");
        payments.add(payment1);
        Payment payment2 = new Payment("7f9015bb-4b15-42f4-aebc-c3af385fb078",
                paymentData, "VOUCHER_CODE", "SUCCESS");
        payments.add(payment2);
    }

//    @Test
//    void testCreatePayment() {
//        Payment payment = payments.get(1);
//        doReturn(payment).when(paymentRepository).save(payment);
//
//        Payment result = paymentService.addPayment(payment);
//        verify(paymentRepository, times(1)).save(payment);
//        assertEquals(payment.getId(), result.getId());
//    }

//    @Test
//    void testCreatePaymentIfAlreadyExist() {
//        Payment payment = payments.get(1);
//        doReturn(payment).when(paymentRepository).findById(payment.getId());
//
//        assertNull(paymentService.addPayment(payment, ));
//        verify(paymentRepository, times(0)).save(payment);
//    }

    @Test
    void testUpdateStatus() {
        Payment payment = payments.get(1);
        Payment newPayment = new Payment(payment.getId(), payment.getPaymentData(),
                payment.getMethod(), PaymentStatus.SUCCESS.getValue());
        doReturn(payment).when(paymentRepository).findById(payment.getId());
        doReturn(payment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.setStatus(payment, PaymentStatus.SUCCESS.toString());

        assertEquals(payment.getId(), result.getId());
        assertEquals(PaymentStatus.SUCCESS.toString(), result.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testUpdateStatusInvalidStatus() {
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        assertThrows(IllegalArgumentException.class,
                () -> paymentService.setStatus(payment, "MEOW"));

        verify(paymentRepository, times(0)).save(any(Payment.class));
    }

    @Test
    void testUpdateStatusInvalidPaymentId() {
        doReturn(null).when(paymentRepository).findById("zczc");

        assertThrows(NoSuchElementException.class,
                () -> paymentService.setStatus("zczc", PaymentStatus.SUCCESS.toString()));

        verify(paymentRepository, times(0)).save(any(Payment.class));
    }

    @Test
    void testFindIdIfIdFound() {
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        Payment result = paymentService.getPayment(payment.getId());
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testFindIdIfIdNotFound() {
        doReturn(null).when(paymentRepository).findById("zczc");
        assertNull(paymentService.getPayment("zczc"));
    }

    @Test
    void testFindAllPayments() {
        Payment payment = payments.get(1);
        doReturn(payments).when(paymentRepository).findAll();

        List<Payment> result = paymentService.getAllPayments();
        assertEquals(2, result.size());
    }
}
