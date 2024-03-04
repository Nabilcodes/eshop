package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        this.paymentData = new HashMap<>();
        this.paymentData.put("author", "NabilMA");
        this.paymentData.put("amount", "1000000");
    }

    @Test // Happy test: create payment with no status
    void testCreatePaymentNoStatus() {
        Payment payment = new Payment("123456", this.paymentData, "VOUCHER_CODE", null);
        assertNull(payment.getStatus());
    }
    @Test // Happy test
    void testCreatePaymentSuccessStatus() {
        Payment payment = new Payment("123456", this.paymentData, "VOUCHER_CODE", "SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test // Unhappy test
    void testCreatePaymentInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("123456", this.paymentData,"VOUCHER_CODE" , "MEOW");
        });
    }
    @Test // Happy test
    void testSetStatusToSuccess() {
        Payment payment = new Payment("123456", this.paymentData,"VOUCHER_CODE", "REJECTED");
        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test // Unhappy test
    void testSetStatusToInvalidStatus() {
        Payment payment = new Payment("123456", this.paymentData,"VOUCHER_CODE", "SUCCESS");
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("MEOW"));
    }

    @Test // Happy test: create payment with valid sub-feature method
    void testCreatePaymentVoucherCodeMethod() {
        Payment payment = new Payment("123456", this.paymentData,"VOUCHER_CODE", "SUCCESS");
        assertEquals("VOUCHER_CODE", payment.getMethod());
    }

    @Test // Unhappy test
    void testCreatePaymentInvalidMethod() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("123456", this.paymentData,"CREDIT_CARD", "SUCCESS");
        });
    }

    @Test // Happy test
    void testSetMethodToVoucherCode() {
        Payment payment = new Payment("123456", this.paymentData,"BANK_TRANSFER", "SUCCESS");
        payment.setMethod("VOUCHER_CODE");
        assertEquals("VOUCHER_CODE", payment.getMethod());
    }

    @Test // Unhappy test
    void testSetMethodToInvalidMethod() {
        Payment payment = new Payment("123456", this.paymentData,"VOUCHER_CODE" "SUCCESS", this.paymentData);
        assertThrows(IllegalArgumentException.class, () -> payment.setMethod("CREDIT_CARD"));
    }

}
