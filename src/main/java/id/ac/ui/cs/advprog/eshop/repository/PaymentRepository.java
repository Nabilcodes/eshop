//package id.ac.ui.cs.advprog.eshop.repository;
//
//import id.ac.ui.cs.advprog.eshop.model.Order;
//import id.ac.ui.cs.advprog.eshop.model.Payment;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class PaymentRepository {
//    private List<Payment> paymentData = new ArrayList<>();
//    public Payment save(Payment payment) {
//        int i = 0;
//        for (Payment savedPayment : paymentData) {
//            if (savedPayment.getId().equals(payment.getId())) {
//                paymentData.remove(i);
//                paymentData.add(i, payment);
//                return payment;
//            }
//            i+=1;
//        }
//
//        paymentData.add(payment);
//        return payment;
//    }
//
//    public Payment findById(String id) {
//        for (Payment savedPayment : paymentData) {
//            if (savedPayment.getId().equals(id)) {
//                return savedPayment;
//            }
//        }
//        return null;
//    }
//
//    public List<Payment> findAll() {
//        List<Payment> result = new ArrayList<>();
//        for (Payment savedOrder : paymentData) {
//            result.add(savedOrder);
//        }
//        return result;
//    }
//}

package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentRepository {
    private List<Payment> paymentData = new ArrayList<>();

    public Payment save(Payment payment) {
        int index = paymentData.indexOf(payment);
        if (index != -1) {
            paymentData.set(index, payment);
        } else {
            paymentData.add(payment);
        }
        return payment;
    }

    public Payment findById(String id) {
        return paymentData.stream()
                .filter(payment -> payment.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Payment> findAll() {
        return new ArrayList<>(paymentData);
    }
}
